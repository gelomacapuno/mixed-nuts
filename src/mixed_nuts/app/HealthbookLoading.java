package mixed_nuts.app;
import mixed_nuts.components.GridBag;
import mixed_nuts.components.ImageLabel;
import mixed_nuts.components.MyLabel;
import mixed_nuts.components.MyPanel;

import javax.swing.*;
import java.awt.*;


public class HealthbookLoading extends JFrame{
    public MyLabel circle;

    //constructor method
    public HealthbookLoading()  {
        setHeader();
        setSize(1280, 720);
        setLayout(null);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
        //To give time for the system to prepare the resources
        try{
            for (int i = 0; i <= 100; i++){
                Thread.sleep(100);
                if(i == 0) circle.setText("Opening Application");
                if(i == 26) circle.setText("Starting Database");
                if(i == 51) circle.setText("Managing the Queries");
                if(i == 76) circle.setText("Starting the System");
            }
        }catch (Exception e){ JOptionPane.showMessageDialog(null, e);}
        this.setVisible(false);

        new LoginForm();
    }

    public void setHeader() {
        //variables
        GridBag constraints = new GridBag();
        MyPanel head = new MyPanel(new Color(0x283469),0,0,1280,720);
        head.setLayout(new GridBagLayout());

        //add all the labels in a Panel
        constraints.setConstraints(0.0,0,0,4,40,0,45,0);
        head.add(new ImageLabel(new ImageIcon("loading_logo.png"),0,0,0,0),constraints);
        constraints.setConstraints(0,1,4,0,0,0,0);
        head.add(new ImageLabel(new ImageIcon("loading_label.png"),0,0,0,0),constraints);
        constraints.setConstraints(GridBagConstraints.HORIZONTAL,1.0,0, 2,4,50,0,20,0);
        head.add(new MyLabel(" ",new Color(0x283469),null,0,0,0,0), constraints);
        constraints.setConstraints(0,3,1,0,0,0,0);
        head.add(new MyLabel(" ",new Color(0x283469),null,0,0,0,0),constraints);
        circle = new MyLabel("Opening Application", Color.white, new Font("Helvetica", Font.PLAIN, 36),
                0,0,0,0);
        circle.setLoadingSettings();
        constraints.setConstraints(GridBagConstraints.LINE_END,1.0, 3,3,1,0,40,0,0);
        head.add(circle,constraints);
        constraints.setConstraints(1,3,0,0,0,0);
        head.add(new MyLabel(" ",new Color(0x283469),null,0,0,0,0),constraints);
        constraints.setConstraints(2,3,0,0,0,0);
        head.add(new MyLabel(" ",new Color(0x283469),null,0,0,0,0),constraints);
        constraints.setConstraints(GridBagConstraints.HORIZONTAL,
                1.0,0,4,1,0,0,0,0);
        head.add(new MyLabel(" ",new Color(0x283469),null,0,0,0,0), constraints);
        add(head);
    }
}
