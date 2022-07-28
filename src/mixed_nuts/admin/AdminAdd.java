package mixed_nuts.admin;

import mixed_nuts.components.*;
import mixed_nuts.util.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminAdd extends JPanel implements ActionListener {
    private String surname,firstname,middlename,department,empID,verifiedPass,position;
    private int id;
    private JPasswordField passField, verifyField;
    private JComboBox<String> dept;
    private ButtonGroup roles;
    private JRadioButton doctorRadio, nurseRadio;
    private MyButton confirmButton;
    private MyTextField surnameField,givenField,middleField,empIDField;
    private final Font benteSingko = new Font("Montserrat",Font.PLAIN,20);
    private final Font bente = new Font("Montserrat",Font.PLAIN,20);
    public MyPanel panel;
    public AdminAdd(){
        setBGDesign();
        addUserForm();
        setLayout(null);
    }

    private void setBGDesign() {
        setBackground(new Color(0xFFAE52));
        panel = new MyPanel(new Color(255, 255, 255, 120), 15, 75, 964, 630){
            protected void paintComponent(Graphics g)
            {
                g.setColor( getBackground() );
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        panel.setOpaque(false);
        add(panel);
        panel.setLayout(null);
        String[] user = {"Welcome back! Admin", "Change Password"};
        JComboBox<String> userMenu = new JComboBox<>(user);
        userMenu.setBounds(630, 20, 350, 41);
        userMenu.setFont(new Font("Montserrat", Font.PLAIN, 22));
        add(userMenu);
        userMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userMenu.getSelectedItem().toString().equals("Change Password")) {
                    userMenu.setSelectedIndex(0);
                    AdminMenu.changePass();
                }

            }
        });
        add(new ImageLabel(new ImageIcon("admin_element.png"),380,25,817,660));
    }
    public void addUserForm() {
        //Labels
        panel.add(new MyLabel("Name:",Color.black,benteSingko,64,33,151,29));
        panel.add(new MyLabel("Surname",Color.black,benteSingko,277, 68, 110, 29));
        panel.add(new MyLabel("Given Name",Color.black,benteSingko,488, 68, 150, 29));
        panel.add(new MyLabel("Middle Name",Color.black,benteSingko,710, 68, 160, 29));
        panel.add(new MyLabel("Department",Color.black,benteSingko,64, 100, 141, 29));
        panel.add(new MyLabel("Role:",Color.black,benteSingko,64, 144, 138, 29));
        panel.add(new MyLabel("Doctor",Color.black,benteSingko,262, 144, 138, 29));
        panel.add(new MyLabel("Nurse",Color.black,benteSingko,407, 144, 138, 29));
        //panel.add(new MyLabel("<html>Department<br>Code:</html>",Color.black,benteSingko,64, 188, 138, 58));
        panel.add(new MyLabel("EmployeeID",Color.black,benteSingko,64, 261, 138, 58));
        panel.add(new MyLabel("Password",Color.black,benteSingko,64, 361, 138, 58));
        panel.add(new MyLabel("<html>Verify<br>Password:</html>",Color.black,benteSingko,64, 434, 155, 62));



        surnameField = new MyTextField(null,229, 32, 200, 28,bente);
        panel.add(surnameField);
        givenField = new MyTextField(null,455, 32, 200, 28,bente);
        panel.add(givenField);
        middleField = new MyTextField(null,680, 32, 200, 28,bente);
        panel.add(middleField);
        /*codeField = new MyTextField(null,229,203,230,28,bente);
        panel.add(codeField);*/
        empIDField = new MyTextField(null,229,276,230,28,bente);
        panel.add(empIDField);



        //ID FORM
        passField = new JPasswordField();
        passField.setEchoChar('•');
        passField.setBounds(232, 450, 230, 28);
        passField.setBorder(BorderFactory.createEmptyBorder());
        passField.setFont(new Font("Montserrat", Font.PLAIN, 20));
        passField.setHorizontalAlignment(JTextField.CENTER);
        add(passField);

        //ID FORM
        verifyField = new JPasswordField();
        verifyField.setEchoChar('•');
        verifyField.setBounds(232, 550, 230, 28);
        verifyField.setBorder(BorderFactory.createEmptyBorder());
        verifyField.setFont(new Font("Montserrat", Font.PLAIN, 20));
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




        doctorRadio = new JRadioButton();
        doctorRadio.setBounds(229, 223, 25, 25);
        doctorRadio.setOpaque(false);
        doctorRadio.setBorderPainted(false);
        doctorRadio.setContentAreaFilled(false);
        add(doctorRadio);

        nurseRadio = new JRadioButton();
        nurseRadio.setBounds(374, 223, 25, 25);
        nurseRadio.setOpaque(false);
        nurseRadio.setContentAreaFilled(false);
        nurseRadio.setBorderPainted(false);
        add(nurseRadio);

        //RADIO BUTTON GROUP
        roles = new ButtonGroup();
        roles.add(doctorRadio);
        roles.add(nurseRadio);

        //CODE FORM
        confirmButton = new MyButton(new ImageIcon("confirm_logo.png"),400, 550, 161, 41,null,new Color(0xfed3a2));
        confirmButton.setOpaque(true);
        panel.add(confirmButton);
        confirmButton.addActionListener(this);
    }

    private void inputDoctorNurse(String input){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();
        try{
            Statement st = connect.createStatement();
            st.executeUpdate(input);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    private void inputUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String insertFields = "INSERT INTO accountst(empType,username,password,department) VALUES('";
        String insertValue = position +"','"+ empID + "','" + verifiedPass + "','"+ department + "')";
        String insertUser = insertFields + insertValue;
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertUser);
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        try{
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery("SELECT last_insert_id() as 'last'");
            if(rs.next()){
                id = rs.getInt("last");
            }
        }catch (Exception e){
            e.getCause();
            e.printStackTrace();
        }
        if(position.equals("D")){
            insertFields = "INSERT INTO doctor_t(lastname,givenname,middlename,accountID) VALUES('";
            insertValue = surname +"','"+ firstname + "','" + middlename + "','"+ id + "')";
            inputDoctorNurse(insertFields+insertValue);
        }
        if(position.equals("N")){
            insertFields = "INSERT INTO nurse_t(lastname,givenname,middlename,accountID) VALUES('";
            insertValue = surname +"','"+ firstname + "','" + middlename + "','"+ id + "')";
            inputDoctorNurse(insertFields+insertValue);
        }

    }

    private void transferData(){
        surname = surnameField.getText();
        firstname = givenField.getText();
        middlename = middleField.getText();
        department = (String)dept.getSelectedItem();
        empID = empIDField.getText();
        verifiedPass = verifyField.getText();
        if(doctorRadio.isSelected()){
            position = "D";
        }
        if(nurseRadio.isSelected()){
            position = "N";
        }


    }

    private void getEmpID(){
        transferData();
        String checker = null;
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();
        String verify = empIDField.getText();
        String identify = "SELECT username FROM accountst WHERE username='" + verify + "'";

        try{
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(identify);
            if(rs.next()){
                checker = rs.getString("username");
            }
            if(verify.equals(checker)){
                JOptionPane.showMessageDialog(null,"EmpID is already in use please change.");
                empIDField.setText("");
                passField.setText("");
                verifyField.setText("");
            }else {
                inputUser();
                AdminSearch.getTableData(new String[]{"","All"});
                JOptionPane.showMessageDialog(null,"User Created Successfully.");
            }
        }
        catch (Exception e){
            e.getCause();
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(surnameField.getText().equals("") || middleField.getText().equals("") ||
            givenField.getText().equals("") || dept.getSelectedIndex() == 0 ||
            roles.getSelection()== null || empIDField.getText().equals("") ||
            passField.getText().equals("")||verifyField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please complete all required fields.");
        }else{
            if(!passField.getText().equals(verifyField.getText())){
                JOptionPane.showMessageDialog(null,"Password and Verify Password is not the same.");
            }else{getEmpID();}
        }

    }
}
