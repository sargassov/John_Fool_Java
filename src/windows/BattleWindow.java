package windows;

import actors.Computer;
import actors.MoveAndDefence;
import actors.Player;
import cards.Card;
import mains.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BattleWindow extends Window{

    private static int valueOfPlayers;
    private static int HEIGHT = 500;
    private static int WIDTH = 500;
    private Game game;
    private ArrayList<MoveAndDefence> actors;
    private static String battleImageFilename = "src/images/battleImage.jpg";
    private Font fontMove = new Font("Times New Roman", Font.ITALIC, 16);
    private int UPRSTR_X = 220;
    private int UPRSTR_Y = 20;
    private ArrayList<String> messages = new ArrayList<>(Arrays.asList("Ход Игрока", "Ход Компьютера", "Карта не подходит",
            "Компьютер взял карты себе"));
    private ArrayList<String> buttonMessages = new ArrayList<>(Arrays.asList("Передать ход", "Взять карты себе"));
    private ArrayList<Integer> listX = new ArrayList<>(Arrays.asList(20, 70, 380, 430, 20, 70, 380, 430, 20, 70, 380, 430));
    private ArrayList<Integer> listY = new ArrayList<>(Arrays.asList(40, 40, 40, 40, 120, 120, 120, 120, 200, 200, 200, 200));
    private static int mesCounter = 0;
    private static int butCounter = 0;
    private JButton actionButton;
    private JPanel jPanel;
    private static int xCoordMouse;
    private static int yCoordMouse;
    private static int yCoordtoCards = 330;
    private static int yCoordtoCardsLower = 455;
    private static int ySizeCard = 75;
    private Card taken;
    private ArrayList<Card> valueOfCards;


    public BattleWindow(Game game, ArrayList<MoveAndDefence> actors){
        super();
        this.game = game;
        this.actors = actors;
        System.out.println("actors is = " + actors.size());

        if(mesCounter == 1){
            valueOfCards = actors.get(game.getDefencePlayer()).getShuffle();
        }
        else{
            valueOfCards = actors.get(game.getMovePlayer()).getShuffle();
        }
        jFrame.setVisible(false);
    }

    public void showBattle(){
        jFrame.setVisible(true);
        jPanel = new JPanel(new GridLayout(1,1));
        jFrame.add(jPanel, BorderLayout.SOUTH);

        actionButton = new JButton(buttonMessages.get(butCounter));
        jPanel.add(actionButton, BorderLayout.SOUTH);
//        if(game.getPlayShuffle().size() != 0){
//            jPanel.add(actionButton, BorderLayout.SOUTH);
//        }
        actionButton.addActionListener(e -> {
            if(mesCounter == 0){
                System.out.println("movvvvvve");
                game.endMethod();
            }
            else{
                actors.get(game.getDefencePlayer()).getShuffle().addAll(game.getPlayShuffle());
                game.getPlayShuffle().clear();
                actors.get(game.getMovePlayer()).move(null);
            }
        });

        jFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                xCoordMouse = e.getX();
                yCoordMouse = e.getY();
                System.out.println("x = " + xCoordMouse + " y = " + yCoordMouse);

                int startX = 0;


                if(yCoordMouse >= yCoordtoCards + 30 && yCoordMouse < yCoordtoCardsLower){
                    for(Card c : valueOfCards){
                        if(xCoordMouse >= startX + (WIDTH / getValueOfCards().size()) / 2 - ySizeCard / 2 + 10 &&
                                xCoordMouse < startX + (WIDTH / getValueOfCards().size()) / 2 + ySizeCard / 2 - 10){
                            System.out.println(c.getNumber() + " " + c.getSuit());
                            if(mesCounter == 0){
                                System.out.println("moveP = " + game.getMovePlayer());
                                actors.get(game.getMovePlayer()).move(c);
                                return;
                            }
                            else
                                actors.get(game.getDefencePlayer()).defence(c);
                            return;
                        }
                        startX += WIDTH / actors.get(game.getMovePlayer()).getShuffle().size();

                    }
                    //jFrame.revalidate();
                }

            }
        });
        jFrame.add(new BattleWindow.BattleComponent());
    }

    class BattleComponent extends JComponent{
        @Override
        public void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D)g;
            Image battleImg = new ImageIcon(battleImageFilename).getImage();
            g2.drawImage(battleImg, 0, 0,null);

            g2.setFont(fontMove);
            g2.setColor(Color.WHITE);
            g2.drawString(messages.get(mesCounter), UPRSTR_X, UPRSTR_Y);

            showPlayerCards(g2);

            if(mesCounter == 2) mesCounter = 0;
            if(mesCounter == 3) mesCounter = 0;

            if(game.getPlayShuffle().size() != 0){
                System.out.println("игровой шафл ----- ");
                for(Card c : game.getPlayShuffle()){
                    System.out.println(c.getSuit() + " " + c.getNumber());
                }
                System.out.println("-------");
            }



            System.out.println("-----Карты компьютера ----");
            for(Card c : actors.get(1).getShuffle()){
                System.out.println(c.getSuit() + " " + c.getNumber());
            }
            System.out.println("--------------");
            repaint();
        }

        private void showPlayerCards(Graphics2D g2){
            for(int x = 0; x < game.getPlayShuffle().size(); x++){
                Image image = new ImageIcon(game.getPlayShuffle().get(x).getPictureAddress()).getImage();
                g2.drawImage(image, listX.get(x), listY.get(x), null);
            }

            int startX = 0;
            int width = BattleWindow.WIDTH;

            for(Card c : getValueOfCards()){
                Image image = new ImageIcon(c.getPictureAddress()).getImage();
                g2.drawImage(image, startX + (width / getValueOfCards().size()) / 2 - ySizeCard / 2, yCoordtoCards, null);
                startX += width / getValueOfCards().size();
            }
        }
    }

    public static void setMesCounter(int mesCounter) {
        BattleWindow.mesCounter = mesCounter;
    }

    public static void setButCounter(int butCounter) {
        BattleWindow.butCounter = butCounter;
    }

    private ArrayList<Card> getValueOfCards() {
        return valueOfCards;
    }

    public static int getMesCounter() {
        return mesCounter;
    }

    public static int getButCounter() {
        return butCounter;
    }
}
