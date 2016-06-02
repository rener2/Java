import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


/**
 * Template for HW01 Treasurehunt.
 * More information:
 * https://courses.cs.ttu.ee/pages/ITI0011:Aardejaht
 */
public class HW01 {

    /**
     * Program logic.
     * @param args for console commands.
     */
    public static void main(String[] args) {
        leaderboards = new String[BIG_NUMBER][LEADERBOARD_LENGTH];
        boolean again = true;
        whichGame();
        while (again) {
            askInput();
            int counter = 0;
            int originalTreasures = treasures;
            while (treasures > 0) {
                System.out.println("kaevamisi: " + counter
                        + ", aardeid jäänud: " + treasures);
                nextMoveInput(originalTreasures);
                counter++;
            }
            leaderBoard(counter, originalTreasures);
            again = repeatGame(counter);
        }
    }

    /**
     * The game field containing the values,
     * that the player has already discovered.
     */
    private static String[][] emptyMatrix;

    /**
     * The game field containing the
     * real values, that are hidden
     * from the player.
     */
    private static String[][] matrix;

    /**
     * 2D array containing the 10 best
     * results for each game field.
     */
    private static String[][] leaderboards;

    /**
     * The amount of treasures left on
     * the game field.
     */
    private static int treasures;

    /**
     * Amount of rows in the game field.
     */
    private static int row;

    /**
     * Amount of columns in the game field.
     */
    private static int col;

    /**
     * Current variation of the game chosen
     * by the user in the beginning.
     */
    private static String variation;

    /**
     * Number of places on the leaderboard,
     * plus the leaderboards name.
     */
    public static final int LEADERBOARD_LENGTH = 11;

    /**
     * Minimum amount of symbols submit as his/hers nickname.
     */
    public static final int SHORTEST_NAME = 3;

    /**
     * Maximum amount of symbols submit as his/hers nickname.
     */
    public static final int LONGEST_NAME = 20;

    /**
     * Big integer value.
     */
    public static final int BIG_NUMBER = 10000;

    /**
     * Value to return in makeMove in case
     * the cell was empty.
     */
    public static final int CELL_EMPTY = 0;


