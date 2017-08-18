import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Created by me on 17.08.17.
 */
public class Fold {

    public static int[] foldArray(int[] array, int runs)
    {
        System.out.println(Arrays.toString(array));
        boolean odd = array.length % 2 == 0;
        int middle = array.length / 2 - (odd ? 1 : 0);
        int[] result = new int[middle + 1];
        for (int i = 0; i <= middle; i++)
            result[i] =  (i != middle || odd) ? array[i] + array[array.length - i - 1] : array[i];
        return runs > 0 ? foldArray(result, runs -1) : array;
    }

    public static void main(String[] args) {
        int[] data = new int[] { 1, 2, 3, 4, 5 };
        for (int i : foldArray(data, 3)) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }
}
