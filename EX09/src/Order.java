import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rerakk
 */

/**
 * Object order.
 */
public class Order {

    /**
     * To keep count of order number.
     */
    private static Integer count = 1;

    /**
     * Current orders number.
     */
    private Integer orderNumber = 0;

    /**
     * List of different flowers.
     */
    private List<Flower> flowers = new ArrayList<>();

    /**
     * Clients name.
     */
    private String client;

    /**
     * For precision.
     */
    private final int precision = 100;

    /**
     * For decimal points.
     */
    private final double decimal = 100.00;

    /**
     * Clients address.
     */
    private String address;

    /**
     * Constructor method.
     * @param clientsName who is the receiver.
     * @param destination where the flowers are being sent.
     */
    public Order(String clientsName, String destination) {
        client = clientsName;
        address = destination;
    }

    /**
     * @return clients name.
     */
    public final String getClient() {
        return client;
    }

    /**
     * @return clients address.
     */
    public final String getAddress() {
        return address;
    }

    /**
     * Add given flower to flowers list.
     * @param flower given object to be added.
     * @return if it was successful.
     */
    public final boolean add(Flower flower) {
        if (flower != null && flower.getPrice() >= 0) {
                flowers.add(flower);
            return true;
        }
        return false;
    }

    /**
     * @return total price of flowers.
     */
    public final double getTotalPrice() {
        int tulips = 0;
        int roses = 0;
        //int fairTradeRoses = 0;
        for (Flower flower: flowers) {
            if (flower instanceof Tulip) {
                if (flower.getPrice() >= 0) {
                    tulips++;
                }
            } else if (flower instanceof Rose && !(flower instanceof FairTradeRose)) {
                if (flower.getPrice() >= 0) {
                    roses++;
                }
            }
        }
        double amount = 0;
        for (Flower flower: flowers) {
            if (flower != null) {
                if (flower instanceof Tulip) {
                    if (flower.getPrice() >= 0) {
                        amount += ((Tulip) flower).getPrice(tulips);
                    }
                } else if (flower instanceof Rose && !(flower instanceof FairTradeRose)) {
                    if (flower.getPrice() >= 0) {
                        amount += ((Rose) flower).getPrice(roses);
                    }
                } else if (flower instanceof FairTradeRose) {
                        if (flower.getPrice() >= 0) {
                            amount += ((FairTradeRose) flower).getPrice();
                        }
                }
            }
        }
        return Math.floor(amount * precision) / decimal;
    }

    /**
     * Compile a check of the flowers in
     * the list and return it in string form.
     * @return check.
     */
    public final String pay() {
        int roseCount = 0;
        int tulipCount = 0;
        int fairTradeRoseCount = 0;
        for (Flower flower: flowers) {
            if (flower != null) {
                if (flower instanceof Rose && !(flower instanceof FairTradeRose)) {
                    roseCount++;

                } else if (flower instanceof Tulip) {
                    tulipCount++;

                } else if (flower instanceof FairTradeRose) {
                    fairTradeRoseCount++;

                }
            }
        }
        orderNumber = getNextOrderNumber();
        String check = "Order: " + getOrderNumber() + "\nClient: " + this.getClient() + "\nAddress: "
                + this.getAddress();
        if (fairTradeRoseCount > 0) {
            check += "\nFairTradeRoses: " + fairTradeRoseCount;
        }
        if (roseCount > 0) {
            check += "\nRoses: " + roseCount;
        }
        if (tulipCount > 0) {
            check += "\nTulips: " + tulipCount;
        }
        //check += "\n" + String.format("%.2f", getTotalPrice()).replaceAll(",", ".") + "€";
        DecimalFormat df = new DecimalFormat("0.00");
        String b = df.format(getTotalPrice()).replace(",", ".");
        check += "\n" + df.format(getTotalPrice()).replace(",", ".") + "€";
        return check;
    }

    /**
     * @return order number.
     */
    public final int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Change order number.
     * @return new order number.
     */
    public static int getNextOrderNumber() {
        count++;
        return count - 1;
    }

    /**
     * Find the customer who buys
     * most FairTradeRoses and return the name
     * of the client.
     * @param orders list of orders.
     * @return clients name.
     */
    public static String findTheMostCaringCustomer(List<Order> orders) {
        String clients = "";
        if (orders == null) {
            return clients;
        }
        int previousHowMany = 1;
        int howMany;
        for (Order order: orders) {
            howMany = 0;
            if (order != null) {
                for (Flower flower : order.flowers) {
                    if (flower instanceof FairTradeRose) {
                        howMany++;
                    }
                }
                if (order.getClient() != null) {
                    if (howMany > previousHowMany) {
                        previousHowMany = howMany;
                        clients = order.getClient() + "\n";
                    } else if (howMany == previousHowMany) {
                        clients += order.getClient() + "\n";
                    }
                }
            }
        }
        if (clients.equals("")) {
            return clients;
        }
        return clients.substring(0, clients.length() - 1);
    }
}
