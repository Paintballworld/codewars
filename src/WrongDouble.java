import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by me on 16.08.17.
 */
public class WrongDouble {

    public static double findUnique(double arr[]) {
        // Do the magic
        return Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey))
                .get(1L);
    }

    private static double precision = 0.0000000000001;

    public static void main(String[] args) {
        System.out.println(findUnique(new double[]{0, 1, 0})); // 1.0
        System.out.println(findUnique(new double[]{1, 1, 1, 2, 1, 1})); // 2.0
    }
}
