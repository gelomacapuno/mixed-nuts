package mixed_nuts.components;
import javax.swing.*;
import java.awt.*;

public class MyTextField extends JTextField {
    public MyTextField(String text,int x, int y, int width, int height, Font font){
        setText(text);
        setBounds(x,y,width,height);
        setFont(font);
        setBorder(BorderFactory.createEmptyBorder());
        setBackground(Color.white);
        setHorizontalAlignment(JTextField.CENTER);
    }


    public void loginField(){
        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
    }
}
