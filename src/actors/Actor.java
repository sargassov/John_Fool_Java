package actors;

import cards.Card;
import cards.Koloda;
import mains.Game;

import java.util.ArrayList;
import java.util.Scanner;

public class Actor {
    protected static int STANDART_CARD_VALUE = 6;
    protected ArrayList<Card> shuffle;
    protected Koloda koloda;
    protected ArrayList<Card> playShuffle;
    protected Scanner sc = new Scanner(System.in);
    protected Game game;
    protected boolean END_MOVE;
    public static String kozar;


    public Actor(Koloda koloda, ArrayList<Card> playShuffle, Game game, boolean END_MOVE){
        this.game = game;
        this.koloda = koloda;
        this.playShuffle = playShuffle;
        this.END_MOVE = END_MOVE;
        shuffle = new ArrayList<>();

        for (int i = 0; i < STANDART_CARD_VALUE; i++) {
            shuffle.add(koloda.getCards().get(0));
            koloda.getCards().remove(0);
        }
    }


}
