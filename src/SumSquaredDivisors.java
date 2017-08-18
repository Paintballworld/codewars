import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Created by me on 18.08.17.
 */
public class SumSquaredDivisors {

    public static String listSquared(long m, long n) {
        return "[" + LongStream.rangeClosed(m, n)
                .boxed()
                .map(SumSquaredDivisors::pair)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(", ")) + "]";
    }

    private static String pair(long value) {
        long sum = getDividersSum(value);
        return isSquare(sum) ? String.format("[%d, %d]", value, sum) : null;
    }

    private static long getDividersSum(long value) {
        return LongStream.rangeClosed(1, value)
                .filter(a -> isDividerNatural(value, a))
                .map(SumSquaredDivisors::squared)
                .sum();
    }

    private static long squared(long value) {
        return value * value;
    }

    private static boolean isDividerNatural(long a, long b) {
        return isNatural((double) a / b);
    }

    private static boolean isNatural(double value){
        return value == Math.round(value);
    }

    private static boolean isSquare(long value) {
        return Math.sqrt(value) == Math.round(Math.sqrt(value));
    }

    public static void main(String[] args) {
        System.out.println(listSquared(1, 250));

    }

}
