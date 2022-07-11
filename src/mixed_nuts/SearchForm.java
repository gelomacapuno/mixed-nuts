package mixed_nuts;

import mixed_nuts.components.ImageLabel;
import mixed_nuts.components.MyButton;
import mixed_nuts.components.MyLabel;
import mixed_nuts.components.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchForm extends JPanel implements ActionListener {
    public MyButton logout;
    public SearchForm(){
        setLayout(null);
        setBGUI();
    }

    private void setBGUI(){
        setBackground(new Color(0x142959));
        MyPanel panel;
        add(panel = new MyPanel(new Color(255,255,255,120),15,75, 964,630));
        add(new MyLabel("",Color.white,new Font("Helvetica", Font.PLAIN, 40),
                17,20,894,68));
        add(new ImageLabel(new ImageIcon("element.png"),246,25,817,660));
        add(logout = new MyButton(new ImageIcon("log_out.png"),841,20,123,41,null,null));
        logout.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu.close();
    }
}
