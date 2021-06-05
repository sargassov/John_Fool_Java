package windows;

import mains.Game;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameWindow extends Window {
    private final int WIDTH = 300;
    private final int HEIGHT = 150;
    private final FrontWindow frontWindow;
    private JSlider valueOfPlayersSlider;
    private String VALUE_OF_PLAYERS = "Игроков ";
    private int currentValueOfPlayers;
    private static Game game;

    public NewGameWindow(FrontWindow frontWindow){
        this.frontWindow = frontWindow;
        jFrame.setSize(WIDTH, HEIGHT);

        jFrame.setBounds(dimension.width/2 - WIDTH / 2, dimension.height/2 - HEIGHT / 2,
                WIDTH, HEIGHT );

        jFrame.setTitle("Меню новой игры");
        jFrame.setLayout(new GridLayout(4,1));

        howManyPlayers();

        JButton jbtnStartGame = new JButton("Начать игру");
        jbtnStartGame.addActionListener(e -> {
            jFrame.setVisible(false);
            frontWindow.jFrame.setVisible(false);
            System.out.println("currentValueOfPlayers = " +  currentValueOfPlayers);
            game = new Game(currentValueOfPlayers, frontWindow);
        });

        jFrame.add(jbtnStartGame);
    }

    private void howManyPlayers(){
        jFrame.add(new JLabel("Выбирите количество игроков. От " + frontWindow.getMinValueOfPlayers() +
                " до " + frontWindow.getMaxValueOfPlayers()));
        final JLabel lblValueOfPlayers = new JLabel(VALUE_OF_PLAYERS + frontWindow.getMinValueOfPlayers());
        jFrame.add(lblValueOfPlayers);

        valueOfPlayersSlider = new JSlider(frontWindow.getMinValueOfPlayers(), frontWindow.getMaxValueOfPlayers(),
                frontWindow.getMinValueOfPlayers());
        valueOfPlayersSlider.addChangeListener(e -> {
            currentValueOfPlayers = valueOfPlayersSlider.getValue();
            lblValueOfPlayers.setText(VALUE_OF_PLAYERS + currentValueOfPlayers);
        });
        jFrame.add(valueOfPlayersSlider);
    }

}
