import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 16.08.17.
 */
public class TestThings {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("first");
        System.out.println(set.add("first"));
        System.out.println(set.add("second"));
    }
}
