import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author rerakk
 */
public class EX07 {

    /**
     * List of Friend objects.
     */
    private static List<Friend> friends = new ArrayList<>();

    /**
     * Program logic.
     *
     * @param args for console.
     */
    public static void main(String[] args) {
        // System.out.println("~".compareTo("b"));
         friends = readFriendsFromFile("example.txt");
         System.out.println(findFriendByLastName("Ain").getFullName()); // Ain Ain
         System.out.println(findFriendByLastName("Punn").getFullName()); // Baul Punn
         System.out.println(findFriendByLastName("Rukis").getFullName()); // Kaera Jaan Rukis
         System.out.println(findFriendByLastName("Tamm").getNames()); //[Drop, Table, User]
         System.out.println(findFriendByLastName("Kaal").getFullName()); //null
          System.out.println(findFriendByLastName("aasmaa").getFullName()); // mat Kaal
          System.out.println(findFriendByLastName("Baggins").getFullName());
    }

    /**
     * Read all names from fileName, make
     * every name into an Friend object and
     * add every object to friends list.
     *
     * @param inputFileName input fileName, contains all of the names.
     * @return List of Friend objects.
     */
    public static List<Friend> readFriendsFromFile(String inputFileName) {
        List<Friend> friends1 = new ArrayList<>();
        if (inputFileName == null) {
            return null;
        }
        try {
            Path path = Paths.get(inputFileName);
            Scanner input = new Scanner(path);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (line.length() != line.replaceAll(" ", "").length()) {
                    line = line.replaceAll(" +", " ").trim();
                    Friend newMember = new Friend();
                    newMember.setFullName(line);
                    friends1.add(newMember);
                }
            }
        } catch (IOException e) {
            return null;
        }
        friends = friends1;
        return friends;
    }

    /**
     * Find the name with given
     * lastName value.
     *
     * @param lastName last name.
     * @return full name for the last name.
     */
    public static Friend findFriendByLastName(String lastName) {
        String resultName = "~";

        Friend resultFriend = null;

        for (Friend friend : friends) {
            if (friend.getLastName().equals(lastName)) {

                String newName = String.join(" ", friend.getNames()).toLowerCase();

                if (newName.compareTo(resultName) < 0) {
                    resultFriend = friend;
                    resultName = newName;
                    }
                }
            }
        return resultFriend;
    }
}
