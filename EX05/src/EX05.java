import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Fifth homework.
 */
public class EX05 {

    /**
     * Required length for the array.
     */
    private static final int LENGTH = 4;

    /**
     * Last index in list.
     */
    private static final int LAST_INDEX = 3;


    /**
     * For testing.
     * @param args for console.
     */
    public static void main(String[] args) {
        System.out.println(convert("Movies.txt", "newMovies.txt"));
    }

    /**
     * Convert a text file with given name
     * to a new format string and write it
     * to a new text file with given name.
     *
     * @param inputFilename  name of input file.
     * @param outputFilename name of output file.
     * @return number of lines successfully converted.
     */
    public static int convert(String inputFilename, String outputFilename)  {

        int counter = 0;

        try {
            List<String> allLines = Files.readAllLines(Paths.get(inputFilename));
            PrintWriter writer = new PrintWriter(outputFilename);
            for (int i = 0; i < allLines.size(); i++) {
                String result = getNicelyFormattedMovie(allLines.get(i));

                if (result != null) {
                    if (counter > 0) {
                        result = "\n\n" + result;
                    }
                    writer.write(result);
                    counter++;
                }
            }
            writer.close();
        } catch (IOException e) {
            return 0;
        }
        return counter;
    }

    /**
     * Convert line of information into multi-line
     * better form of information.
     *
     * @param movieLine line that contains information
     *                  about given movie
     * @return formatted string.
     */
    public static String getNicelyFormattedMovie(String movieLine) {
        if (movieLine == null) {
            return null;
        }
        //if (!movieLine.matches("^[0-9-]*[\\|](.*)[\\|](.*)[\\|][0-9][.][0-9\\s]*$")) {
        //    return null;
       // }
        String[] moviesList = movieLine.split("\\|");
        if (moviesList.length != LENGTH) {
            return null;
        }
        String[] dateList = moviesList[0].split("-");
        String newDate = dateList[2] + "/" + dateList[1] + "/" + dateList[0];
        return  moviesList[1] + "\n"
                + "Release date: " + newDate + "\n"
                + "Description: " + moviesList[2] + "\n"
                + "Average rating: " + moviesList[LAST_INDEX];
    }
}
