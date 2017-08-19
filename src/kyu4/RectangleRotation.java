package kyu4;

public class RectangleRotation {

    static int rectangleRotation(final int a, final int b) {
        double h1 = Math.sqrt(sqr(a) * 2);
        double h2 = Math.sqrt(sqr(b) / 2);
        System.out.println(String.format("h1:%7.4f, h2:%7.4f", h1, h2));
        return ((int) (h1 * h2));
    }

    private static int sqr(int val) {
        return val * val;
    }

    public static void main(String[] args) {
        System.out.println("23: " + rectangleRotation(6, 4));
        System.out.println("65: " + rectangleRotation(30, 2));
        System.out.println("49: " + rectangleRotation(8, 6));
        System.out.println("333: " + rectangleRotation(16, 20));
    }
}
