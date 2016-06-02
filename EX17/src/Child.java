import java.util.ArrayList;
import java.util.List;

/**
 * Child class.
 */
public class Child {

    public Child(String name) {
        name = this.name;
    }



    private String name;
    private static List<Child> children = new ArrayList<Child>();
    private static int number = 0;
    private static List<String> names = new ArrayList<>();



    public static List<String> getSandbox(Child c) {
       // if (number <= children.size()) {
        //    number ++;
        if (names.size() < 3) {
            names.add(children.get(0).name); //['kati','mati']
            return getSandbox(c.children.get(0));
        }
       // }
        //System.out.println(c.children.get(0));

        return names;
    }

    public void playsWith(Child... children) {
        for (Child c : children) {
            c.children.add(c);
        }
    }
}
