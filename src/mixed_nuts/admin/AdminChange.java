package mixed_nuts.admin;

import mixed_nuts.components.*;
import mixed_nuts.util.DatabaseConnection;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminChange extends JPanel implements ActionListener{
    private JPasswordField currentField, newField,verifyField;
    private String curPass, newPass, verPass, check;
    private MyButton confirmButton;
    private final Font benteSingko = new Font("Helvetica",Font.PLAIN,25);
    private final Font bente = new Font("Helvetica",Font.PLAIN,20);
    public MyPanel panel;
    public AdminChange(){
        setBGDesign();
        changePassForm();
        setLayout(null);

    }
    private void setBGDesign(){
        setBackground(new Color(0x142959));

        add(panel = new MyPanel(new Color(255,255,255,120),15,75, 964,630));
        panel.setLayout(null);
        String[] user = {"Welcome back! Admin", "Change Password"};
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

            }
        });

        add(new ImageLabel(new ImageIcon("element_opacity.png"),246,25,817,660));

    }
    private void changePassForm(){
        panel.add(new MyLabel("Change Admin Password",Color.black,
                new Font("Helvetica",Font.PLAIN,40),22,19,460,55));
        panel.add(new MyLabel("Current Password:",Color.black,benteSingko,191,132,284,29));
        panel.add(new MyLabel("New Password:",Color.black,benteSingko,191,215,284,29));
        panel.add(new MyLabel("Verify New Password:",Color.black,benteSingko,191,301,284,29));


        panel.add(currentField = new JPasswordField());
        currentField.setEchoChar('*');
        currentField.setBounds(504, 133, 193, 28);
        currentField.setBorder(BorderFactory.createEmptyBorder());
        currentField.setFont(bente);
        currentField.setHorizontalAlignment(JTextField.CENTER);

        panel.add(newField = new JPasswordField());
        newField.setEchoChar('*');
        newField.setBounds(504, 193, 193, 28);
        newField.setBorder(BorderFactory.createEmptyBorder());
        newField.setFont(bente);
        newField.setHorizontalAlignment(JTextField.CENTER);

        panel.add(verifyField = new JPasswordField());
        verifyField.setEchoChar('*');
        verifyField.setBounds(504, 301, 193, 28);
        verifyField.setBorder(BorderFactory.createEmptyBorder());
        verifyField.setFont(bente);
        verifyField.setHorizontalAlignment(JTextField.CENTER);


        panel.add(confirmButton = new MyButton(new ImageIcon("confirm_logo.png"),761,337,161,41,null,null));
        confirmButton.addActionListener(this);
    }


    public void TransferVal(){
        curPass = currentField.getText();
        newPass = newField.getText();
        verPass = verifyField.getText();
    }

    public void clearField(){
        currentField.setText("");
        newField.setText("");
        verifyField.setText("");
    }

    private void verifyCurrentPass(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String ver = "SELECT password FROM accountst WHERE empType ='A'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(ver);
            if(rs.next())
                check = rs.getString("password");
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    private void verifyadmin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verify = "UPDATE accountst SET password = '"+newPass+"'WHERE empType ='A';";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(verify);
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TransferVal();
        verifyCurrentPass();
        if(curPass.equals(check)){
            if(newPass.equals(verPass)){
                verifyadmin();
                JOptionPane.showMessageDialog(null,"Password has been changed");
                clearField();
            }else{
                JOptionPane.showMessageDialog(null,"New and verify password don't match");
            }
        }else if(!curPass.equals(check)){
            JOptionPane.showMessageDialog(null,"Current Password is incorrect");
            clearField();
        }
    }
}
