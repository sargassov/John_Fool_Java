package windows;


import javax.swing.*;
import java.awt.*;

public class FrontWindow extends Window{

    private static NewGameWindow newGameWindow;
    private final int minValueOfPlayers;
    private final int maxValueOfPlayers;
    private static String frontImageFilename = "src/images/frontImage.jpg";
    private static int WIDTH = 500;
    private static int HEIGHT = 500;
    private static int UPRSTR_X = 210;
    private static int UPRSTR_Y = 35;
    private static int LWRSTR_X = 160;
    private static int LWRSTR_Y = 440;

    public FrontWindow(int minValueOfPlayers, int maxValueOfPlayers){
        super();
        this.minValueOfPlayers = minValueOfPlayers;
        this.maxValueOfPlayers = maxValueOfPlayers;

        jFrame.add(new FrontComponent());
        JPanel jPanel = new JPanel(new GridLayout(1,2));
        jFrame.add(jPanel, BorderLayout.SOUTH);

        JButton newGameBtn = new JButton("Сыграть");
        JButton exitBtn = new JButton("Выйти");
        jPanel.add(newGameBtn);
        jPanel.add(exitBtn);

        newGameBtn.addActionListener(e -> {
            newGameWindow = new NewGameWindow(this);
        });

        exitBtn.addActionListener(e -> {
            System.exit(0);
        });

    }

    static class FrontComponent extends JComponent{
        @Override
        public void paintComponent(Graphics g){
            Font fontTitle = new Font("Times New Roman", Font.ITALIC, 35);
            Font fontLow = new Font("Times New Roman", Font.BOLD, 16);
            Graphics2D g2 = (Graphics2D)g;
            Image frontImg = new ImageIcon(frontImageFilename).getImage();
            g2.drawImage(frontImg, 0, 0,null);
            g2.setFont(fontTitle);
            g2.drawString("Дурак", UPRSTR_X,UPRSTR_Y);
            g2.setFont(fontLow);
            g2.drawString("Written by <<S.Pedenko>>", LWRSTR_X,LWRSTR_Y);
        }
    }

    public int getMinValueOfPlayers() {
        return minValueOfPlayers;
    }

    public int getMaxValueOfPlayers() {
        return maxValueOfPlayers;
    }

    public NewGameWindow getNewGameWindow() {
        return newGameWindow;
    }
}
