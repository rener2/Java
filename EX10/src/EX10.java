/**
 * @author rerakk
 */
public class EX10 {
    public static void main(String[] args) {
        OrderedPackageProvider pp = new OrderedPackageProvider();

        Customer c1 = new Customer(12, "kati", "põdra");
        Customer c2 = new Customer(10, "mati", "metsa");
        Customer c3 = new Customer(12, "vati", "villa");
// notation in the name:
// s: priority of the sender, r: priority of the receiver
        Package p1 = new Package("p (s:12 r:10)", 100, 100);
        p1.setSender(c1);
        p1.setReceiver(c2);
        Package p2 = new Package("p (s:10 r:12)", 100, 120);
        p2.setSender(c2);
        p2.setReceiver(c1);
// notation in the name:
// pp priority s: priority of the sender, r: priority of the receiver
        PremiumPackage pp1 = new PremiumPackage(10, "pp 10 (s:12 r:10)", 100, 100);
        PremiumPackage pp2 = new PremiumPackage(10, "pp 10 (s:12 r:12)", 100, 100);
        PremiumPackage pp3 = new PremiumPackage(12, "pp 12 (s:12 r:12)", 100, 100);

        pp1.setSender(c1);
        pp1.setReceiver(c2);

        pp2.setSender(c1);
        pp2.setReceiver(c3);

        pp3.setSender(c1);
        pp3.setReceiver(c3);

        pp.addPackage(p1);
        pp.addPackage(p2);
        pp.addPackage(pp2);
        pp.addPackage(pp1);
        pp.addPackage(pp3);
// we use lambda expression
        pp.getPackages().forEach(p -> System.out.println(p.getPackageNumber()));
// if Package.toString is implemented to print out package number, we could use:
// pp.getPackages().forEach(System.out::println);

// the above is the same as:
/*
for (Package p : pp.getPackages()) {
    System.out.println(p.getPackageNumber());
}
*/
// take the next (first) package
        pp.getNextPackage();
// 4 remains:
        System.out.println(pp.getPackages().size());
        pp.getPackages().forEach(p -> System.out.println(p.getPackageNumber()));

// add a new package
        PremiumPackage pp4 = new PremiumPackage(13, "pp 13 (s:12 r:12)", 100, 100);
        pp4.setSender(c1);
        pp4.setReceiver(c3);
        pp.addPackage(pp4);
        System.out.println("After adding a new one:");
        pp.getPackages().forEach(p -> System.out.println(p.getPackageNumber()));

// take 4 more packages
        pp.getNextPackage();
        pp.getNextPackage();
        pp.getNextPackage();
        pp.getNextPackage();

// do we have more?
        System.out.println(pp.hasNextPackage()); // do we?
        System.out.println(pp.getNextPackage()); // ok

// do we still have more?
        System.out.println(pp.hasNextPackage()); // NO!
        System.out.println(pp.getNextPackage()); // no more -> null
    }
}