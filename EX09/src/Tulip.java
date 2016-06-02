/**
 * @author rerakk
 */
public class Tulip extends Flower {

    /**
     * Tulips colour.
     */
    private String colour;

    /**
     * 95 percent.
     */
    private final double percentage = 0.90;

    /**
     * Minimum required amount for discount.
     */
    private final int minimumDiscount = 5;

    /**
     * Constructor method.
     * @param colourChoice flowers colour.
     */
    public Tulip(String colourChoice) {
        if (colourChoice != null) {
            colour = colourChoice;
        }
    }

    /**
     * Constructor method.
     * @param newPrice price of the tulip.
     * @param colourChoice colour of the tulip.
     */
    public Tulip(double newPrice, String colourChoice) {
        super.setPrice(newPrice);
        colour = colourChoice;
    }

    /**
     * @return colour of the tulip.
     */
    public final String getColour() {
        return colour;
    }

    /**
     * Calculate the price of a single tulip
     * considering the amount of tulips given
     * as the parameter.
     * @param amount amount of tulips.
     * @return price of a single flower.
     */
    public final double getPrice(int amount) {
        double value = 0.0;
        if (Tulip.super.getPrice() < 0) {
            return value;
        }
        if (amount >= minimumDiscount) {
            value = Tulip.super.getPrice() * percentage;
            return value;
        }
        if (amount > 0) {
            return Tulip.super.getPrice();
        }
        return value;
    }
}
