package mixed_nuts.components;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyLabel extends JLabel {
    public MyLabel(String string, Color color, Font font, int x, int y, int width, int height){

        this.setText(string);
        this.setForeground(color);
        this.setBounds(x,y,width,height);
        this.setFont(font);
        setVisible(true);

    }

    public void setLoadingSettings(){
        this.setHorizontalTextPosition(SwingConstants.LEFT);
        this.setIconTextGap(18);
        this.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.setIcon(new ImageIcon("loading_circle.gif"));
    }
}
