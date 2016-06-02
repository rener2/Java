/**
 * @author <rerakk>
 */
import java.util.Arrays;
import java.util.Scanner;

/**
 * EX04.
 */
public class EX04 {
    public static char[][] matrix;
    /** Guess parameter array index for guess X value*/
    public static final int FIELD_X = 0;
    /** Guess parameter array index for guess Y value*/
    public static final int FIELD_Y = 1;
    /** Settings parameter array index for matrix height value*/
    public static final int FIELD_MATRIX_HEIGHT = 0;
    /** Settings parameter array index for matrix wdith value */
    public static final int FIELD_MATRIX_WIDTH = 1;
    /** Settings parameter array index for matrix target x value */
    public static final int FIELD_TARGET_X = 2;
    /** Settings parameter array index for matrix target y value*/
    public static final int FIELD_TARGET_Y = 3;
    /** The count of settings parameters */
    public static final int INITIAL_PARAMETER_ARRAY_LENGTH = 4;
    /** The count of guess parameters */
    public static final int GUESS_ARRAY_LENGTH = 2;
    /** Maximum matrix dimension value */
    public static final int MAX_DIMENSION = 10;
    /** Precision for double checking */
    public static final double ERROR_BOUND = 0.001;

    /**
     * Entry point.
     * @param args commandline arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] matrixParameters = readInitialMatrixParameters(scanner);
        char[][] matrix = createMatrix(
                matrixParameters[FIELD_MATRIX_HEIGHT],
                matrixParameters[FIELD_MATRIX_WIDTH],
                matrixParameters[FIELD_TARGET_X],
                matrixParameters[FIELD_TARGET_Y]
        );

        int[] guessInput;
        int totalGuesses = 0;
        while (true) {
            System.out.println(getAsciiMatrix(matrix));
            guessInput = readGuessInput(scanner, matrixParameters);
            totalGuesses++;
            System.out.println("Katseid : " + totalGuesses);
            double distance = guess(matrix, guessInput[FIELD_X], guessInput[FIELD_Y]);
            if (distance < ERROR_BOUND) {
                System.out.println("Lahendus leitud " + totalGuesses + " katsega!");
                break;
            } else {
                System.out.printf("Kaugus mõeldud punktist %.2f\n", distance);
            }
        }
    }

    /**
     * Create 2D character array map, fill it
     * with empty characters represented
     * by ".". Also mark the x point on the
     * map.
     *
     * @param n  amount of columns
     * @param m  amount of rows
     * @param x  column of 'x'
     * @param y  row of 'x'
     * @return char [][] array matrix commandline arguments
     */
    public static char[][] createMatrix(int n, int m, int x, int y) {
        matrix = new char[n][m];
        for (char[] row: matrix) {
            Arrays.fill(row, '.');
        }
        matrix[x][y] = 'x';
        return matrix;
    }

