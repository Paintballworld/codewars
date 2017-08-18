import java.util.*;

class DoubleLinear {

    private static Set<Integer> buffer = new TreeSet<>();
    private static Queue<Integer> keys = new PriorityQueue<>(Comparator.naturalOrder());
    private static int lastIndex = 0;

    static {
        buffer.add(1);
        keys.add(1);
    }

    public static int dblLinear (int n) {
        while (lastIndex < n) {
            lastIndex++;
            int lastValue = keys.poll();
            int x = 2 * lastValue + 1;
            int y = 3 * lastValue + 1;
            if (!keys.contains(x))
                keys.offer(x);
            if (!keys.contains(y))
                keys.offer(y);
            buffer.add(x);
            buffer.add(y);
        }
        return getFromBuffer(n);
    }

    private static int getFromBuffer(int n) {
        return new ArrayList<>(buffer).get(n);
    }

    public static void main(String[] args) {
        long start= System.currentTimeMillis();
        System.out.println(dblLinear(10));
        System.out.println(dblLinear(20));
        System.out.println(dblLinear(30));
        System.out.println(dblLinear(50));
        System.out.println("t:" + (System.currentTimeMillis() - start));

    }

}