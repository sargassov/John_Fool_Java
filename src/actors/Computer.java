package actors;

import cards.Card;
import cards.Koloda;
import mains.Game;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Computer extends Actor implements MoveAndDefence {

    public Computer(Koloda koloda, ArrayList<Card> playShuffle, Game game, boolean END_MOVE){
        super(koloda, playShuffle, game, END_MOVE);
    }



    @Override
    public void move() {
        int minPower = 100;
        int currentCardToMove = -1;
        Set<String> possibleMoveCards = new LinkedHashSet<>();

        if(playShuffle.size() != 0){
            for(Card c : playShuffle){
                possibleMoveCards.add(c.getNumber());
            }
        }


        for(Card c : shuffle){
            if(c.getCardPower() < minPower && (playShuffle.size() == 0 || possibleMoveCards.contains(c.getNumber()))){
                minPower = c.getCardPower();
                currentCardToMove = shuffle.indexOf(c);
            }
        }

        if(currentCardToMove == -1){
            playShuffle.clear();
            game.endMethod();
            return;
        }

        playShuffle.add(shuffle.get(currentCardToMove));
        System.out.println("Компьютер ходит картой " + shuffle.get(currentCardToMove).getSuit() + " "+
                shuffle.get(currentCardToMove).getNumber());
        shuffle.remove(currentCardToMove);
    }

    @Override
    public void defence() {
        Card battleCard = playShuffle.get(playShuffle.size() - 1);
        int defenceCard = -1;

        for(Card c : shuffle){
            System.out.println(c.getSuit() +  " " + c.getNumber());
            if((battleCard.getSuit().equals(c.getSuit()) || c.getSuit().equals(kozar))
                    && c.getCardPower() > battleCard.getCardPower()){
                int currentCard = shuffle.indexOf(c);
                if(defenceCard == -1 || shuffle.get(currentCard).getCardPower() < shuffle.get(defenceCard).getCardPower()){
                    defenceCard = currentCard;
                }
            }
        }

        if(defenceCard == -1){
            shuffle.addAll(playShuffle);
            playShuffle.clear();
            System.out.println("Компьютер взял карты себе!");
            game.takeOffMethod();
        }
        else{
            playShuffle.add(shuffle.get(defenceCard));
            System.out.println("Компьютер отбился картой " + shuffle.get(defenceCard).getSuit() + " " + shuffle.get(defenceCard).getNumber());
            shuffle.remove(defenceCard);
        }
    }

    @Override
    public ArrayList<Card> getShuffle() {
        return shuffle;
    }

    @Override
    public void newCardTake(){
        if(koloda.getCards().size() == 0) return;
        while(shuffle.size() < 6){
            shuffle.add(koloda.getCards().get(0));
            koloda.getCards().remove(0);
            if(koloda.getCards().size() == 0) return;
        }
    }

}
