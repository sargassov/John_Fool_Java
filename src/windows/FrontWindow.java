package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontWindow extends Window{
    private static Image frontWindowImage;
    private static String frontWindowImagePath = "src/pictures/frontimage.jpg";
    private static String frontWindowHelloText = "DURAK 1.0";
    private static Image image;
    private static Font font;
    private static JPanel jPanel = new JPanel(new GridLayout(1,2));;
    private static JButton jButtonStart = new JButton("Начать игру");
    private static JButton jButtonExit = new JButton("Выход");
    private FrontWindow frontWindow = this;

    public FrontWindow(){

        add(new FrontWindowComponent());
        jPanel.add(jButtonStart);
        jPanel.add(jButtonExit);
        add(jPanel, BorderLayout.SOUTH);

        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jButtonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                GameWindow gameWindow = new GameWindow(frontWindow);
            }
        });
    }


    static class FrontWindowComponent extends JComponent{

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            font = new Font("Times New Roman", Font.BOLD, 32);
            image = new ImageIcon(frontWindowImagePath).getImage();
            g2.setFont(font);
            g2.setColor(Color.WHITE);
            g2.drawImage(image, 0, 0, null);
            g2.drawString(frontWindowHelloText, 50, 80);
        }
    }
}
