/**
 * Configuration for HW02 Droptris.
 */
public class Configuration {
    /**
     * Game level (indicates which pieces are used).
     */
    private int level;
    /**
     * How many pieces ahead are visible.
     */
    private int lookahead;
    /**
     * Seed number (for random).
     */
    private int seed;
    /**
     * Student uni-id.
     */
    private final String uniid = "rerakk";

    /**
     * Default constructor.
     * @param level Level to play.
     * @param lookahead Lookahead to use.
     * @param seed Seed to use.
     */
    public Configuration(int level, int lookahead, int seed) {
        this.level = level;
        this.lookahead = lookahead;
        this.seed = seed;
    }

    @Override
    public String toString() {
        return "{\"uniid\": \"mati.gaal\",\n\"seed\": " + seed
                + "\n\"level\": " + level + "\n\"lookahead\": " + lookahead + "\n}";
    }
}
