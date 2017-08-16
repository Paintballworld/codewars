import java.util.stream.Stream;

/**
 * Created by me on 16.08.17.
 */
public class DigitalRoot {
    public static int digital_root(int n) {
        while (n > 9) {
            n = Stream.of((n + "").split("")).mapToInt(Integer::valueOf).sum();
        }
        return n;
    }

    public static void main(String[] args) {
        System.out.println(digital_root(132189));

    }
}
