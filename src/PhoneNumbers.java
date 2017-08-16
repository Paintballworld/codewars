import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by me on 15.08.17.
 */
public class PhoneNumbers {

    public static String createPhoneNumber(int[] numbers) {
        String pattern = "(###) ### ####";
        for (int number : numbers) {
            pattern = pattern.replaceFirst("#", number + "");
        }
        return pattern;
    }

    public static void main(String[] args) {
        System.out.println(createPhoneNumber(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}));
    }
}
