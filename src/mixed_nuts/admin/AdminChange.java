package mixed_nuts.admin;

import mixed_nuts.components.ImageLabel;
import mixed_nuts.components.MyPanel;
import mixed_nuts.nurse.NurseChange;
import mixed_nuts.nurse.NurseMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminChange extends JPanel {
    public MyPanel panel;
    public AdminChange(){
        setBGDesign();
        setLayout(null);

    }
    private void setBGDesign(){
        setBackground(new Color(0x142959));

        add(panel = new MyPanel(new Color(255,255,255,120),15,75, 964,630));
        String[] user = {"Welcome back! Admin", "Change Password", "Logout"};
        JComboBox<String> userMenu = new JComboBox<>(user);
        userMenu.setBounds(630,20,350,41);
        userMenu.setFont(new Font("Helvetica", Font.PLAIN, 22));
        add(userMenu);

        userMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userMenu.getSelectedItem().toString().equals("Change Password")){
                    userMenu.setSelectedIndex(0);
                    NurseMenu.close();
                }
                if (userMenu.getSelectedItem().toString().equals("Logout")){
                    userMenu.setSelectedIndex(0);
                    NurseMenu.close();
                }
            }
        });

        add(new ImageLabel(new ImageIcon("element_opacity.png"),246,25,817,660));

    }


}
