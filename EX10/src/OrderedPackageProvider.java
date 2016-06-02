import java.util.ArrayList;
import java.util.List;

/**
 * @author rerakk
 */
public class OrderedPackageProvider implements PackageProvider {

    /**
     * Package filter.
     */
    private PackageFilter packageFilter;

    /**
     * List of packages.
     */
    private List<Package> packages = new ArrayList<>();



    @Override
    public Package getNextPackage() {
        if (packages.size() == 0) return null;
        Package result = packages.get(0);
        //result.sender.setPriority(0);
        //result.receiver.setPriority(0);
        int previousPriority = 0;
        int currentPriority;
        for (Package pakage: packages) {
            //p (s:12 r:10)
            //p (s:10 r:12)
            //pp 10 (s:12 r:10)
            if (pakage instanceof PremiumPackage) {

                currentPriority = ((PremiumPackage) pakage).getPriority();

                if (currentPriority >= previousPriority) {
                    String random = "x";
                    if (currentPriority == previousPriority) {

                        if (result.receiver.getPriority() + result.sender.getPriority() > pakage.receiver.getPriority()
                                + pakage.sender.getPriority()) {
                            random = "s";
                        } else {
                            result = pakage;
                        }

                    } else {
                        result = pakage;
                        previousPriority = currentPriority;
                    }
                }
            } else if (previousPriority == 0) {
                if (result.receiver.getPriority() + result.sender.getPriority() > pakage.receiver.getPriority()
                        + pakage.sender.getPriority()) {
                    int randomPa = 1;
                } else {
                    result = pakage;
                }
            }

        }
        packages.remove(result);
        return result;
    }

    @Override
    public void addPackage(Package packageToAdd) {
        if (!packages.contains(packageToAdd)) {
            packages.add(packageToAdd);
        }

    }

    @Override
    public boolean hasNextPackage() {
        if (packages.size() > 0) return true;
        return false;
    }

    @Override
    public void setPackageFilter(PackageFilter packageFilter) {
        this.packageFilter = packageFilter;
    }

    @Override
    public PackageFilter getPackageFilter() {
        return packageFilter;
    }

    @Override
    public List<Package> getPackages() {
        return packages;
    }

    @Override
    public List<Package> findAllPackagesBySender(Customer customer) {
        List<Package> result = new ArrayList<>();
        for (Package pakage: packages) {
            if (pakage.getSender().equals(customer)) {
                result.add(pakage);
            }
        }
        return result;
    }

    @Override
    public List<Package> findAllPackagesByReceiver(Customer customer) {
        List<Package> result = new ArrayList<>();
        for (Package pakage: packages) {
            if (pakage.getReceiver().equals(customer)) {
                result.add(pakage);
            }
        }
        return result;
    }
}
