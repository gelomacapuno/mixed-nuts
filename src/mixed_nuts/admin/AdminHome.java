package mixed_nuts.admin;

import mixed_nuts.components.ImageLabel;
import mixed_nuts.components.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminHome extends JPanel {
    private MyPanel panel;

    public AdminHome() {
        setLayout(null);
        setBGDesign();
    }

    private void setBGDesign() {
        setBackground(new Color(0xFFAE52));

        add(panel = new MyPanel(new Color(255, 255, 255, 120), 15, 75, 964, 630));
        String[] user = {"Welcome back! Admin", "Change Password"};
        JComboBox<String> userMenu = new JComboBox<>(user);
        userMenu.setBounds(630, 20, 350, 41);
        userMenu.setFont(new Font("Helvetica", Font.PLAIN, 22));
        add(userMenu);
        userMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userMenu.getSelectedItem().toString().equals("Change Password")) {
                    userMenu.setSelectedIndex(0);
                    AdminMenu.changePass();
                }

            }
        });
        add(new ImageLabel(new ImageIcon("admin_element.png"),248,25,817,660));
    }


}

