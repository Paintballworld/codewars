import java.util.stream.IntStream;

/**
 * Created by me on 15.08.17.
 */
public class NoFive {

    public static int dontGiveMeFive(int start, int end)
    {
        return (int) IntStream.rangeClosed(start, end )
                .filter(p -> !String.valueOf(p).contains("5"))
                .count();
    }

    public static void main(String[] args) {

        System.out.println();
        System.out.println(dontGiveMeFive(4, 17));
    }
}
