import java.util.stream.IntStream;

/**
 * Created by me on 15.08.17.
 */
public class MulipliersSumm {

    public static int solution(int number) {
        return IntStream
                .range(1, number)
                .filter(p -> (p % 3 == 0) || (p % 5 == 0))
                .sum();
    }

    public static void main(String[] args) {
        System.out.println(solution(10));
    }
}
