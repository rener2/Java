/**
 * @author rerakk
 */
public class SoldierOfFortune {

    /**
     * Program logic.
     */
    public SoldierOfFortune() {

    }

    /**
     * First name of the soldier.
     */
    private String firstName;

    /**
     * Last name of the soldier.
     */
    private String lastName;

    /**
     * Code name of the soldier.
     */
    private String codeName;

    /**
     * Amount of completed missions.
     */
    private int numberOfMissionsCompleted;

    /**
     * Set the given parameter as the soldiers first name.
     * @param name given name
     */
    public final void setFirstName(String name) {
        if (name != null) {
        firstName = name;
        }
    }

    /**
     * Set the given parameter as the soldiers code name.
     * @param name given name
     */
    public final void setCodeName(String name) {
        if (name != null) {
            codeName = name;
        }
    }

    /**
     * Set the given parameter as the soldiers last name.
     * @param name given name
     */
    public final void setLastName(String name) {
        if (name != null) {
            lastName = name;
        }
    }

    /**
     * find the first name of the soldier.
     * @return first name of the soldier.
     */
    public final String getFirstName() {
        return firstName;
    }

    /**
     * find the code name of the soldier.
     * @return code name of the soldier.
     */
    public final String getCodeName() {
        return codeName;
    }

    /**
     * find the last name of the soldier.
     * @return last name of the soldier.
     */
    public final String getLastName() {

        return lastName;
    }

    /**
     * Turn soldiers first name, code name and last name
     * to a string, put the code name in between quotation
     * marks.
     * @return the result.
     */
    public final String toString() {
        if (firstName == null && codeName == null && lastName == null) {
            return "";
        }
        String result = firstName + " \"" + codeName + "\" " + lastName;
        return result;
    }

    /**
     * Find the number of missions the soldier
     * has completed.
     * @return the amount.
     */
    public final int getNumberOfMissionsCompleted() {
        return numberOfMissionsCompleted;
    }

    /**
     * Set the given amount as the
     * number of missions the soldier has
     * completed.
     * @param amount the amount of missions.
     */
    public final void setNumberOfMissionsCompleted(int amount) {
        numberOfMissionsCompleted = amount;
    }

}