    /**
     * Makes move to cell in certain row and column
     * and returns the contents of the cell.
     * Use CELL_* constants in return.
     *
     * @param nextRow user selected row.
     * @param nextCol user selected column.
     */
    public static void makeMove(int nextRow, int nextCol) {
        boolean alreadyChosen = false;
        if (!emptyMatrix[nextCol][nextRow].equals(".")) {
            alreadyChosen = true;
        }
        emptyMatrix[nextCol][nextRow] = matrix[nextCol][nextRow];
        if (emptyMatrix[nextCol][nextRow].equals(String.valueOf(CELL_EMPTY))
                && !alreadyChosen) {
            mineSweeper(nextCol, nextRow);
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(emptyMatrix[i][j]);
            }
            System.out.println();
        }
        if (matrix[nextCol][nextRow].equals("+") && !alreadyChosen) {
            System.out.println("AARE!\n");
            treasures--;
        }
    }

    /**
     * Creates a map with certain measures and treasures.
     * As this is a static method which doesn't return anything (void),
     * you should store the created map internally.
     * This means you can choose your own implementation of how to store
     * the map.
     * The treasures should be put on the map randomly using setCell method.
     */
    public static void createMap() {

        matrix = new String[col][row];
        emptyMatrix = new String[col][row];
        for (String[] i : matrix) {
            Arrays.fill(i, String.valueOf(CELL_EMPTY));
        }
        for (String[] i : emptyMatrix) {
            Arrays.fill(i, ".");
        }
        setCell();
        if (variation.equals("kaardi avamine")) {
            addAmounts();
        } else if (variation.equals("soe-külm")) {
            addDistances();
        }
    }

    /**
     * Sets the cell value for the active map (created earlier using
     * createMap method).
     * This method is required to test certain maps
     */
    public static void setCell() {
        for (int number = 0; number != treasures; number++) {
            Random randomRow = new Random();
            Random randomCol = new Random();
            int treasureRow = randomRow.nextInt(row);
            int treasureCol = randomCol.nextInt(col);
            if (matrix[treasureCol][treasureRow].equals("+")) {
                number--;
            }
            if (matrix[treasureCol][treasureRow].equals(
                    String.valueOf(CELL_EMPTY))) {
                matrix[treasureCol][treasureRow] = "+";
            }
        }
    }

    /**
     * Adds the amount of treasures near the
     * position as the positions
     * value.
     */
    public static void addAmounts() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int surroundingCount = 0;
                if (!matrix[i][j].equals("+")) {
                    if (i != 0 && j != 0 && matrix[i - 1][j - 1].equals("+")) {
                        surroundingCount++;
                    }
                    if (i != 0 && matrix[i - 1][j].equals("+")) {
                        surroundingCount++;
                    }
                    if (i != 0 && j != matrix[0].length - 1
                            && matrix[i - 1][j + 1].equals("+")) {
                        surroundingCount++;
                    }
                    if (j != 0 && matrix[i][j - 1].equals("+")) {
                        surroundingCount++;
                    }
                    if (j != matrix[0].length - 1
                            && matrix[i][j + 1].equals("+")) {
                        surroundingCount++;
                    }
                    if (i != matrix.length - 1 && j != 0
                            && matrix[i + 1][j - 1].equals("+")) {
                        surroundingCount++;
                    }
                    if (i != matrix.length - 1
                            && matrix[i + 1][j].equals("+")) {
                        surroundingCount++;
                    }
                    if (i != matrix.length - 1
                            && j != matrix[0].length - 1
                            && matrix[i + 1][j + 1].equals("+")) {
                        surroundingCount++;
                    }
                    String value = String.valueOf(surroundingCount);
                    matrix[i][j] = value;
                }
            }
        }
    }

    /**
     * Ask for user input to create the map
     * with that specific size and add
     * the amount of treasures given.
     */
    public static void askInput() {
        String moveInput = "";
        String pattern = "^[1-9][0-9]*[,][1-9][0-9]*[,][1-9][0-9]*$";
        String[] inputArray;
        while (!moveInput.matches(pattern) || treasures > row * col) {
            Scanner userInput = new Scanner(System.in);
            System.out.print("Sisesta M (ridade arv), N (veergude arv),"
                    + " X (aarete arv): ");
            moveInput = userInput.nextLine().replaceAll(" ", "");
            if (moveInput.matches(pattern)) {
                inputArray = moveInput.split(",");
                row = Integer.parseInt(inputArray[0]);
                col = Integer.parseInt(inputArray[1]);
                treasures = Integer.parseInt(inputArray[2]);
            }
        }
        createMap();
        System.out.println();
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                System.out.print(emptyMatrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Ask for user input to either make a
     * next move or to take an order from
     * the user. If the user wants to make
     * a next move then return the move, else
     * complete the order by calling out another
     * function and ask for the input again until
     * the user gives a correct input to make the next
     * move
     *
     * @param originalTreasures   Amount of treasures on the game field
     * @return Integer array containing the next move.
     */
    public static int[] nextMoveInput(int originalTreasures) {
        int[] nextMove = new int[2];
        String[] inputArray;
        nextMove[0] = BIG_NUMBER;
        nextMove[1] = BIG_NUMBER;
        String inputPattern = "^[0-9][0-9]*[,][0-9][0-9]*$";
        String nextInput = "";

        while (!(nextInput.matches(inputPattern)) || nextMove[0] >= row
                || nextMove[1] >= col) {
            Scanner userInput = new Scanner(System.in);
            System.out.print("käik (rida, veerg) või käsk: ");
            nextInput = userInput.nextLine();
            if (nextInput.equals("edetabel")) {
                printLeaderboard(originalTreasures);
            } else if (nextInput.equals("puhastada")) {
                clearLeaderboard(originalTreasures);
            }
            if (nextInput.matches(inputPattern)) {
                nextInput = nextInput.replaceAll(" ", "");
                inputArray = nextInput.split(",");
                nextMove[0] = Integer.parseInt(inputArray[0]);
                nextMove[1] = Integer.parseInt(inputArray[1]);
            }
        }
        makeMove(nextMove[0], nextMove[1]);
        return nextMove;

    }

    /**
     * Ask for user input if the user wants
     * to play another game or not.
     *
     * @param counter  Value of how many times the user
     *                 has already guessed different points
     *                 on the map.
     * @return boolean value of users answer(yes = true, no = false).
     */
    public static boolean repeatGame(int counter) {
        Scanner newInput = new Scanner(System.in);
        System.out.println("\nMäng läbi! Kokku tehti " + counter
                + " kaevamist.\n" + "Kas soovid veel mängida?");
        String answer = newInput.nextLine().toLowerCase();
        return (answer.startsWith("j") || answer.startsWith("y"));
    }

    /**
     * Find out if a leaderboard already exists
     * for the given game, if it does then insert
     * the users result into the correct place, if not
     * then create a leaderboard for given game and insert
     * the users result there.
     *
     * @param originalTreasures Amount of treasures at the
     *                          start of the game.
     * @param result            Value of how many guesses
     *                          it took for the user to find
     *                          all the treasures
     */
    public static void leaderBoard(int result, int originalTreasures) {
        String gameField = String.valueOf(col + "x" + row + ", "
                + originalTreasures);
        boolean exists = false;
        int gameFld = 0;

        for (int i = 0; i < leaderboards.length; i++) {
            if (leaderboards[i][0] != null
                    && leaderboards[i][0].equals(gameField)) {
                exists = true;
                gameFld = i;
                break;
            }
        }
        if (exists) {
            for (int j = 1; j < leaderboards[j].length; j++) {
                if (leaderboards[gameFld][j] == null) {
                    leaderboards[gameFld][j] = askName() + " "
                            + String.valueOf(result);
                    break;
                } else if (leaderboards[gameFld][j] != null) {
                    String prevComp = leaderboards[gameFld][j];
                    String[] guesse = prevComp.replaceAll(" ", ",").split(",");
                    int previousResult = Integer.valueOf(guesse[1]);
                    if (previousResult > result) {
                        for (int z = leaderboards[0].length - 1; z + 1 != j;
                             z--) {
                            if (leaderboards[gameFld][z] != null) {
                                if (z < leaderboards[0].length) {
                                    leaderboards[gameFld][z + 1]
                                            = leaderboards[gameFld][z];
                                }
                            }
                        }
                        leaderboards[gameFld][j] = (askName() + " "
                                + String.valueOf(result));
                        break;
                    }
                }
            }
        }
        if (!exists) {
            for (int i = 0; i < leaderboards.length; i++) {
                if (leaderboards[i][0] == null) {
                    leaderboards[i][0] = gameField;
                    leaderboards[i][1] = askName() + " "
                            + String.valueOf(result);
                    break;
                }
            }
        }
    }

    /**
     * Print out current games leaderboard.
     *
     *@param originalTreasures current games treasure amount
     */
    public static void printLeaderboard(int originalTreasures) {
        String gameField = String.valueOf(col + "x" + row + ", "
                + originalTreasures);
        for (int i = 0; i < leaderboards.length; i++) {
            if (leaderboards[i][0] != null
                    && leaderboards[i][0].equals(gameField)) {
                System.out.println("\nEdetabel seadetega "
                        + leaderboards[i][0]);
                for (int j = 1; j < leaderboards[0].length; j++) {
                    if (leaderboards[i][j] != null) {
                        String[] koht = leaderboards[i][j].replaceAll(" ", ",")
                                .split(",");
                        int nameLength = koht[0].length();
                        String addedSpace = " ";
                        for (int s = 0; s < LONGEST_NAME - nameLength; s++) {
                            addedSpace += " ";
                        }
                        if (j == leaderboards[0].length - 1) {
                            addedSpace = addedSpace.substring(0,
                                    addedSpace.length() - 1);
                        }
                        System.out.println(j + ". " + koht[0] + addedSpace
                                + koht[1]);
                    }
                }
            }
        }
        System.out.println();
    }

    /**
     * Empty current games game field.
     *
     * @param originalTreasures current games treasure amount
     */
    public static void clearLeaderboard(int originalTreasures) {
        String gameField = String.valueOf(col + "x" + row + ", "
                + originalTreasures);
        for (int i = 0; i < leaderboards.length; i++) {
            if (leaderboards[i][0] != null
                    && leaderboards[i][0].equals(gameField)) {
                for (int j = 0; j < leaderboards[i].length; j++) {
                    if (leaderboards[i][j] != null) {
                        leaderboards[i][j] = null;
                    }
                }
            }
        }
        System.out.println("\nEdetabel seadetega " + row + "x" + col + ", "
                + originalTreasures + " puhastatud");
    }

    /**
     * Check the user movement and if
     * there can logically not be a treasure
     * in some places then print that information
     * to the user.
     *
     * @return  User inputted correct name.
     */
    public static String askName() {
        Scanner input = new Scanner(System.in);
        boolean conditions = false;
        String name = "";
        while (!conditions) {
            System.out.print("Sisestage oma nimi: ");
            name = input.nextLine();
            if (name.length() <= LONGEST_NAME && name.length() >= SHORTEST_NAME
                    && name.matches("^[a-zA-Z]+$")) {
                conditions = true;
            }
        }
        return name;
    }

    /**
     * Open all the surrounding points of (i,j)
     * and if some of the surrounding values are also
     * 0 then do the same to them.
     *
     * @param i    User guessed row.
     * @param j    User guessed column.
     */
    public static void mineSweeper(int i, int j) {
        emptyMatrix[i][j] = matrix[i][j];
        if (i != 0 && j != 0) {
            if (!matrix[i - 1][j - 1].equals("0")) {
                emptyMatrix[i - 1][j - 1] = matrix[i - 1][j - 1];
            }
            if (matrix[i - 1][j - 1].equals("0")
                    && !emptyMatrix[i - 1][j - 1].equals("0")) {
                mineSweeper(i - 1, j - 1);
            }
        }
        if (i != 0) {
            if (!matrix[i - 1][j].equals("0")) {
                emptyMatrix[i - 1][j] = matrix[i - 1][j];
            }
            if (matrix[i - 1][j].equals("0")
                    && !emptyMatrix[i - 1][j].equals("0")) {
                mineSweeper(i - 1, j);
            }
        }
        if (i != 0 && j != matrix[0].length - 1) {
            if (!matrix[i - 1][j + 1].equals("0")) {
                emptyMatrix[i - 1][j + 1] = matrix[i - 1][j + 1];
            }
            if (matrix[i - 1][j + 1].equals("0")
                    && !emptyMatrix[i - 1][j + 1].equals("0")) {
                mineSweeper(i - 1, j + 1);
            }
        }
        if (j != 0) {
            if (!matrix[i][j - 1].equals("0")) {
                emptyMatrix[i][j - 1] = matrix[i][j - 1];
            }
            if (matrix[i][j - 1].equals("0")
                    && !emptyMatrix[i][j - 1].equals("0")) {
                mineSweeper(i, j - 1);
            }
        }
        if (j != matrix[0].length - 1) {
            if (!matrix[i][j + 1].equals("0")) {
                emptyMatrix[i][j + 1] = matrix[i][j + 1];
            }
            if (matrix[i][j + 1].equals("0")
                    && !emptyMatrix[i][j + 1].equals("0")) {
                mineSweeper(i, j + 1);
            }
        }
        if (i != matrix.length - 1 && j != 0) {
            if (!matrix[i + 1][j - 1].equals("0")) {
                emptyMatrix[i + 1][j - 1] = matrix[i + 1][j - 1];
            }
            if (matrix[i + 1][j - 1].equals("0")
                    && !emptyMatrix[i + 1][j - 1].equals("0")) {
                mineSweeper(i + 1, j - 1);
            }
        }
        if (i != matrix.length - 1) {
            if (!matrix[i + 1][j].equals("0")) {
                emptyMatrix[i + 1][j] = matrix[i + 1][j];
            }
            if (matrix[i + 1][j].equals("0")
                    && !emptyMatrix[i + 1][j].equals("0")) {
                mineSweeper(i + 1, j);
            }
        }
        if (i != matrix.length - 1 && j != matrix[0].length - 1) {
            if (!matrix[i + 1][j + 1].equals("0")) {
                emptyMatrix[i + 1][j + 1] = matrix[i + 1][j + 1];
            }
            if (matrix[i + 1][j + 1].equals("0")
                    && !emptyMatrix[i + 1][j + 1].equals("0")) {
                mineSweeper(i + 1, j + 1);
            }
        }
    }

    /**
     * Ask user which variation of the game user wants to play.
     */
    public static void whichGame() {
        Scanner userInput = new Scanner(System.in);
        variation = "";
        while (!variation.equals("kaardi avamine")
                && !variation.equals("soe-külm")) {
            System.out.print("Millist variatsiooni mängust soovite mängida "
                    + "(Kaardi avamine/Soe-külm)? ");
            variation = userInput.nextLine().toLowerCase();
        }
    }

    /**
     * Calculate how far is the closest unguessed
     * treasure, then print that information
     * to the user.
     *
     * @param userRow    User guessed row.
     * @param userCol    User guessed column.
     * @return the distance between the user guessed
     * point on the map and the closest treasure which
     * has not yet been discovered by the user.
     */
    public static int howFar(int userRow, int userCol) {
        int smallestDistance = BIG_NUMBER;
        int treasureRow;
        int treasureCol;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j].equals("+")
                        && !emptyMatrix[i][j].equals("+")) {
                    treasureRow = i;
                    treasureCol = j;
                    int width = Math.abs(treasureRow - userRow);
                    int height = Math.abs(treasureCol - userCol);
                    double distance = Math.sqrt(width * width + height
                            * height);
                    int minDistance = (int) (distance);
                    if (minDistance < smallestDistance) {
                        smallestDistance = minDistance;
                    }
                }
            }
        }
        return smallestDistance;
    }

    /**
     * Fills the game field with values that
     * represent distance from the closest treasure.
     */
    public static void addDistances() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals(String.valueOf(CELL_EMPTY))) {
                    matrix[i][j] = String.valueOf(howFar(i, j));
                }
            }
        }
    }

}

