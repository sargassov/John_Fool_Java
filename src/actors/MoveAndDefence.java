package actors;

import cards.Card;

import java.util.ArrayList;

public interface MoveAndDefence {
    void move();
    void defence();
    void newCardTake();
    ArrayList<Card> getShuffle();
}
