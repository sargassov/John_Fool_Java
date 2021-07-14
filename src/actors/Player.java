package actors;

import cards.Card;
import cards.Koloda;
import mains.Game;
import windows.BattleWindow;

import java.util.ArrayList;

public class Player extends Actor implements MoveAndDefence {

    private BattleWindow battleWindow;

    public Player(Koloda koloda, ArrayList<Card> playShuffle, Game game, boolean END_MOVE){
        super(koloda, playShuffle, game, END_MOVE);
    }

    @Override
    public void move(Card card) {
        if(playShuffle.size() == 0) {
            addNRemoveCard(card);
            game.getActors().get(game.getDefencePlayer()).defence(card);
        }
        else {
            for(int i = 0; i < playShuffle.size(); i++){
                if(playShuffle.get(i).getNumber().equals(card.getNumber())){
                    addNRemoveCard(card);
                    game.getActors().get(game.getDefencePlayer()).defence(card);
                }
            }
        }
    }


    private void addNRemoveCard(Card card){
        playShuffle.add(card);
        shuffle.remove(card);
    }

    @Override
    public void defence(Card card) {
        if((card.getCardPower() > playShuffle.get(playShuffle.size()-1).getCardPower()) &&
        (card.getSuit().equals(playShuffle.get(playShuffle.size()-1).getSuit()) ||
                    card.getSuit().equals(kozar))){

            playShuffle.add(card);
            shuffle.remove(card);

            game.getActors().get(game.getMovePlayer()).move(null);
        }
    }



    @Override
    public ArrayList<Card> getShuffle() {
        return shuffle;
    }


    @Override
    public void newCardTake(){
        while(shuffle.size() < 6){
            if(koloda.getCards().size() == 0) return;
            else{
                shuffle.add(koloda.getCards().get(0));
                koloda.getCards().remove(0);
            }

        }
    }
}
