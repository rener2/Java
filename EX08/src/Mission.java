/**
 * @author rerakk
 */
public class Mission {

    /**
     * Class constructor method.
     */
    public Mission() {
    }

    /**
     * Code name of the current object.
     */
    private String codeName;

    /**
     * Minimum team average requirement for the mission.
     */
    private int requiredMissionsCompleted;

    /**
     * Current missions status.
     */
    private boolean missionStatus = false;

    /**
     * Set the given parameter as the new
     * codeName of object.
     * @param name given String value.
     */
    public final void setCodeName(String name) {
        if (name != null) {
            codeName = name;
        }
    }

    /**
     * @return missionStatus.
     */
    public final boolean isCompleted() {
        return missionStatus;
    }

    /**
     * Set the given parameter value as the
     * new value for variable requiredMissionsCompleted.
     * @param completed given value.
     */
    public final void setRequiredMissionsCompleted(int completed) {
        requiredMissionsCompleted = completed;
    }

    /**
     * @return variable requiredMissionsCompleted.
     */
    public final int getRequiredMissionsCompleted() {
        return requiredMissionsCompleted;
    }

    /**
     * Check whether the object Team given as parameter
     * matches the current objects requirements. If so
     * then change missionStatus to true.
     * @param team given object.
     * @return whether parameter matches requirements of the object.
     */
    public final boolean receiveTeam(Team team) {
        if (team == null) {
            return false;
        }
        if (team.getNumberOfSoldiers() == 0) {
            return false;
        }
        if (team.averageMissionsCompleted() >= requiredMissionsCompleted) {
            missionStatus = true;
            return true;
        }
        return false;
    }

    /**
     * @return codeName;
     */
    public final String toString() {
        if (codeName == null) {
            return "Operation ";
        }
        return "Operation " + codeName;
    }

}
