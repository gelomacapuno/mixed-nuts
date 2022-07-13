package mixed_nuts.doctor;

import mixed_nuts.components.ImageLabel;
import mixed_nuts.components.MyLabel;
import mixed_nuts.components.MyPanel;
import mixed_nuts.components.MyTextField;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorChange extends JPanel {
    private final Font bente = new Font("Helvetica", Font.PLAIN, 20);
    private final Font benteSingko = new Font("Helvetica",Font.PLAIN,25);
    public MyPanel panel;
    public DoctorChange(){
        setBGDesign();
        changePassForm();
        setLayout(null);
    }

    private void setBGDesign(){
        setBackground(new Color(0x142959));

        add(panel = new MyPanel(new Color(255,255,255,120),15,75, 964,630));
        panel.setLayout(null);
        String[] user = {"Welcome back! <user>", "Change Password", "Logout"};
        JComboBox<String> userMenu = new JComboBox<>(user);
        userMenu.setBounds(630,20,350,41);
        userMenu.setFont(new Font("Helvetica", Font.PLAIN, 22));
        add(userMenu);

        userMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userMenu.getSelectedItem().toString().equals("Change Password")){
                    userMenu.setSelectedIndex(0);

                }
                if (userMenu.getSelectedItem().toString().equals("Logout")){
                    userMenu.setSelectedIndex(0);
                    DoctorMenu.close();
                }
            }
        });

        add(new ImageLabel(new ImageIcon("element_opacity.png"),246,25,817,660));


    }
    private void changePassForm(){
        panel.add(new MyLabel("Change User Password",Color.black,
                new Font("Helvetica",Font.PLAIN,40),22,19,460,55));
        panel.add(new MyLabel("Current Password:",Color.black,benteSingko,191,132,284,29));
        panel.add(new MyLabel("New Password:",Color.black,benteSingko,191,215,284,29));
        panel.add(new MyLabel("Verify New Password:",Color.black,benteSingko,191,301,284,29));

        MyTextField currentField = new MyTextField(null,504, 133, 193, 28,bente);
        panel.add(currentField);
        MyTextField newField = new MyTextField(null,504, 193, 193, 28,bente);
        panel.add(newField);
        MyTextField verifyField = new MyTextField(null,504, 301, 193, 28,bente);
        panel.add(verifyField);
    }
}
