/**
* Public class.
*/
public class EX16 {

    /**
     * First value, what we are looking for.
     */
    public static final int FIRSTNUMBBER = 8;
    /**
     * Second value, what we are looking for.
     */
    public static final int SECONDNUMBBER = 9;
    /**
     * Second value, what we are looking for.
     */
    public static final int FLOORVALUE = 10;



    /**
     * Find how many times certain values occur in a
     * integer.
     * @param n Integer what we are looking at.
     * @return how many times the values occur.
     */
    public static int count98(int n) {
        int lastNum = Math.floorMod(n, FLOORVALUE);
        int nextNum = n / FLOORVALUE;
        if (n == 0) {
            return 0;
        }
        if (lastNum == FIRSTNUMBBER || lastNum == SECONDNUMBBER) return 1 + count98(nextNum);
        else {
            return count98(nextNum);
        }


    }

    /**
     * Main class.
     * @param args for console commands.
     */
    public static void main(String[] args) {
        //System.out.println(count98(811111118)); // => 3
        //System.out.println(count98(818));
        //TEST YOUR IMPLEMENTATION HERE
    }
}
