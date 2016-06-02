import java.util.ArrayList;
import java.util.List;

/**
 * @author rerakk
 */
public class Team {

    /**
     * Constructor of the class.
     */
    public Team() {
    }

    /**
     * Code name of the team.
     */
    private String codeName;

    /**
     * List of the teams members.
     */
    private List<SoldierOfFortune> members = new ArrayList<>();

    /**
     * List of completed missions for the current object.
     */
    private List<Mission> completedMissions = new ArrayList<>();

    /**
     * Set given list values as members of the team.
     * @param newMembers new members to be set.
     */
    public final void setMembers(List<SoldierOfFortune> newMembers) {
        members.clear();
        for (SoldierOfFortune soldier : newMembers) {
            if (!members.contains(soldier)) {
                members.add(soldier);
            }
        }


    }

    /**
     * Find and the members of the team.
     * @return the members as a list.
     */
    public final List<SoldierOfFortune> getMembers() {
        return members;
    }

    /**
     * Set the given parameter as the teams
     * code name.
     * @param name given code name.
     */
    public final void setCodeName(String name) {
        codeName = name;
    }

    /**
     * Find the code name of the team.
     * @return code name of the soldier.
     */
    public final String getCodeName() {
        return codeName;
    }

    /**
     * Add the given soldier to the
     * list members.
     * @param soldier given soldier.
     */
    public final void addSoldierToTeam(SoldierOfFortune soldier) {
        if (soldier != null) {
            if (!members.contains(soldier)) {
                members.add(soldier);
            }
        }
    }

    /**
     * Turn the teams code name and
     * members to a string value.
     * @return the string value.
     */
    public final String toString() {
        String result = "";
        if (codeName == null && members.isEmpty()) {
            return null;
        }
        if (codeName != null) {
            result = codeName + ": ";
        }
        if (!members.isEmpty()) {
            for (SoldierOfFortune soldier : members) {
                if (soldier != null) {
                    result += soldier.getCodeName() + ", ";
                }
            }
            return result.substring(0, result.length() - 2);
        }
        return result;
    }

    /**
     * Find the amount of objects in
     * list members.
     * @return the value.
     */
    public final int getNumberOfSoldiers() {
        int count = 0;
        for (SoldierOfFortune soldier : members) {
            if (soldier != null) {
                count += 1;
            }
        }
       return count;
    }

    /**
     * Find the average amount of missions completed
     * for the whole team. If the team has no members
     * return 0.0 .
     * @return average value of missions completed for the team.
     */
    public final double averageMissionsCompleted() {
        if (members.isEmpty()) {
            return 0;
        }
        int soldierCount = 0;
        double amount = 0.0;
        for (SoldierOfFortune soldier: members) {
            if (soldier != null) {
                double missionCount = (double) soldier.getNumberOfMissionsCompleted();
                if (missionCount != 0.0) {
                    amount += missionCount;
                }
                soldierCount++;
            }
        }
        return amount / soldierCount;

    }

    /**
     * Call out mission method receiveTeam for this
     * current object, if result is true, then add
     * mission object to completedMissions list.
     * @param mission given mission object.
     * @return boolean whether the mission was added to the list.
     */
    public final boolean sendToMission(Mission mission) {
        if (mission == null || Team.this.getNumberOfSoldiers() == 0) {
            return false;
        }
        if (mission.receiveTeam(Team.this)) {
            completedMissions.add(mission);
            for (SoldierOfFortune soldier : members) {
                if (soldier != null) {
                    soldier.setNumberOfMissionsCompleted(soldier.getNumberOfMissionsCompleted() + 1);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * @return list completedMissions.
     */
    public final List<Mission> getCompletedMissions() {
        return completedMissions;
    }


}