    /**
     * Calculate the distance between
     * the user guessed point on the map
     * and the location of 'x'. Also mark
     * the user guessed spot on the map as '*'.
     *
     * @param matrix  char [][] array of the map
     * @param x       amount of rows
     * @param y       column of 'x'
     * @return double value of the distance
     */
    public static double guess(char[][] matrix, int x, int y) {
        if(matrix[x][y] == '.') {matrix[x][y] = '*';}
        int meantRow = 0;
        int meantColumn = 0;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 'x' || matrix[i][j] == 'c'){
                    meantRow = i;
                    meantColumn = j;
                }
            }
        }
        double columnLength = meantRow - x;
        double rowLength = meantColumn - y;
        double distance = Math.sqrt(columnLength * rowLength + columnLength * rowLength);
        return distance;
    }

    /**
     * Convert the given char [][] array
     * to its string form. Hide the original spot
     * 'x' on the map.
     *
     * @param matrix  char [][] array of the map
     * @return String form of matrix char [][] array
     */
    public static String getAsciiMatrix(char[][] matrix) {
        String result = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[0].length; k++) {
                if (matrix[i][k] == '*') {
                    result += matrix[i][k];
                } else {
                    result += '.';
                }
            } if (i != matrix.length - 1) {
                result += "\n";
            }
        }
        return result;
    }

    /**
     * Read user guess input from scanner.
     * Returned array structure:
     * X - x coordinate, Y - y coordinate
     * Index 0 - X, Index 1 - Y
     *
     * @param scanner          Input scanner
     * @param matrixParameters Array of user guess information
     * @return int[] array of guess input parameters
     */
    public static int[] readGuessInput(Scanner scanner, int[] matrixParameters) {
        int[] parameters;
        do {
            System.out.print("Sisestage mõeldav ruut (näiteks 0,0):");
            String parameterInput = scanner.nextLine();
            parameters = splitInputStructureToIntArray(parameterInput);
        } while (!validateGuessInput(parameters, matrixParameters));
        return parameters;
    }

    /**
     * Validate whether the guess input parameters are ok.
     * 1) Guessinput length must be 2 (x,y) coordinates
     * 2) (x,y) must be inside matrix that is specified by the matirxParameters
     *
     * @param guessInput       user guess input, consists of [0] - x [1] - y coordinates
     * @param matrixParameters game matrix parameters (look readInitialMatrixParameters for structure information)
     * @return whether the guess input is ok.
     */
    public static boolean validateGuessInput(int[] guessInput, int[] matrixParameters) {
        if (guessInput.length != GUESS_ARRAY_LENGTH) {
            return false;
        }
        for (int guessValue : guessInput) {
            if (guessValue < 0) {
                return false;
            }
        }
        return guessInput[FIELD_X] < matrixParameters[FIELD_MATRIX_HEIGHT]
                && guessInput[FIELD_Y] < matrixParameters[FIELD_MATRIX_WIDTH];
    }

    /**
     * Read matrix parameters from scanner.
     * returned array structure
     * N - Matrix height, M - matrix width
     * X - x value of target square, Y - y value of target square
     * index 0 - N value, index 1 - M value
     * index 2 - X value, index 3 - Y value
     *
     * @param scanner Input scanner
     * @return int[] - Array of user input parameters
     */
    public static int[] readInitialMatrixParameters(Scanner scanner) {
        int[] parameters;
        do {
            System.out.print("Sisestage N,M,X,Y (näiteks: 2,2,0,0):");
            String parameterInput = scanner.nextLine();
            parameters = splitInputStructureToIntArray(parameterInput);
        } while (!validateInitialParameters(parameters));

        return parameters;
    }

    /**
     * Converts input that is spearetd by commas to integer array.
     * If value is not convertable to int -1 is used.
     *
     * @param input input string to be parsed
     * @return int[] array of int values.
     */
    public static int[] splitInputStructureToIntArray(String input) {

        String[] splitParts = input.replaceAll(" ", "").split(",");
        int[] parameters = new int[splitParts.length];
        for (int i = 0; i < splitParts.length; i++) {
            try {
                parameters[i] = Integer.parseInt(splitParts[i]);
            } catch (NumberFormatException e) {
                parameters[i] = -1;
            }
        }
        return parameters;
    }

    /**
     * Check whether the input input parameters are ok.
     * We consider a parameter ok when its value is between 1 and 10(exlusive) and
     * X, Y are inside the matrix
     *
     * @param parameters Array of user matrix parameters.
     * @return boolean - whether the input parameters are ok.
     */
    public static boolean validateInitialParameters(int[] parameters) {
        if (parameters.length != INITIAL_PARAMETER_ARRAY_LENGTH) {
            return false;
        }
        for (int parameter : parameters) {
            if (parameter < 0 || parameter > MAX_DIMENSION) {
                return false;
            }
        }
        return parameters[FIELD_MATRIX_HEIGHT] > parameters[FIELD_TARGET_X]
                && parameters[FIELD_MATRIX_WIDTH] > parameters[FIELD_TARGET_Y];
    }
}
