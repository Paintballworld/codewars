package kyu4;

public class RectangleRotation {

    static int rectangleRotation(final int a, final int b) {
        int ha = (int) ((a / Math.sqrt(2)) + 1);
        int hb = (int) ((b / Math.sqrt(2)) + 1);
        if (needToResizeBy(ha) && !needToResizeBy(hb))
            return (ha * (hb - 1) + ((ha - 1) * hb));
        if (needToResizeBy(hb) && !needToResizeBy(ha))
            return ((ha - 1) * hb) + (ha * (hb -1));
        return (ha-- * hb--)+(ha * hb);
    }

    private static boolean needToResizeBy(int a) {
        return a % 2 == 0;
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        System.out.println(needToResizeBy(5));
        System.out.println("23: " + rectangleRotation(6, 4));
        System.out.println("65: " + rectangleRotation(30, 2));
        System.out.println("49: " + rectangleRotation(8, 6));
        System.out.println("333: " + rectangleRotation(16, 20));
    }
}
