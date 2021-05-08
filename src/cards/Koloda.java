package cards;


import java.util.ArrayList;
import java.util.Collections;

public class Koloda {
    private ArrayList<Card> cards;
    //private int count;
    static private int SUIT_COUNT = 4;
    static private int NUMBER_COUNT = 9;

    public Koloda(){
        cards = new ArrayList<>();

        for (int i = 0; i < SUIT_COUNT; i++) {
            for (int j = 0; j < NUMBER_COUNT; j++) {
                Card card = new Card(i, j);
                cards.add(card);
            }
        }

        Collections.shuffle(cards);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
