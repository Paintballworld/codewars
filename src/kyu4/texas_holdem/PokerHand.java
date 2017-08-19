package kyu4.texas_holdem;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PokerHand {

    Hand hand;
    String cards;

    enum Result { TIE, WIN, LOSS }

    enum Suit { SPADES("S"), HEARTS("H"), DIAMONDS("D"), CLUBS("C");
        public String s;
        Suit(String s) {
            this.s = s;
        }

        public static Suit of (String val) {
            return Arrays.stream(Suit.values()).filter(p -> p.s.equals(val)).findFirst().orElse(null);
        }
    }

    private enum Combination {
        HIGHEST_CARD, PAIR, TWO_PAIRS, SET,
        STRAIGHT, FLUSH, FULL_HOUSE,
        QUADS, STRAIGHT_FLUSH, ROYAL_FLASH
    }


    private class Hand implements Comparable {
        Combination combination;
        Integer highestValue;
        Integer subHighestValue;
        TreeSet<Integer> cardsSet = new TreeSet<>();

        Hand(Combination combination, int highest, int subHighest) {
            this.combination = combination;
            this.highestValue = highest;
            this.subHighestValue = subHighest;
        }

        Hand(Combination combination, Collection<Card> cardsCollection) {
            this.combination = combination;
            this.cardsSet = new TreeSet<>(cardsCollection
                    .stream()
                    .map(Card::getValue)
                    .collect(Collectors.toList()));
        }

        Hand(Combination combination, Integer highest, List<Integer> valueByCount) {
            this.combination = combination;
            this.highestValue = highest;
            this.cardsSet = new TreeSet<>(valueByCount);
        }

        @Override
        public int compareTo(Object o) {
            Hand h1 = PokerHand.this.hand;
            Hand h2 = (Hand) o;
            if (h1.combination != h2.combination)
                return h1.combination.compareTo(h2.combination);
            if (h1.highestValue != null && !h1.highestValue.equals(h2.highestValue))
                return Integer.compare(h1.highestValue, h2.highestValue);
            if (!h1.cardsSet.isEmpty())
                while (h1.cardsSet.size() > 0) {
                    int card1 = h1.cardsSet.pollLast();
                    int card2 = h2.cardsSet.pollLast();
                    if (card1 != card2)
                        return Integer.compare(card1, card2);
                }
            if (h1.subHighestValue != null && !h1.subHighestValue.equals(h2.subHighestValue))
                return Integer.compare(h1.subHighestValue, h2.subHighestValue);
            return 0;
        }

        @Override
        public String toString() {
            return this.combination.toString();
        }
    }

    PokerHand(String hand) {
        this.hand = getHand(hand);
    }
    
    private Hand getHand(String handValue) {
        List<Card> cards = Arrays
                .stream(handValue.split(" "))
                .map(Card::of)
                .sorted()
                .collect(Collectors.toList());
//        System.out.println(cards.stream().map(Card::toString).collect(Collectors.joining(", ")));
        this.cards = cards.stream().map(Card::toString).collect(Collectors.joining(", "));
        Map<Suit, List<Card>> suitMap = cards
                .stream()
                .collect(Collectors.groupingBy(Card::getSuit));
        boolean isFlash = suitMap.size() == 1;
        boolean isStraight = isStraight(cards);

        Map<Integer, Long> countMap = cards
                .stream()
                .map(Card::getValue)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<Integer> quads = getValueByCount(countMap, 4);
        List<Integer> pairs = getValueByCount(countMap, 2);
        List<Integer> sets = getValueByCount(countMap, 3);
        int maxPairValue = pairs.size() > 0 ? pairs.get(0) : 0;

        if (isFlash || isStraight) {
        // Royal_flush, flush, straight, straight_flush
            int olderCard = cards.get(cards.size() - 1).value;
            int preOlderCard = cards.get(cards.size() - 2).value;
            if (olderCard == 14)
                olderCard = preOlderCard == 13 ? olderCard : preOlderCard;
            if (isFlash && isStraight) {
                if (olderCard == 14)
                    return new Hand(Combination.ROYAL_FLASH, olderCard, 0);
                return new Hand(Combination.STRAIGHT_FLUSH, olderCard, 0);
            }
            else if (isFlash)
                return new Hand(Combination.FLUSH, cards);
            else
                return new Hand(Combination.STRAIGHT, cards);
        } else
            // full house, set, two pairs, pair, quads
        if (quads.size() == 1) {
                return new Hand(Combination.QUADS, quads.get(0), getValueByCount(countMap, 1));
        } else if (pairs.size() == 1 && sets.size() == 1) {
            return new Hand(Combination.FULL_HOUSE, sets.get(0), maxPairValue);
        } else if (sets.size() == 1) {
            return new Hand(Combination.SET, sets.get(0), getValueByCount(countMap, 1));
        } else if (pairs.size() > 1) {
            return new Hand(Combination.TWO_PAIRS, maxPairValue, getValueByCount(countMap, 1));
        } else if (pairs.size() == 1) {
            return new Hand(Combination.PAIR, maxPairValue, getValueByCount(countMap, 1));
        } else {
            return new Hand(Combination.HIGHEST_CARD, cards);
        }
    }

    /**
     * Возвращает номиналы карт, котрый встречаются count раз в коллекции
     * @param countMap - карта Карта - количество повторений
     * @param count - количество включений в карту
     * @return список номиналов карт, которые встречаются столько-то раз в коллекции
     */
    private List<Integer> getValueByCount(Map<Integer, Long> countMap, int count) {
        return countMap
                .keySet()
                .stream()
                .filter(cardValue -> countMap.get(cardValue).intValue() == count)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private static boolean isStraight(List<Card> cards) {
        List<Integer> sorted = cards.stream().map(Card::getValue).sorted().collect(Collectors.toList());
        boolean result = isStraightOrdered(sorted);
        if (!result && sorted.contains(14)) {
            sorted.remove(Integer.valueOf(14));
            sorted.add(0, 1); // Помещаем туз в начало со значением 1
            result = isStraightOrdered(sorted);
        }
        return result;
    }

    private static boolean isStraightOrdered(List<Integer> cardValues) {
        Integer previous = null;
        for (int cardValue : cardValues) {
            if (previous != null && cardValue - previous != 1)
                return false;
            previous = cardValue;
        }
        return true;
    }

    Result compareWith(PokerHand hand) {
        int result = this.hand.compareTo(hand.hand);
        return result > 0 ?
                Result.WIN : result < 0 ?
                Result.LOSS :
                Result.TIE;
    }

    public static void main(String[] args) {
        PokerHand ph = new PokerHand("TS JS QC KH AH");
        System.out.println(ph.cards);
        System.out.println(ph.hand);

        String h1 = "3C 5D 5S KH KH";
        String h2 = "2C 5S 5D KH KH";
        PokerHand pl = new PokerHand(h1);
        PokerHand op = new PokerHand(h2);
        System.out.println(pl.cards + " : " + pl.hand.combination);
        System.out.println(op.cards + " : " + op.hand.combination);
        System.out.println(pl.compareWith(op));
    }
}

class Card implements Comparable{
    int value;
    private PokerHand.Suit suit;

    int getValue() {
        return value;
    }

    PokerHand.Suit getSuit() {
        return suit;
    }

    static Card of(String value) {
        Card card = new Card();
        card.value = intValueOf(value.split("")[0]);
        card.suit = PokerHand.Suit.of(value.split("")[1]);
        return card;
    }
    private static int intValueOf(String s) {
        try {
            return Integer.valueOf(s);
        } catch (NumberFormatException ignore) {
            String order = "TJQKA";
            return 10 + order.indexOf(s);
        }
    }

    @Override
    public String toString() {
        return get(value) +
                get(suit);
    }

    private String get(int val) {
        if (val <= 10)
            return val + "";
        switch (val) {
            case 11 : return "J";
            case 12 : return "Q";
            case 13 : return "K";
            case 14 : return "A";
        }
        return "";
    }

    private String get(PokerHand.Suit suit) {
        switch (suit) {
            case CLUBS: return "♣";
            case DIAMONDS: return "♦";
            case SPADES: return "♠";
            case HEARTS: return "♥";
        }
        return "";
    }

    @Override
    public int compareTo(Object o) {
        Card other = (Card) o;
        return Integer.compare(this.value, other.value);
    }
}


