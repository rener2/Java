/**
 * @author rerakk
 */
public class Rose extends Flower {

    /**
     * Thorns of the given object.
     */
    private boolean thorns;

    /**
     * 95 percent.
     */
    private final double percentage = 0.95;

    /**
     * Minimum required amount for discount.
     */
    private final int minimumDiscount = 3;

    /**
     * Empty constructor.
     */
    public Rose() {
    }

    /**
     * Constructor method with 2
     * parameters.
     * @param newPrice New price of the flower.
     * @param assignThorns If the flower has thorns or not.
     */
    public Rose(double newPrice, boolean assignThorns) {
        super.setPrice(newPrice);
        thorns = assignThorns;
    }

    /**
     * Set given parameter as new thorn value.
     * @param thornValue boolean if the flower has thorns or not.
     */
    public final void setThorns(boolean thornValue) {
        thorns = thornValue;
    }

    /**
     * @return If the object has thorns or not.
     */
    public final boolean hasThorns() {
        return thorns;
    }

    /**
     * Return price of the given object.
     * @param amount amount of roses.
     * @return price of a single rose.
     */
    public double getPrice(int amount) {
        double value = 0;
        if (Rose.super.getPrice() < 0) {
            return value;
        }
        if (amount >= minimumDiscount) {
            value = Rose.super.getPrice() * percentage;
            return value;
        }
        if (amount > 0) {
            return Rose.super.getPrice();
        }
        return value;
    }
}
