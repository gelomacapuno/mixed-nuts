package mixed_nuts.admin;

import mixed_nuts.app.PieChartForm;
import mixed_nuts.components.ImageLabel;
import mixed_nuts.components.MyButton;
import mixed_nuts.components.MyLabel;
import mixed_nuts.components.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminHome extends JPanel {

    public AdminHome() {
        setLayout(null);
        setBGDesign();
    }

    private void setBGDesign() {
        setBackground(new Color(0xFFAE52));

        MyPanel panel;
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
        panel.add(new MyLabel("Welcome to Healthbook!",Color.white,new Font("Helvetica", Font.PLAIN, 40),
                17, 20, 894, 68));
        panel.add(new MyLabel("Summary of Records",Color.white,new Font("Sans Serif", Font.PLAIN, 30),
                347, 136, 286, 32));
        panel.add(new MyLabel("View Records by Department",Color.white,new Font("Helvetica", Font.PLAIN, 25),
                327, 508, 330, 27));

        MyButton pieButton;
        panel.add(pieButton = new MyButton(new ImageIcon("piegraph.png"),
                275,231,430,242,null,null));
        pieButton.addActionListener(e -> new PieChartForm());
        add(new ImageLabel(new ImageIcon("admin_element.png"),248,25,817,660));

    }


}

