/**
 * Package filtering interface.
 */
public interface PackageFilter {

    /**
     * Validate package information.
     *
     * @param p Package to validate.
     * @return Whether the package information is correct.
     */
    boolean isValid(Package p);

    /**
     * Get broken package provider.
     * Broken package provider includes all
     *
     * @return BrokenPackageProvider
     */
    PackageProvider getBrokenPackageProvider();

}
