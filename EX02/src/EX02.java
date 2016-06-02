public class EX02 {

    /**
     * Constant.
     * Every 3 days, feed worms.
     */
    public static final int WORM_FEEDING_DAY = 3;

    /**
     * Constant.
     * Every 5 days, bathe in sand.
     */
    public static final int BATHING_DAY = 5;

    /**
     * Constant.
     * Total number of days for which instructions are needed.
     */
    public static final int NUMBER_OF_DAYS = 30;

    /**
     * Entry point of the program.
     * @param args Arguments from command line.
     */
    public static void main(String[] args) {
        for (int currentDay = 1; currentDay <= NUMBER_OF_DAYS; currentDay++) {
            System.out.println(getInstructionForCurrentDay(currentDay));
            if(currentDay < 1) {
                break;
            }
        }
    }
    /**
     * Return instruction for given day.
     * @param currentDay number of day to print instructions for.
     */
    public static String getInstructionForCurrentDay(int currentDay) {
        if (currentDay < 1) {
            return "Can't fly back in time";
        } else {
            if (currentDay % WORM_FEEDING_DAY  == 0 && currentDay % BATHING_DAY != 0) {
                return "Day " + currentDay + " : " + "feed worms";
            } else if (currentDay % BATHING_DAY == 0 && currentDay % WORM_FEEDING_DAY  != 0) {
                return "Day " + currentDay + " : " + "bathe in sand";
            } else if (currentDay % WORM_FEEDING_DAY  == 0 && currentDay % BATHING_DAY == 0) {
                return "Day " + currentDay + " : " + "glide in wind";
            } else {
                return "Day " + currentDay + " : " + "give fruit and water";
            }
    }
}
}