package mixed_nuts.nurse;

import mixed_nuts.app.LoginForm;
import mixed_nuts.components.*;
import mixed_nuts.util.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static mixed_nuts.nurse.NurseHome.getNurseName;

public class NurseChange extends JPanel implements ActionListener{
    private MyButton confirmButton;
    private String curPassword,newPassword,verifyPassword,check;
    private JPasswordField currentField, newField,verifyField;
    private final Font bente = new Font("Helvetica", Font.PLAIN, 20);
    private final Font benteSingko = new Font("Helvetica",Font.PLAIN,25);
    public MyPanel panel;
    public NurseChange(){
        setBGDesign();
        changePassForm();
        setLayout(null);
    }

    private void setBGDesign(){
        setBackground(new Color(0x142959));

        add(panel = new MyPanel(new Color(255,255,255,120),15,75, 964,630));
        panel.setLayout(null);
        String respect = "Welcome! Nurse " + getNurseName();
        String[] user = {respect, "Change Password"};
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

        currentField = new JPasswordField();
        currentField.setEchoChar('*');
        currentField.setBounds(504, 133, 193, 28);
        currentField.setFont(bente);
        panel.add(currentField);
        newField = new JPasswordField();
        newField.setBounds(504, 193, 193, 28);
        newField.setEchoChar('*');
        newField.setFont(bente);
        panel.add(newField);
        verifyField = new JPasswordField();
        verifyField.setEchoChar('*');
        verifyField.setBounds(504, 301, 193, 28);
        verifyField.setFont(bente);
        panel.add(verifyField);

        confirmButton = new MyButton(new ImageIcon("confirm_logo.png"),400,500,161,41,
                null,new Color(0x828da6));
        confirmButton.setOpaque(true);
        confirmButton.addActionListener(this);
        panel.add(confirmButton);
    }

    private void transferVal(){
        curPassword = currentField.getText();
        newPassword = newField.getText();
        verifyPassword = verifyField.getText();
    }
    public void verifyCurEmp(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String ver = "SELECT password FROM accountst WHERE accountID = '"+ LoginForm.id+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(ver);
            if(rs.next()) {
                check = rs.getString("password");
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    private void updatePassword(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verify = "UPDATE accountst SET password ='"+newPassword+"' WHERE accountID='"+LoginForm.ida+"'";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(verify);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    private void clearField() {
        currentField.setText("");
        newField.setText("");
        verifyField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        transferVal();
        verifyCurEmp();
        if(curPassword.equals(check)){
            if(newPassword.equals(verifyPassword)){
                updatePassword();
                JOptionPane.showMessageDialog(null,"Password has been changed.");
                clearField();
            }else{
                JOptionPane.showMessageDialog(null,"New and Verify Password mismatched");
                clearField();
            }

        }else{
            JOptionPane.showMessageDialog(null,"Current password is incorrect.");
            clearField();
        }

    }
}
