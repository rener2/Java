/**
 * @author rerakk
 */
public class PremiumPackage extends Package {

    /**
     * Package priority.
     */
    private int priority;
    /**
     * Package width in cm.
     */
    private int width;
    /**
     * Package height in cm.
     */
    private int height;
    /**
     * Package number printed on page.
     */
    private String packageNumber;
    /**
     * Package constructor without arguments.
     */
    public PremiumPackage() {
    }
    /**
     * Package constructor.
     *
     * @param packageNumber Package number printed on package
     * @param width         Package width in cm
     * @param height        Package height in cm
     * @param priority      Package priority
     */
    public PremiumPackage(int priority, String packageNumber, int width, int height) {
        this.packageNumber = packageNumber;
        this.height = height;
        this.width = width;
        this.priority = priority;

    }

    /**
     * @return package priority.
     */
    public int getPriority() {
        return priority;
    }
    /**
     * Set priority.
     *
     * @param priority priority
     */
    public void setPriority(int priority) {
        this.priority = priority;

    }


    /**
     * Set receiver.
     *
     * @param receiver Receiver customer
     */
    public void setReceiver(Customer receiver) {
        this.receiver = receiver;
    }

    /**
     * Get sender.
     *
     * @return Sender customer
     */
    public Customer getSender() {
        return sender;
    }

    /**
     * Set sender.
     *
     * @param sender Sender customer
     */
    public void setSender(Customer sender) {
        this.sender = sender;
    }

    /**
     * Get package number.
     *
     * @return Package number
     */
    public String getPackageNumber() {
        return packageNumber;
    }

    /**
     * Set package number.
     *
     * @param packageNumber Package number
     */
    public void setPackageNumber(String packageNumber) {
        this.packageNumber = packageNumber;
    }

    /**
     * Get package height.
     *
     * @return Package height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set package height.
     *
     * @param height Package height.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Get package width.
     *
     * @return Package width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get package width.
     *
     * @param width Package width.
     */
    public void setWidth(int width) {
        this.width = width;
    }
}
