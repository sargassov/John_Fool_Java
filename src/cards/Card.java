package cards;

import java.util.ArrayList;
import java.util.Arrays;

public class Card{
    private static ArrayList<String> suit = new ArrayList<>(Arrays.asList("Heart" , "Diamond", "Club", "Spade"));;
    private static ArrayList<String> number = new ArrayList<>(Arrays.asList("Six" , "Seven", "Eight", "Nine", "Ten",
            "Jack", "Queen", "King", "Ace"));;
    private String currentSuit;
    private String currentNum;
    private int cardPower;
    private String pictureAddress;

    public Card(int sSuit, int nNum){
        currentSuit = suit.get(sSuit);
        currentNum = number.get(nNum);
        cardPower = nNum + 1;
        pictureAddress = getPictureAddr();
    }

    public String getSuit() {
        return currentSuit;
    }

    public String getNumber() {
        return currentNum;
    }

    public int getCardPower() {
        return cardPower;
    }

    public void setCardPower(int cardPower) {
        this.cardPower = cardPower;
    }

    private String getPictureAddr(){
        StringBuilder address = new StringBuilder("src/images/cards/");

        int techNumber = 6;

        for(int x = 0; x < 5; x++){
            if(currentNum.equals(number.get(x))){
                address.append(techNumber);
            }
            techNumber++;
        }

        if(currentNum.equals("Jack")) address.append("J");
        else if(currentNum.equals("Queen")) address.append("Q");
        else if(currentNum.equals("King")) address.append("K");
        else if(currentNum.equals("Ace")) address.append("A");

        if(currentSuit.equals("Heart")) address.append("heart.png");
        else if(currentSuit.equals("Diamond")) address.append("diamond.png");
        else if(currentSuit.equals("Club")) address.append("club.png");
        else if(currentSuit.equals("Spade")) address.append("spade.png");

        String neo = new String (address);
        return neo;
    }

    public String getPictureAddress() {
        return pictureAddress;
    }
}
