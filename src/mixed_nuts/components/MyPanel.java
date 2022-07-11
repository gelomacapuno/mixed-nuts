package mixed_nuts.components;

import javax.swing.*;
import java.awt.*;


public class MyPanel extends JPanel{

    public MyPanel(Color color, int x, int y, int width, int height){
        setBackground(color);
        setBounds(x,y,width,height);
        setVisible(true);

    }

}
