import java.io.IOException;

/**
 * Homework 02 - Droptris AI.
 * https://courses.cs.ttu.ee/pages/ITI0011:HW02_Droptris
 */
public class HW02 {

    /**
     * Current column for square.
     */
    public static int column = 0;
    /**
     * Block start index.
     */
    public static final int BLOCK_START = 11;
    /**
     * Block end index.
     */
    public static final int BLOCK_END = 12;
    /**
     * Square start index.
     */
    public static final int SQUARE_PLACE = 4;
    /**
     * Where to put the stick.
     */
    public static final int STICK_PLACE = 6;
    /**
     * Score value starting index.
     */
    public static final int SC0RE_START = 10;
    /**
     * Score value ending index.
     */
    public static final int SCORE_END = 14;
    /**
     * Code value ending index.
     */
    public static final int CODE_END = 6;
    /**
     * Block string ending index.
     */
    public static final int BLOCK_FIND = 7;


    /**
     * The main method. You can use this to initialize the game.
     * Tester will not execute the main method.
     * @param args Arguments from command line
     */
    public static void main(String[] args) {

        Configuration c = new Configuration(1, 1, 1);
        setup();
        run(c.toString());
    }

    /**
     * Optional setup. This method will be called
     * before the game is started. You can do some
     * precalculations here if needed.
     *
     * If you don't need to precalculate anything,
     * just leave it empty.
     */
    public static void setup() {
    }

    /**
     * The method to execute your AI.
     * @param connectionString JSON-formatted connection string.
     *                         If you implement Socket connection yourself
     *                         you should use this string directly when connecting.
     *                         If you use DroptrisConnection, you can ignore that.
     * @return The final score. You should read the score from the server.
     */
    public static int run(String connectionString) {
        try {
            DroptrisConnection conn = new DroptrisConnection("rerakk", true);
            String line = conn.readLine();
            String score = "";

            System.out.println(line);

            line = "{\"block\": \"x\"}";

            while (line != null && line.substring(2, BLOCK_FIND).equals("block")) {
                line = conn.readLine();
                if (line != null && line.substring(2, CODE_END).equals("code")) {
                    System.out.println(line);
                    line = conn.readLine();
                }
                if (line == null) {
                    break;
                }
                System.out.println(line);
                conn.sendAction(bestPlacement(line));
                System.out.println(conn.readScoreData());
                score = conn.readScoreData().substring(SC0RE_START, SCORE_END);
            }
            System.out.println("no more blocks!");
            System.out.println("result: " + score);
            System.out.println("game over!");
            return Integer.valueOf(score);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Find the best placement for given block.
     * @param input contains the block information.
     * @return string value to be sent to the server.
     */
    public static String bestPlacement(String input) {
        String result = "{\"column\": " + column + ", \"rotation\": " + 0 + "}";
        String block = input.substring(BLOCK_START, BLOCK_END);
        if (column > SQUARE_PLACE) {
            column = 0;
        }
        if (block.equals("O")) {
            result = "{\"column\": " + column + ", \"rotation\": " + 0 + "}";
            column += 2;
        } else if (block.equals("I")) {
            result = "{\"column\": " + STICK_PLACE + ", \"rotation\": " + 1 + "}";
        }
        return result;
    }

}
