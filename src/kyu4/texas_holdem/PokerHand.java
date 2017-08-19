package kyu4.texas_holdem;

/**
 * Created by me on 19.08.17.
 */
public class PokerHand {

    public enum Result { TIE, WIN, LOSS }

    enum Suit { SPADES("S"), HEARTS("H"), DIAMONDS("D"), CLUBS("C");
        public String s;
        Suit(String s) {
            this.s = s;
        }
    }

    enum Combination {
        HIGH_CARD, PAIR, TWO_PAIRS, SET,
        STRAIGHT, FLUSH, FULL_HOUSE,
        QUADS, STRAIGHT_FLUSH, ROYAL_FLASH;
    }

    class Hand {
        public Combination combination;
        public int highestCard;
    }

    PokerHand(String hand)
    {

    }

    public Result compareWith(PokerHand hand) {
        return Result.TIE;
    }

    public static void main(String[] args) {
        System.out.println(Combination.FLUSH.compareTo(Combination.PAIR) > 0);
        System.out.println(intValueOf("T"));
        System.out.println(intValueOf("J"));
        System.out.println(intValueOf("Q"));
        System.out.println(intValueOf("K"));
        System.out.println(intValueOf("A"));
    }

    private static int intValueOf(String s) {
        try {
            return Integer.valueOf(s);
        } catch (NumberFormatException ignore) {
            String order = "TJQKA";
            return 10 + order.indexOf(s);
        }
    }
}
