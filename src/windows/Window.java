package windows;

import javax.swing.*;
import java.awt.*;

public abstract class Window extends JFrame {

    protected int WIDTH = 650;
    protected int HEIGHT = 650;
    protected static Dimension dimension;
    protected static Toolkit toolkit;

    public Window(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        toolkit = Toolkit.getDefaultToolkit();
        dimension = toolkit.getScreenSize();
        setBounds(dimension.width/2 - WIDTH/2, dimension.height/2 - HEIGHT/2, WIDTH, HEIGHT);
        setVisible(true);
        setTitle("Дурак 1.0");
        //setResizable(false);
        //setLayout(null);
    }
}
