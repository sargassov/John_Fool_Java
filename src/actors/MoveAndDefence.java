package actors;

import cards.Card;

import java.util.ArrayList;

public interface MoveAndDefence {
    void move(Card c);
    void defence(Card c);
    void newCardTake();
    ArrayList<Card> getShuffle();
}
