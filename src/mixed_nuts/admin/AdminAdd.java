package mixed_nuts.admin;

import mixed_nuts.components.ImageLabel;
import mixed_nuts.components.MyLabel;
import mixed_nuts.components.MyPanel;
import mixed_nuts.components.MyTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminAdd extends JPanel implements MouseListener {
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
        String[] user = {"Welcome back! <user>", "Change Password", "Logout"};
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
        panel.add(new MyLabel("Name:",Color.white,benteSingko,64,133,151,29));
        panel.add(new MyLabel("Surname",Color.white,benteSingko,277, 168, 110, 29));
        panel.add(new MyLabel("Given Name",Color.white,benteSingko,488, 168, 150, 29));
        panel.add(new MyLabel("Middle Name",Color.white,benteSingko,710, 168, 160, 29));
        panel.add(new MyLabel("Department",Color.white,benteSingko,64, 200, 141, 29));
        panel.add(new MyLabel("Role:",Color.white,benteSingko,64, 244, 138, 29));
        panel.add(new MyLabel("Doctor",Color.white,benteSingko,262, 244, 138, 29));
        panel.add(new MyLabel("Nurse",Color.white,benteSingko,407, 244, 138, 29));
        panel.add(new MyLabel("<html>Department<br>Code:</html>",Color.white,benteSingko,64, 288, 138, 58));
        panel.add(new MyLabel("<html>Employee<br>ID:</html>",Color.white,benteSingko,64, 361, 138, 58));
        panel.add(new MyLabel("<html>Verify<br>Employee ID:</html>",Color.white,benteSingko,64, 434, 155, 62));



        MyTextField surnameField = new MyTextField(null,229, 132, 200, 28,bente);
        panel.add(surnameField);
        MyTextField givenField = new MyTextField(null,455, 132, 200, 28,bente);
        panel.add(givenField);
        MyTextField middleField = new MyTextField(null,680, 132, 200, 28,bente);
        panel.add(middleField);


        codeField = new JTextField();
        codeField.setBounds(229, 303, 230, 28);
        codeField.setBorder(BorderFactory.createEmptyBorder());
        codeField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        codeField.setHorizontalAlignment(JTextField.CENTER);
        codeField.setEditable(false);
        add(codeField);

        //ID FORM


        IDField = new JPasswordField();
        IDField.setEchoChar('•');
        IDField.setBounds(229, 376, 230, 28);
        IDField.setBorder(BorderFactory.createEmptyBorder());
        IDField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        IDField.setHorizontalAlignment(JTextField.CENTER);
        add(IDField);

        //ID FORM


        verifyField = new JPasswordField();
        verifyField.setEchoChar('•');
        verifyField.setBounds(229, 448, 230, 28);
        verifyField.setBorder(BorderFactory.createEmptyBorder());
        verifyField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        verifyField.setHorizontalAlignment(JTextField.CENTER);
        add(verifyField);

        //Dept FORM
        String[] department_list = {"--", "Cardiology", "Gastroenterology", "Gynecology",
                                    "Nephrology", "Neurology", "Oncology", "Ophthalmology",
                                    "Orthopaedics", "Otolaryngology", "Urology"};
        JComboBox<String> dept = new JComboBox<>(department_list);
        dept.setBounds(229, 200, 229, 28);
        dept.setFont(bente);
        panel.add(dept);
        dept.addMouseListener(this);




        JRadioButton doctorRadio = new JRadioButton();
        doctorRadio.setBounds(229, 244, 25, 25);
        doctorRadio.setForeground(Color.white);
        doctorRadio.setBackground(new Color(0x212C58));
        add(doctorRadio);

        JRadioButton nurseRadio = new JRadioButton();
        nurseRadio.setBounds(374, 244, 25, 25);
        doctorRadio.setForeground(Color.white);
        nurseRadio.setBackground(new Color(0x212C58));
        add(nurseRadio);

        //RADIO BUTTON GROUP
        ButtonGroup Roles = new ButtonGroup();
        Roles.add(doctorRadio);
        Roles.add(nurseRadio);

        //CODE FORM



        confirmButton = new JButton(new ImageIcon("confirm_logo.png"));
        confirmButton.setBounds(734, 551, 161, 41);
        confirmButton.setBorder(BorderFactory.createEmptyBorder());
        confirmButton.setContentAreaFilled(false);
        confirmButton.setFocusPainted(false);
        add(confirmButton);
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
}
