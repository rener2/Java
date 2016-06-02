/**
 * @author rerakk
 */
public class FairTradeRose extends Rose {

    /**
     * Empty constructor.
     */
    public FairTradeRose() {
    }

    /**
     * Constructor method with 2
     * parameters.
     * @param newPrice New price of the flower.
     * @param assignThorns If the flower has thorns or not.
     */
    public FairTradeRose(double newPrice, boolean assignThorns) {
        super.setPrice(newPrice);
        super.setThorns(assignThorns);
    }

    /**
     * Find the price of given flower and
     * double it. return the value
     * @return calculated value.
     */
    public final double getPrice() {
        if (FairTradeRose.super.getPrice() < 0) {
            return Double.NaN;
        }
        return 2 * FairTradeRose.super.getPrice();
    }

    /**
     * Calculate the price of a single rose.
     * @param amount amount of roses
     * @return price of the given object.
     */
    public final double getPrice(int amount) {
        if (FairTradeRose.super.getPrice() * amount < 0) {
            return 0;
        }
        return (2 * FairTradeRose.super.getPrice());
    }
}
