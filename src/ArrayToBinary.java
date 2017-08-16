import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by me on 15.08.17.
 */
public class ArrayToBinary {

    public static int ConvertBinaryArrayToInt(List<Integer> binary) {
        // Your Code

        AtomicInteger j = new AtomicInteger(0);
        Collections.reverse(binary);
        return binary.stream().mapToInt(i -> (i * (int) Math.pow(2 , j.getAndIncrement()))).sum();

    }

    public static void main(String[] args) {
        System.out.println("0110:" + ConvertBinaryArrayToInt(Arrays.asList(0, 1, 1, 0)));
    }

}
