package mixed_nuts.components;
import javax.swing.*;
import java.awt.*;

public class ImageLabel extends JLabel {
    public ImageLabel(ImageIcon image, int x, int y, int width, int height){
        super(image);
        setBounds(x,y,width,height);

    }

}
