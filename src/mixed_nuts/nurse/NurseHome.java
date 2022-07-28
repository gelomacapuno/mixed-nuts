package mixed_nuts.nurse;


import mixed_nuts.app.LoginForm;
import mixed_nuts.app.PieChartForm;
import mixed_nuts.components.ImageLabel;
import mixed_nuts.components.MyButton;
import mixed_nuts.components.MyLabel;
import mixed_nuts.components.MyPanel;
import mixed_nuts.util.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class NurseHome extends JPanel  {

    public MyPanel panel;
    public NurseHome(){
        setLayout(null);
        setBGDesign();
    }

    private void setBGDesign(){
        setBackground(new Color(0x142959));

        add(panel = new MyPanel(new Color(255,255,255,120),15,75, 964,630));
        panel.setLayout(null);
        String respect = "Welcome! Nurse " + getNurseName();
        String[] user = {respect, "Change Password"};
        JComboBox<String> userMenu = new JComboBox<>(user);
        userMenu.setBounds(630,20,350,41);
        userMenu.setFont(new Font("Montserrat", Font.PLAIN, 22));
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
        panel.add(new MyLabel("Welcome to Healthbook!",Color.white,new Font("Montserrat", Font.BOLD, 40),
                17, 20, 894, 68));
        panel.add(new MyLabel("Summary of Records",Color.white,new Font("Montserrat", Font.PLAIN, 25),
                360, 136, 286, 32));
        panel.add(new MyLabel("View Records by Department",Color.white,new Font("Montserrat", Font.PLAIN, 17),
                370, 508, 330, 27));

        MyButton pieButton;
        panel.add(pieButton = new MyButton(new ImageIcon("piegraph.png"),
                275,231,430,242,null,null));
        pieButton.addActionListener(e -> new PieChartForm());



        add(new ImageLabel(new ImageIcon("element_opacity.png"),248,25,817,660));
    }


    public static String getNurseName() {
        String givenName = null;
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();

        String Identify = "SELECT givenName FROM nurse_t WHERE accountID = '"+ LoginForm.ida +"';";
        
        try{
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(Identify);
            if(rs.next()) {
                givenName = rs.getString("givenName");
            }
        }catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }

       return givenName;

    }
    public static String getNurseID() {
        String id = null;
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();

        String Identify = "SELECT NurseID FROM nurse_t WHERE accountID = '"+ LoginForm.ida +"';";

        try{
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(Identify);
            if(rs.next()) {
                id = rs.getString("NurseID");
            }
        }catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }

        return id;

    }
}
