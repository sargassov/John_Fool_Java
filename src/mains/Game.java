package mains;

import actors.Actor;
import actors.Computer;
import actors.MoveAndDefence;
import actors.Player;
import cards.Card;
import cards.Koloda;

import java.util.ArrayList;

public class Game {
    private int valueOfPlayers;
    private ArrayList<Card> playShuffle;
    private ArrayList<Card> kolodaCards;
    private ArrayList<MoveAndDefence> actors;
    private static String KOZAR;
    private static int KOZAR_ADVANTAGE = 9;
    private static boolean IS_WON = false;
    private static boolean END_MOVE;
    private static int movePlayer = 0;
    private static int defencePlayer = movePlayer + 1;
    private static int countMove = 1;
    private static int maxPlayShuffleSize = 12;

    public Game(int valueOfPlayers){
        //this.valueOfPlayers = valueOfPlayers;
        Koloda koloda = new Koloda();
        kolodaCards = koloda.getCards();
        KOZAR = kolodaCards.get(kolodaCards.size()-1).getSuit();
        Actor.kozar = KOZAR;
        System.out.println("КОЗАРЬ - это " + KOZAR);

        cardPowerReInstance();

        actors = new ArrayList<>();
        playShuffle = new ArrayList<>();

        for (int i = 0; i <= valueOfPlayers; i++) {
            if(i < valueOfPlayers){
                Player player = new Player(koloda, playShuffle, this, END_MOVE);
                actors.add(player);
            }
            else{
                Computer computer = new Computer(koloda, playShuffle, this, END_MOVE);
                actors.add(computer);
            }
        }
    }

    public void battle(){
        while(true){
            for(movePlayer = 0, defencePlayer = 1;;){
                END_MOVE = false;
                System.out.println("----------\n" + countMove + " ход. Осталось карт в колоде = " + kolodaCards.size());
                if(defencePlayer == actors.size()) {defencePlayer = 0;}
                if(movePlayer == actors.size()) break;

                if(playShuffle.size() != 0) visualPlayShuffle();

                if(!IS_WON && !END_MOVE){
                    actors.get(movePlayer).move();
                }

                if(playShuffle.size() != 0) visualPlayShuffle();

                if(!IS_WON && !END_MOVE){
                    actors.get(defencePlayer).defence();
                }
                isWon();
                if(IS_WON) break;
            }
            if(IS_WON) break;
        }

    }

    private void visualPlayShuffle() {
        System.out.println();
        for(Card c : playShuffle){
            System.out.println(c.getSuit() +  " " + c.getNumber());
        }
        System.out.println();
    }

    public void endMethod(){
        takeOffMethod();
        for (int i = 0; i < actors.size(); i++) {
            actors.get(i).newCardTake();
        }
        END_MOVE = true;
        movePlayer++;
        defencePlayer++;
        countMove++;
    }

    public void isWon(){
        for(MoveAndDefence m : actors){
            if(m.getShuffle().size() == 0){
                System.out.println("Player " + actors.indexOf(m) + " WON!!!");
                IS_WON = true;
            }
        }
    }

    public void takeOffMethod(){
        actors.get(movePlayer).newCardTake();
    }

    private void cardPowerReInstance(){
        for(Card c : kolodaCards){
            if(c.getSuit().equals(KOZAR))
                c.setCardPower(c.getCardPower() + KOZAR_ADVANTAGE);
        }
    }

}
