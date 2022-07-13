package mixed_nuts.nurse;

import com.toedter.calendar.JDateChooser;
import mixed_nuts.components.*;
import mixed_nuts.nurse.NurseMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NurseAdd extends JPanel implements ActionListener {
    private MyPanel addFormPanel;
    public MyButton logout, confirm;
    public JLabel heading;

    public NurseAdd(){
        addFields();
        setLayout(null);
        setBackground(new Color(0x142959));
        setBGDesign();
    }


    private void addFields(){
        add(addFormPanel = new MyPanel(new Color(255,255,255,120),15,75, 964,630));
        addFormPanel.setLayout(null);
        Font bente = new Font("Helvetica", Font.PLAIN, 20);
        String[] bloodTypes = {"--", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        String[] level = {"--","1","2","3","4","5","6","7","8","9","10",};
        

        //All Labels
        addFormPanel.add(new MyLabel("Name:",Color.black,bente,25,22,74,25));
        addFormPanel.add(new MyLabel(" Surname:",Color.black,bente,186,57, 107,25));
        addFormPanel.add(new MyLabel(" Given Name:",Color.black,bente,395,57, 142,25));
        addFormPanel.add(new MyLabel(" Middle Name:",Color.black,bente,617,57, 150,25));
        addFormPanel.add(new MyLabel("/",Color.black,new Font("Helvetica", Font.PLAIN, 20),620,349,14,20));
        addFormPanel.add(new MyLabel("Sex:",Color.black,bente,25,92, 52,25));
        addFormPanel.add(new MyLabel("Male",Color.black,bente,176,92, 57,25));
        addFormPanel.add(new MyLabel("Female",Color.black,bente,321,92, 85,25));
        addFormPanel.add(new MyLabel("Address:",Color.black,bente,25,135, 100,25));
        addFormPanel.add(new MyLabel("<html>Date of<br>Birth:</html>",Color.black,bente,25,213, 151,62));
        addFormPanel.add(new MyLabel("Weight:",Color.black,bente,25,281, 88,25));
        addFormPanel.add(new MyLabel("Height:",Color.black,bente,348,281, 85,25));
        addFormPanel.add(new MyLabel("kg",Color.black,bente,258,281, 30,25));
        addFormPanel.add(new MyLabel("cm",Color.black,bente,628,281, 48,25));
        addFormPanel.add(new MyLabel("<html>Pulse<br>Rate:</html>",Color.black,bente,348,402, 109,48));
        addFormPanel.add(new MyLabel("bpm",Color.black,bente,628,423, 48,25));
        addFormPanel.add(new MyLabel("<html>Blood<br>Type:</html>",Color.black,bente,25,329, 69,62));
        addFormPanel.add(new MyLabel("<html>Blood<br>Pressure:</html>",Color.black,bente,348,337, 109,62));
        addFormPanel.add(new MyLabel("<html>Body<br>Temp:</html>",Color.black,bente,22,398, 109,58));
        addFormPanel.add(new MyLabel("°C",Color.black,bente,251,423, 30,25));
        addFormPanel.add(new MyLabel("<html>Level of<br>Pain:</html>",Color.black,bente,22,463, 109,58));


        MyTextField surnameField = new MyTextField(null,136,22,200,28,bente);
        addFormPanel.add(surnameField);

        MyTextField firstnameField = new MyTextField(null,368, 22, 200, 28,bente);
        addFormPanel.add(firstnameField);

        MyTextField middlenameField = new MyTextField(null,593, 22, 200, 28,bente);
        addFormPanel.add(middlenameField);

        JRadioButton maleRadioButton = new JRadioButton();
        maleRadioButton.setBounds(143,92,25,25);
        maleRadioButton.setBackground(new Color(0x828da6));
        addFormPanel.add(maleRadioButton);

        JRadioButton femaleRadioButton = new JRadioButton();
        femaleRadioButton.setBounds(290,92,25,25);
        femaleRadioButton.setBackground(new Color(0x828da6));
        addFormPanel.add(femaleRadioButton);
        ButtonGroup Gender = new ButtonGroup();
        Gender.add(maleRadioButton);
        Gender.add(femaleRadioButton);

        //ADDRESS FORM
        JTextArea addressField = new JTextArea();
        addressField.setEditable(true);
        addressField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        addressField.setBounds(136,135, 772,59);
        addFormPanel.add(addressField);


        JDateChooser birthField = new JDateChooser();
        birthField.setBounds(136, 213, 200, 28);
        birthField.setBorder(BorderFactory.createEmptyBorder());
        birthField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        birthField.setDateFormatString("dd/MM/yyyy");
        addFormPanel.add(birthField);
        MyTextField weightField = new MyTextField(null,136, 281, 100, 28,bente);
        addFormPanel.add(weightField);
        MyTextField heightField = new MyTextField(null,513, 280, 100, 28,bente);
        addFormPanel.add(heightField);
        MyTextField pulseField = new MyTextField(null,513, 417, 100, 28,bente);
        addFormPanel.add(pulseField);




        JComboBox<String> bloodtypeField = new JComboBox<>(bloodTypes);
        bloodtypeField.setBounds(136,349,100,28);
        bloodtypeField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        addFormPanel.add(bloodtypeField);
        MyTextField bloodPressureField1 = new MyTextField(null,513,349,100,28,bente);
        addFormPanel.add(bloodPressureField1);
        MyTextField bloodPressureField2 = new MyTextField(null,647,349,100,28,bente);
        addFormPanel.add(bloodPressureField2);
        MyTextField bodyTempField = new MyTextField(null,136,417,100,28,bente);
        bodyTempField.setBorder(BorderFactory.createEmptyBorder());
        addFormPanel.add(bodyTempField);


        JComboBox<String> levelofpainField = new JComboBox<>(level);
        levelofpainField.setBounds(136,478,100,28);
        levelofpainField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        addFormPanel.add(levelofpainField);


        confirm = new MyButton(new ImageIcon("confirm_logo.png"),768,521,161,41,null,null);
        confirm.setBackground(new Color(255,255,255,0));
        confirm.addActionListener(this);
        addFormPanel.add(confirm);
    }

    private void setBGDesign(){
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
                    NurseMenu.changePass();
                }
                if (userMenu.getSelectedItem().toString().equals("Logout")){
                    userMenu.setSelectedIndex(0);
                    NurseMenu.close();
                }
            }
        });
        add(new ImageLabel(new ImageIcon("element_opacity.png"),248,25,817,660));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logout)
            NurseMenu.close();

        if(e.getSource() == confirm){
            JOptionPane.showMessageDialog(null, "Patient Information has Been Added!");
        }
    }
}
