import java.util.List;

/**
 * PackageProvider interface.
 */
public interface PackageProvider {

    /**
     * Get next package in the queue.
     *
     * @return Next package
     */
    Package getNextPackage();

    /**
     * Add new package to queue.
     *
     * @param packageToAdd A new package to add
     */
    void addPackage(Package packageToAdd);

    /**
     * Returns whether the provider hax next package or not.
     *
     * @return next package exists
     */
    boolean hasNextPackage();

    /**
     * Set package filter to queue.
     *
     * @param packageFilter PackageFilter
     */
    void setPackageFilter(PackageFilter packageFilter);

    /**
     * Get package filter.
     *
     * @return packageFilter
     */
    PackageFilter getPackageFilter();

    /**
     * Get packages as list (not ordered).
     *
     * @return Package list
     */
    List<Package> getPackages();

    /**
     * Get all sender packages (not ordered).
     *
     * @param customer Sender customer
     * @return Ordered list of sender packages
     */
    List<Package> findAllPackagesBySender(Customer customer);

    /**
     * Get all reciever packages (not ordered).
     *
     * @param customer Receiver customer
     * @return Ordered list of receiver packages
     */
    List<Package> findAllPackagesByReceiver(Customer customer);
}

