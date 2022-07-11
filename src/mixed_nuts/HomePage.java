package mixed_nuts;

import mixed_nuts.components.ImageLabel;
import mixed_nuts.components.MyButton;
import mixed_nuts.components.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JPanel implements ActionListener {
    public JLabel heading;
    public MyPanel panel;
    public MyButton logout;
    public HomePage(){
        setLayout(null);
        setBGDesign();
    }

    private void setBGDesign(){
        setBackground(new Color(0x142959));

        add(panel = new MyPanel(new Color(255,255,255,120),15,75, 964,630));
        add(heading = new JLabel(""));
        heading.setFont(new Font("Helvetica", Font.PLAIN, 40));
        heading.setForeground(Color.white);
        heading.setBounds(17, 20, 894, 68);
        add(new ImageLabel(new ImageIcon("element.png"),246,25,817,660));
        add(logout = new MyButton(new ImageIcon("log_out.png"),841,20,123,41,null,null));
        logout.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu.close();
    }
}
