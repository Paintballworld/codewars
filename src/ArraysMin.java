import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Created by me on 19.08.17.
 */
public class ArraysMin {

    public static int findSmallest(final int[] numbers, final String toReturn ) {
        System.out.println(Arrays.toString(numbers));
        AtomicInteger i = new AtomicInteger(0);
        Map<Integer, Integer> indexMap = new HashMap<>();
        int min = IntStream
                .of(numbers)
                .boxed()
                .filter(p -> indexMap.putIfAbsent(p, i.getAndIncrement()) == null)
                .min(Comparator.naturalOrder())
                .get();
        return toReturn.equals("index") ? indexMap.get(min) : min;
    }

    public static void main(String[] args) {
//        System.out.println(findSmallest( new int [] {1, 2, 3} , "index") );
//        System.out.println(findSmallest( new int [] {7, 12, 3, 2, 27} , "value") );
        System.out.println(findSmallest(new int[]{0, 11, 0, 37, 53, 24, 10, 50, 84, 37, 73, 96, 4, 96, 89, 63, 96, 62, 30, 22, 1, 78, 61, 63, 64, 41, 72, 19, 93, 87, 31, 73, 51, 82, 26, 71, 12, 87, 34, 62, 61, 75, 53, 8, 41, 28, 73, 63, 35, 27, 91, 46, 47, 20, 17, 13, 72, 3, 44, 82, 38, 98, 6, 80, 98, 5, 89, 36, 77, 91, 16, 85, 75, 61, 54, 44, 52, 87, 84, 59, 30, 91, 86, 52, 89, 90, 96, 73, 20, 78, 21, 84}, "index"));
    }

}



