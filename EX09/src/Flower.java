/**
 * @author rerajj
 */
public class Flower {

    /**
     * Price of the given object.
     */
    private double price;

    /**
     * Empty constructor.
     */
    public Flower() {

    }

    /**
     * Constructor method with a
     * parameter.
     * @param newPrice New price of the flower.
     */
    public Flower(double newPrice) {
        if (newPrice > 0) {
            price = newPrice;
        }
    }

    /**
     * Set parameter as the new
     * price of the object.
     * @param amount new price value.
     */
    public final void setPrice(double amount) {
        this.price = amount;
    }

    /**
     * @return price of the given object.
     */
    public double getPrice() {
        return price;
    }



}
