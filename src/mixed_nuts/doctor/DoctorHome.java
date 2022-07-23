package mixed_nuts.doctor;
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

public class DoctorHome extends JPanel {
    private MyButton pieButton;

    public DoctorHome(){
        setLayout(null);
        setBGDesign();
    }
    private void setBGDesign(){
        setBackground(new Color(0x142959));

        MyPanel panel;
        add(panel = new MyPanel(new Color(255,255,255,120),15,75, 964,630));
        String greet = "Welcome! Dr. " + getDoctorName();
        String[] user = {greet, "Change Password"};
        JComboBox<String> userMenu = new JComboBox<>(user);
        userMenu.setBounds(630,20,350,41);
        userMenu.setFont(new Font("Helvetica", Font.PLAIN, 22));
        add(userMenu);

        panel.add(new MyLabel("Welcome to Healthbook!",Color.white,new Font("Helvetica", Font.PLAIN, 40),
                17, 20, 894, 68));
        panel.add(new MyLabel("Summary of Records",Color.white,new Font("Sans Serif", Font.PLAIN, 30),
                347, 136, 286, 32));
        panel.add(new MyLabel("View Records by Department",Color.white,new Font("Helvetica", Font.PLAIN, 25),
                327, 508, 330, 27));

        panel.add(pieButton = new MyButton(new ImageIcon("piegraph.png"),
                275,231,430,242,null,null));
        pieButton.addActionListener(e -> new PieChartForm());

        userMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userMenu.getSelectedItem().toString().equals("Change Password")){
                    userMenu.setSelectedIndex(0);
                    DoctorMenu.changePass();
                }

            }
        });

        add(new ImageLabel(new ImageIcon("element_opacity.png"),248,25,817,660));
    }

    public static String getDoctorName(){
        String givenName = null;
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();

        String Identify = "SELECT givenName FROM doctor_t WHERE accountID = '" + DoctorMenu.ida+"';";
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(Identify);
            if(rs.next())
                givenName = rs.getString("givenName");
        }catch (Exception e){
            e.getCause();
            e.printStackTrace();
        }
        return givenName;
    }

    public static String getDoctorID(){
        String id = null;
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();

        String Identify = "SELECT DoctorID FROM doctor_t WHERE accountID = '"+ DoctorMenu.ida+"';";
        try{
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(Identify);
            if(rs.next()) {
                id = rs.getString("DoctorID");
            }
        }catch (Exception e){
            e.getCause();
            e.printStackTrace();
        }
        return id;
    }
}
