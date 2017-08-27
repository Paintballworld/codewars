package personal;

public class MaksTheQueen {

    public static boolean hit(int x1, int y1, int x2, int y2) {
        return x1 == x2 || y1 == y2 || Math.abs(x1 - x2) == Math.abs(y1 - y2);
    }

    public static void main(String[] args) {
        System.out.println(hit(4, 8, 8, 4));
    }
}
