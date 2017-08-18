import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by me on 17.08.17.
 */
public class Vasya {

    public static String Tickets(int[] peopleInLine)
    {
        Money.clear();
        for (int i : peopleInLine) {
            if (!Money.pay(i)) return "NO";
        }
        return "YES";
    }

    public static void main(String[] args) {
//        System.out.println(Tickets(new int[] {25, 25, 50}));
        System.out.println(Tickets(new int []{25, 25, 50, 100, 25, 50, 100, 100, 25, 100}));
    }


}

class Money {
    private static int c25;
    private static int c50;

    public static void clear() {
        c25 = 0;
        c50 = 0;
    }

    static boolean pay(int value) {
        if (value == 25) return c25++ >= 0;
        if (value == 50) return c25 > 0 && c25-- >= 0 && ++c50 >= 0;
        if (value == 100) {
            if (c50 > 0) return c25 > 0 && c25-- >=0 && c50-- >=0;
            if (c25 > 1) return c25-- >= 0 && c25-- >= 0;
        }
        return false;
    }
}
