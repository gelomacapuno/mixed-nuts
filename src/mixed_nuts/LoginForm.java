package mixed_nuts;
import mixed_nuts.components.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginForm extends JFrame{
    public MyPanel DisplayPanel = new MyPanel(new Color(0xF1F1F1),416,54,448,510);
    public MyTextField DeptCode;
    public JPasswordField EmpID;
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

        //Text Fields
        DeptCode = new MyTextField("Dept Code", 59,177,330,40, new Font("Roboto",Font.PLAIN,22));
        DeptCode.loginField();
        DeptCode.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (DeptCode.getText().equals("Dept Code")) {
                    DeptCode.setText("");
                    DeptCode.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (DeptCode.getText().isEmpty()) {
                    DeptCode.setForeground(Color.BLACK);
                    DeptCode.setText("Dept Code");
                }
            }
        });
        EmpID = new JPasswordField("Employee ID");
        EmpID.setEchoChar((char) 0);
        EmpID.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        EmpID.setBounds(59,283,330,40);
        EmpID.setFont(new Font("Roboto", Font.PLAIN, 22));
        EmpID.setOpaque(false);
        EmpID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (EmpID.getText().equals("Employee ID")) {
                    EmpID.setText("");
                    EmpID.setEchoChar('•');
                    EmpID.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (EmpID.getText().isEmpty()) {
                    EmpID.setEchoChar((char) 0);
                    EmpID.setForeground(Color.BLACK);
                    EmpID.setText("Employee ID");
                }
            }
        });


        //Submit Button
        DisplayPanel.add(login = new MyButton(new ImageIcon("signin.png"),
                59,431,330,53,new Font("Arial", Font.PLAIN,40),null));
        login.addActionListener(e -> validateLogin());

        //All text fields
        DisplayPanel.add(DeptCode);
        DisplayPanel.add(EmpID);
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
    public void validateLogin(){
        new MainMenu();
        setVisible(false);
    }
}

