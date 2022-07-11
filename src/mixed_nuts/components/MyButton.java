package mixed_nuts.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MyButton extends JButton {
    public MyButton(ImageIcon image, int x, int y, int width, int height, Font font, Color color){
        super(image);
        setBounds(x,y,width,height);
        setFont(font);
        setContentAreaFilled(false);
        setBackground(color);
        setFocusPainted(false);
        setForeground(Color.white);
        setBorder(BorderFactory.createEmptyBorder());
    }

    public void navButton(int gap, String text, Border border, Color color){
        setContentAreaFilled(true);
        setForeground(color);
        setHorizontalAlignment(SwingConstants.LEFT);
        setIconTextGap(gap);
        setText(text);
        setFont(new Font("Monsterrat", Font.PLAIN,25));
        setBorder(border);
    }
}
