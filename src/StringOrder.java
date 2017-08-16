import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by me on 15.08.17.
 */
public class StringOrder {

    public static String order(String words) {
        return Stream.of(words.split(" "))
                .sorted(Comparator.comparing(p -> Integer.valueOf(p.replaceAll("\\D", ""))))
                .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        System.out.println(order("is2 Thi1s T4est 3a"));
    }

}
