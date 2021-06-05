package mains;

import actors.Actor;
import actors.Computer;
import actors.MoveAndDefence;
import actors.Player;
import cards.Card;
import cards.Koloda;
import com.sun.corba.se.impl.protocol.FullServantCacheLocalCRDImpl;
import windows.BattleWindow;
import windows.FrontWindow;
import windows.Window;

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
    private static FrontWindow frontWindow;
    private static BattleWindow battleWindow;
    private Card taken;
    private boolean access;

    public Game(int valueOfPlayers, FrontWindow frWindow){
        frontWindow = frWindow;
        this.valueOfPlayers = valueOfPlayers;
        Koloda koloda = new Koloda();
        kolodaCards = koloda.getCards();
        KOZAR = kolodaCards.get(kolodaCards.size()-1).getSuit();
        Actor.kozar = KOZAR;
        access = false;

        System.out.println("КОЗАРЬ - это " + KOZAR);
        cardPowerReInstance();

        actors = new ArrayList<>();
        playShuffle = new ArrayList<>();

        for (int i = 0; i < valueOfPlayers; i++) {
            if(i < valueOfPlayers - 1){
                Player player = new Player(koloda, playShuffle, this, END_MOVE);
                actors.add(player);
            }
            else{
                Computer computer = new Computer(koloda, playShuffle, this, END_MOVE);
                actors.add(computer);
            }
        }
        newSession();
    }



    public void endMethod(){
        takeOffMethod();
        for (int i = 0; i < actors.size(); i++) {
            actors.get(i).newCardTake();
        }

        playShuffle.clear();

        movePlayer++;
        if(movePlayer == actors.size()) movePlayer = 0;

        defencePlayer++;
        if(defencePlayer == actors.size()) defencePlayer = 0;

        if(BattleWindow.getMesCounter() == 0) BattleWindow.setMesCounter(1);
        else BattleWindow.setMesCounter(0);

        if(BattleWindow.getButCounter() == 0) BattleWindow.setButCounter(1);
        else BattleWindow.setButCounter(0);

        if(BattleWindow.getMesCounter() == 1) actors.get(movePlayer).move(null);
    }

    public void newSession(){
        battleWindow = new BattleWindow(this, actors);
        battleWindow.showBattle();
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

    public int getMovePlayer() {
        return movePlayer;
    }

    public ArrayList<Card> getPlayShuffle() {
        return playShuffle;
    }

    public int getDefencePlayer() {
        return defencePlayer;
    }

    public void setTaken(Card taken) {
        this.taken = taken;
    }

    public boolean getAccess(){
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public ArrayList<MoveAndDefence> getActors() {
        return actors;
    }

    public BattleWindow getBattleWindow() {
        return battleWindow;
    }
}
