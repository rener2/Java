import java.util.ArrayList;
import java.util.List;

/**
 * Class friend.
 */
public class Friend {

    /**
     * Full name of the object.
     */
    private String fullName;

    /**
     * Program logic.
     * @param args for console.
     */
    public static void main(String[] args) {

    }

    /**
     * Get all the names of the object
     * except for the last name.
     *
     * @return list of found names.
     */
    public final List<String> getNames() {
        List<String> names = new ArrayList<>();
        String[] firstNames = fullName.replaceAll(" ", ",").split(",");
        for (int i = 0; i + 1 < firstNames.length; i++) {
            names.add(firstNames[i]);
        }
        return names;
    }

    /**
     * Set fullName to the object.
     * @param name given name to be set as fullName.
     */
    public final void setFullName(String name) {
        fullName = name;
    }

    /**
     * Find the objects last name and return it.
     *
     * @return Objects lastName.
     */
    public final String getLastName() {
        String[] names = fullName.trim().split("\\s+");
        return   names[names.length - 1];
    }

    /** Get objects full name and return it.
     *
     *  @return Full name of the object.
     */
    public final String getFullName() {
        return fullName;
    }
}


