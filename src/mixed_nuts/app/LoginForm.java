package mixed_nuts.app;
import mixed_nuts.admin.AdminMenu;
import mixed_nuts.components.*;
import mixed_nuts.doctor.DoctorMenu;
import mixed_nuts.miniwindow.AboutWindow;
import mixed_nuts.miniwindow.HelpWindow;
import mixed_nuts.nurse.NurseMenu;
import mixed_nuts.util.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginForm extends JFrame{
    public MyPanel DisplayPanel = new MyPanel(new Color(0xF1F1F1),416,54,448,510);
    private JComboBox<String> empDept;
    private String position;
    public MyTextField empID;
    public JPasswordField empPassword;
    public static String dept,emid, ida;
    public static int id;
    private MyButton login, power, help, about;
    private int posX, posY;

    public LoginForm() {
        SetDisplayPanel();
        SetDisplayContainer();
        setUndecorated(true);
        setVisible(true);
        getContentPane().requestFocusInWindow();
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void SetDisplayPanel(){

        DisplayPanel.setLayout(null);
        DisplayPanel.add(new MyLabel("SIGN IN",Color.black,new Font("Bebas Neue",Font.TRUETYPE_FONT,45), 174,81,480,50));
        DisplayPanel.add(new ImageLabel(new ImageIcon("sign_in_logo.png"),179,22,90,59));

        //Text Fields and Combo Boxes
        String[] department = {"Select Department",
                "Cardiology",
                "Gastroenterology",
                "Gynecology",
                "Nephrology",
                "Neurology",
                "Oncology",
                "Ophthalmology",
                "Orthopaedics",
                "Otolaryngology",
                "Urology"};
        empDept = new JComboBox<>(department);
        empDept.setBounds(59,170,330,40);
        empDept.setFont(new Font("Montserrat", Font.PLAIN, 22));

        empID = new MyTextField("Employee ID", 59,240,330,40, new Font("Montserrat",Font.PLAIN,22));
        empID.setBackground(new Color(0xF1F1F1));
        empID.setHorizontalAlignment(SwingConstants.LEFT);
        empID.loginField();
        empID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (empID.getText().equals("Employee ID")) {
                    empID.setText("");
                    empID.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (empID.getText().isEmpty()) {
                    empID.setForeground(Color.BLACK);
                    empID.setText("Employee ID");
                }
            }
        });
        empPassword = new JPasswordField("Password");
        empPassword.setEchoChar((char) 0);
        empPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        empPassword.setBounds(59,320,330,40);
        empPassword.setFont(new Font("Montserrat", Font.PLAIN, 22));
        empPassword.setOpaque(false);
        empPassword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (empPassword.getText().equals("Password")) {
                    empPassword.setText("");
                    empPassword.setEchoChar('•');
                    empPassword.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (empPassword.getText().isEmpty()) {
                    empPassword.setEchoChar((char) 0);
                    empPassword.setForeground(Color.BLACK);
                    empPassword.setText("Password");
                }
            }
        });


        //Submit Button
        DisplayPanel.add(login = new MyButton(new ImageIcon("signin.png"),
                59,431,330,53,new Font("Montserrat", Font.PLAIN,40),null));
        login.addActionListener(e -> validateLogin());

        //All text fields
        DisplayPanel.add(empDept);
        DisplayPanel.add(empID);
        DisplayPanel.add(empPassword);
        add(DisplayPanel);
    }
    public void SetDisplayContainer(){
        MyPanel displayContainer = new MyPanel(Color.white, 0, 0, 320, 180);
        power = new MyButton(new ImageIcon("power_button_dark.png"),12,21, 35,30,null,new Color(0x283469));
        power.addActionListener(e -> System.exit(0));
        displayContainer.add(power);
        help = new MyButton(new ImageIcon("help_dark.png"),7,73,42, 42,null,new Color(0x283469));
        help.addActionListener(e -> new HelpWindow());
        displayContainer.add(help);
        about = new MyButton(new ImageIcon("about_dark.png"),8,132, 42,42,null,new Color(0x283469));
        about.addActionListener(e -> new AboutWindow());
        displayContainer.add(about);

        displayContainer.add(new ImageLabel(new ImageIcon("element.png"),720,-100,817,660));
        displayContainer.add(new ImageLabel(new ImageIcon("elementinverted.png"),-300,300,817,660));
        displayContainer.setLayout(null);
        displayContainer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ev) {
                posX = ev.getX();
                posY = ev.getY();
            }
        });
        displayContainer.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent evt) {
                setLocation(evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);
            }
        });
        //POWER_OFF


        MyLabel message = new MyLabel("<html><div style='text-align: center;'>Don’t " +
                "have an Account?<br>Contact an <b>Administrator</b>.</div></html>",
                Color.white,new Font("Roboto", Font.PLAIN, 20),524,607,240,46);
        displayContainer.add(message);
        getContentPane().add(displayContainer);
    }

    private void GetPosition() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();

        String Identify = "SELECT * FROM accountst WHERE username = '" +empID.getText()+ "'";

        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery(Identify);
        if(rs.next()) {
            position = rs.getString("empType");
            ida = rs.getString("accountID");
            dept= rs.getString("department");
            emid = rs.getString("username");
        }
        id = Integer.parseInt(ida);
    }

    private void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM accountst WHERE department = '" + empDept.getSelectedItem().toString() +
                "' AND username = '"+ empID.getText() + "' AND password = '"+ empPassword.getText()+"'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()){
                if(queryResult.getInt(1)==1){
                    GetPosition();
                    switch (position){
                        case "D" -> {
                            dispose();
                            new DoctorMenu(ida,position,dept);
                        }
                        case "N" -> {
                            dispose();
                            new NurseMenu(ida,position,dept);
                        }
                        case "A" -> {
                            dispose();
                            new AdminMenu();
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Incorrect Fields");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
}

