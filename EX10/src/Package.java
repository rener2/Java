/**
 * Package class.
 */
public class Package {
    /**
     * Package number printed on page.
     */
    protected String packageNumber;
    /**
     * Package width in cm.
     */
    protected int width;
    /**
     * Package height in cm.
     */
    protected int height;
    /**
     * Package sender.
     */
    protected Customer sender;
    /**
     * Package receiver.
     */
    protected Customer receiver;

    /**
     * Get receiver.
     *
     * @return Receiver customer
     */
    public Customer getReceiver() {
        return receiver;
    }

    /**
     * Package constructor without arguments.
     */
    public Package() {
    }

    /**
     * Package constructor.
     *
     * @param packageNumber Package number printed on package
     * @param width         Package width in cm
     * @param height        Package height in cm
     */
    public Package(String packageNumber, int width, int height) {
        this.packageNumber = packageNumber;
        this.width = width;
        this.height = height;
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
