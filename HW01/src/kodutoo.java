import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


/**
 * Template for HW01: Treasurehunt.
 * More information:
 * https://courses.cs.ttu.ee/pages/ITI0011:Aardejaht
 */
public class kodutoo {

    /**
     * Program logic.
     * @param args for console commands.
     */
    public static void main(String[] args) {
        boolean again = true;
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
     * Big integer value.
     */
    public static final int BIG_NUMBER = 10000;

    /**
     * Value to return in makeMove in case
     * the cell was empty.
     */
    public static final int CELL_EMPTY = 0;

    /**
     * Value to return in makeMove in case
     * the cell contained treasure.
     */
    public static final int CELL_TREASURE = 1;

    /**
     * Value to return in makeMove in case
     * the cell does not exist.
     */
    public static final int CELL_ERROR = -1;

    /**
     * Makes move to cell in certain row and column
     * and returns the contents of the cell.
     * Use CELL_* constants in return.
     *
     * @param nextRow Row to make move to.
     * @param nextCol Column to make move to.
     * @return CELL_EMPTY if the cell doesn't contain treasure.
     */
    public static int makeMove(int nextRow, int nextCol) {
        if (nextRow >= row || nextRow < 0 || nextCol >= col || nextCol < 0) {
            return CELL_ERROR; //see siin ja l6pu returnid ainult testeri p2rast
        }
        boolean alreadyChosen = false;
        if (!emptyMatrix[nextCol][nextRow].equals(".")) {
            alreadyChosen = true;
        }
        emptyMatrix[nextCol][nextRow] = matrix[nextCol][nextRow];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(emptyMatrix[i][j]);
            }
            System.out.println();
        }
        if (matrix[nextCol][nextRow].equals("+") && !alreadyChosen) {
            System.out.println("AARE!");
            treasures--;
        }
        if (matrix[nextCol][nextRow].equals("+")) {
            return CELL_TREASURE;
        }
        if (!matrix[nextCol][nextRow].equals("+")) {
            return CELL_EMPTY;
        }
        return CELL_ERROR;
    }

    /**
     * Creates a map with certain measures and treasures.
     * As this is a static method which doesn't return anything (void),
     * you should store the created map internally.
     * This means you can choose your own implementation of how to store
     * the map.
     * The treasures should be put on the map randomly using setCell method.
     *
     * @return          whether map was created.
     */
    public static boolean createMap(int col, int row, int  treasures) {
        if (row <= 0 || col <= 0 || treasures > row * col) {
            return false;
        }
        matrix = new String[col][row];
        emptyMatrix = new String[col][row];
        for (String[] i : matrix) {
            Arrays.fill(i, String.valueOf(CELL_EMPTY));
        }
        for (String[] i : emptyMatrix) {
            Arrays.fill(i, ".");
        }
        return setCell(row, col, CELL_TREASURE);
    }

    /**
     * Sets the cell value for the active map (created earlier using
     * createMap method).
     * This method is required to test certain maps
     *
     * @return Whether the cell value was set.
     */
    public static boolean setCell(int cellRow, int cellCol, int cellContents) {
        for (int number = 0; number != treasures; number++) {
            Random randomRow = new Random();
            Random randomCol = new Random();
            int treasureRow = randomRow.nextInt(row);
            int treasureCol = randomCol.nextInt(col);
            if (matrix[treasureCol][treasureRow].equals("+")) {
                number--;
            }
            if (matrix[treasureCol][treasureRow].equals("0")) {
                matrix[treasureCol][treasureRow] = "+";
            }
        }
        return (cellRow < row && cellRow >= 0 && cellCol < col && cellCol >= 0);
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
        createMap(col, row, treasures);
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
                && nextMove[1] >= col) {
            Scanner userInput = new Scanner(System.in);
            System.out.print("käik (rida, veerg) või käsk: ");
            nextInput = userInput.nextLine();

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
        System.out.println("Mäng läbi! Kokku tehti " + counter
                + " kaevamist.\n" + "Kas soovid veel mängida?");
        String answer = newInput.nextLine().toLowerCase();
        return (answer.startsWith("j") || answer.startsWith("y"));
    }



}

