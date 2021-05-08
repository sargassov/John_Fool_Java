package actors;

import cards.Card;
import cards.Koloda;
import mains.Game;

import java.util.ArrayList;

public class Player extends Actor implements MoveAndDefence {

    public Player(Koloda koloda, ArrayList<Card> playShuffle, Game game, boolean END_MOVE){
        super(koloda, playShuffle, game, END_MOVE);
    }

    @Override
    public void move() {
        int tookCard = -1;
        tookCard = checkTookCard(tookCard);
        if(tookCard == -1) return;

        System.out.println("Вы выбрали " + shuffle.get(tookCard).getSuit() + " " + shuffle.get(tookCard).getNumber());
        playShuffle.add(shuffle.get(tookCard));
        shuffle.remove(tookCard);
        game.isWon();
    }

    @Override
    public void defence() {
        String currentSuit = playShuffle.get(playShuffle.size() - 1).getSuit();
        int x = 1;

        while(true) {
            System.out.println("Выберете карту, чтобы отбиться от 1 до " + shuffle.size() + ", или жмите 0, чтобы забрать карту себе: ");
            for (Card c : shuffle) {
                System.out.println(x + " " + c.getSuit() + " " + c.getNumber());
                x++;
            }
            x = 1;

            int cardToChoise = sc.nextInt();

            if (cardToChoise == 0){
                game.takeOffMethod();
                shuffle.addAll(playShuffle);
                playShuffle.clear();
                return;
            }
            --cardToChoise;
            if(cardToChoise < 0 || cardToChoise > shuffle.size() - 1) {
                System.out.println("Нет такой карты");
                continue;
            }

            if (!shuffle.get(cardToChoise).getSuit().equals(playShuffle.get(playShuffle.size() - 1).getSuit()) &&
                    !shuffle.get(cardToChoise).getSuit().equals(kozar) && shuffle.get(cardToChoise).getCardPower()
                    <= playShuffle.get(playShuffle.size() - 1).getCardPower()){
                System.out.println("Карта не подходит");
                continue;
            }
            System.out.println("Вы выбрали " + shuffle.get(cardToChoise).getSuit() +
                    " " + shuffle.get(cardToChoise).getNumber());

            playShuffle.add(shuffle.get(cardToChoise));
            shuffle.remove(cardToChoise);

            return;
        }
    }

    @Override
    public ArrayList<Card> getShuffle() {
        return shuffle;
    }

    private int checkTookCard(int tookCard) {
        int x = 1;

        while (tookCard < 1 || tookCard > shuffle.size()){
            if(playShuffle.size() == 0) System.out.println("Выберете карту, от 1 до " + shuffle.size() + ": ");
            else System.out.println("Выберете карту, от 1 до " + shuffle.size() + ", или жмите 0" +
                    " для передачи хода другому игроку: ");
            for(Card c : shuffle){
                System.out.println(x + ". " + c.getSuit() + " " + c.getNumber());
                x++;
            }
            x = 1;
            tookCard = sc.nextInt();
            if(tookCard == 0) {
                playShuffle.clear();
                game.endMethod();
                return -1;
            }

            if(tookCard < 1 ) System.out.println("Нельзя выбирать карту меньше, чем 1.");
            else if (tookCard > shuffle.size()) System.out.println("Нельзя выбирать карту больше, чем " +
                    shuffle.size() + ".");


            if(playShuffle.size() > 0){
                boolean flag = false;
                for(Card c : playShuffle){
                    if(c.getNumber().equals(shuffle.get(tookCard - 1).getNumber())){
                        flag = true;
                        System.out.println(c.getNumber() + " " + flag);
                        break;
                    }
                }
                if (!flag) {
                    tookCard = 0;
                    System.out.println("Карта не подходит");
                }
            }
        }
        return --tookCard;
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
