package windows;

import javax.swing.*;
import java.awt.*;

public abstract class Window extends JFrame {
    private static int WINDOW_HEIGHT = 500;
    private static int WINDOW_WIDTH = 500;
    protected Dimension dimension;
    protected JFrame jFrame;


    public Window(){
        jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width/2 - WINDOW_WIDTH / 2, dimension.height/2 - WINDOW_HEIGHT / 2,
                WINDOW_WIDTH, WINDOW_HEIGHT );
        jFrame.setTitle("Дурак");
        jFrame.setResizable(false);
        jFrame.setLayout(new BorderLayout());

    }


}
