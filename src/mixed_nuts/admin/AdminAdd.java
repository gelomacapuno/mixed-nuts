package mixed_nuts.admin;

import mixed_nuts.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminAdd extends JPanel implements MouseListener, ActionListener {
    private MyTextField codeField;
    private JPasswordField passField, verifyField;
    private JComboBox<String> dept;
    private final Font benteSingko = new Font("Helvetica",Font.PLAIN,25);
    private final Font bente = new Font("Helvetica",Font.PLAIN,20);
    public MyPanel panel;
    public AdminAdd(){
        setBGDesign();
        addUserForm();
        setLayout(null);
    }

    private void setBGDesign() {
        setBackground(new Color(0x142959));

        add(panel = new MyPanel(new Color(255, 255, 255, 120), 15, 75, 964, 630));
        panel.setLayout(null);
        String[] user = {"Welcome back! Admin", "Change Password", "Logout"};
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
                if (userMenu.getSelectedItem().toString().equals("Logout")) {
                    userMenu.setSelectedIndex(0);
                    AdminMenu.close();
                }
            }
        });
        add(new ImageLabel(new ImageIcon("element_opacity.png"),248,25,817,660));
    }
    public void addUserForm() {
        //Labels
        panel.add(new MyLabel("Name:",Color.white,benteSingko,64,33,151,29));
        panel.add(new MyLabel("Surname",Color.white,benteSingko,277, 68, 110, 29));
        panel.add(new MyLabel("Given Name",Color.white,benteSingko,488, 68, 150, 29));
        panel.add(new MyLabel("Middle Name",Color.white,benteSingko,710, 68, 160, 29));
        panel.add(new MyLabel("Department",Color.white,benteSingko,64, 100, 141, 29));
        panel.add(new MyLabel("Role:",Color.white,benteSingko,64, 144, 138, 29));
        panel.add(new MyLabel("Doctor",Color.white,benteSingko,262, 144, 138, 29));
        panel.add(new MyLabel("Nurse",Color.white,benteSingko,407, 144, 138, 29));
        panel.add(new MyLabel("<html>Department<br>Code:</html>",Color.white,benteSingko,64, 188, 138, 58));
        panel.add(new MyLabel("EmployeeID",Color.white,benteSingko,64, 261, 138, 58));
        panel.add(new MyLabel("Password",Color.white,benteSingko,64, 361, 138, 58));
        panel.add(new MyLabel("<html>Verify<br>Password:</html>",Color.white,benteSingko,64, 434, 155, 62));



        MyTextField surnameField = new MyTextField(null,229, 32, 200, 28,bente);
        panel.add(surnameField);
        MyTextField givenField = new MyTextField(null,455, 32, 200, 28,bente);
        panel.add(givenField);
        MyTextField middleField = new MyTextField(null,680, 32, 200, 28,bente);
        panel.add(middleField);
        codeField = new MyTextField(null,229,203,230,28,bente);
        panel.add(codeField);
        MyTextField empIDField = new MyTextField(null,229,276,230,28,bente);
        panel.add(empIDField);



        //ID FORM
        passField = new JPasswordField();
        passField.setEchoChar('•');
        passField.setBounds(232, 450, 230, 28);
        passField.setBorder(BorderFactory.createEmptyBorder());
        passField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        passField.setHorizontalAlignment(JTextField.CENTER);
        add(passField);

        //ID FORM
        verifyField = new JPasswordField();
        verifyField.setEchoChar('•');
        verifyField.setBounds(232, 550, 230, 28);
        verifyField.setBorder(BorderFactory.createEmptyBorder());
        verifyField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        verifyField.setHorizontalAlignment(JTextField.CENTER);
        add(verifyField);

        //Dept FORM
        String[] department_list = {"--", "Cardiology", "Gastroenterology", "Gynecology",
                                    "Nephrology", "Neurology", "Oncology", "Ophthalmology",
                                    "Orthopaedics", "Otolaryngology", "Urology"};
        dept = new JComboBox<>(department_list);
        dept.setBounds(229, 100, 229, 28);
        dept.setFont(bente);
        panel.add(dept);
        dept.addMouseListener(this);




        JRadioButton doctorRadio = new JRadioButton();
        doctorRadio.setBounds(229, 223, 25, 25);
        doctorRadio.setForeground(Color.white);
        doctorRadio.setBackground(new Color(0,0,0,0));
        add(doctorRadio);

        JRadioButton nurseRadio = new JRadioButton();
        nurseRadio.setBounds(374, 223, 25, 25);
        doctorRadio.setForeground(Color.white);
        nurseRadio.setBackground(new Color(0,0,0,0));
        add(nurseRadio);

        //RADIO BUTTON GROUP
        ButtonGroup Roles = new ButtonGroup();
        Roles.add(doctorRadio);
        Roles.add(nurseRadio);

        //CODE FORM
        MyButton confirmButton = new MyButton(new ImageIcon("confirm_logo.png"),734, 451, 161, 41,null,null);
        panel.add(confirmButton);
        confirmButton.addActionListener(this);
    }

    public void Test(){
        if (dept.getSelectedItem().equals("Cardiology")){
            codeField.setText("Cardiology123");
        }
        else if (dept.getSelectedItem().equals("Gastroenterology")){
            codeField.setText("Gastroenterology123");
        }
        else if (dept.getSelectedItem().equals("Gynecology")){
            codeField.setText("Gynecology123");
        }
        else if (dept.getSelectedItem().equals("Nephrology")){
            codeField.setText("Nephrology123");
        }
        else if (dept.getSelectedItem().equals("Neurology")){
            codeField.setText("Neurology123");
        }
        else if (dept.getSelectedItem().equals("Oncology")){
            codeField.setText("Oncology123");
        }
        else if (dept.getSelectedItem().equals("Ophthalmology")){
            codeField.setText("Ophthalmology123");
        }
        else if (dept.getSelectedItem().equals("Orthopaedics")){
            codeField.setText("Orthopaedics123");
        }
        else if (dept.getSelectedItem().equals("Otolaryngology")){
            codeField.setText("Otolaryngology123");
        }
        else if (dept.getSelectedItem().equals("Urology")){
            codeField.setText("Urology123");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Test();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Test();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Test();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Test();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Test();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
