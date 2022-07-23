package mixed_nuts.nurse;

import com.toedter.calendar.JDateChooser;
import mixed_nuts.app.LoginForm;
import mixed_nuts.components.*;
import mixed_nuts.util.DatabaseConnection;

import static mixed_nuts.nurse.NurseHome.getNurseName;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;


public class NurseAdd extends JPanel implements ActionListener {
    private MyTextField surnameField, firstnameField, middlenameField, weightField,heightField,
                        pulseField,bloodPressureField1,bloodPressureField2,bodyTempField;
    private JRadioButton maleRadioButton,femaleRadioButton;
    private ButtonGroup Gender;
    private JTextArea addressField;
    private JDateChooser birthField;
    private String gender;
    JComboBox<String> bloodtypeField, levelofpainField;
    private MyPanel addFormPanel;
    public MyButton logout, confirm;

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



        addFormPanel.add(surnameField = new MyTextField(null,136,22,200,28,bente));
        addFormPanel.add(firstnameField = new MyTextField(null,368, 22, 200, 28,bente));
        addFormPanel.add(middlenameField = new MyTextField(null,593, 22, 200, 28,bente));

        maleRadioButton = new JRadioButton();
        maleRadioButton.setBounds(143,92,25,25);
        maleRadioButton.setBackground(new Color(0x828da6));
        addFormPanel.add(maleRadioButton);

        femaleRadioButton = new JRadioButton();
        femaleRadioButton.setBounds(290,92,25,25);
        femaleRadioButton.setBackground(new Color(0x828da6));
        addFormPanel.add(femaleRadioButton);

        Gender = new ButtonGroup();
        Gender.add(maleRadioButton);
        Gender.add(femaleRadioButton);

        //ADDRESS FORM
        addressField = new JTextArea();
        addressField.setEditable(true);
        addressField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        addressField.setBounds(136,135, 772,59);
        addFormPanel.add(addressField);


        birthField = new JDateChooser();
        birthField.setBounds(136, 213, 200, 28);
        birthField.setBorder(BorderFactory.createEmptyBorder());
        birthField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        birthField.setDateFormatString("dd/MM/yyyy");
        addFormPanel.add(birthField);
        weightField = new MyTextField(null,136, 281, 100, 28,bente);
        addFormPanel.add(weightField);
        heightField = new MyTextField(null,513, 280, 100, 28,bente);
        addFormPanel.add(heightField);
        pulseField = new MyTextField(null,513, 417, 100, 28,bente);
        addFormPanel.add(pulseField);




        bloodtypeField = new JComboBox<>(bloodTypes);
        bloodtypeField.setBounds(136,349,100,28);
        bloodtypeField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        addFormPanel.add(bloodtypeField);
        addFormPanel.add(bloodPressureField1 = new MyTextField(null,513,349,100,28,bente));
        addFormPanel.add(bloodPressureField2 = new MyTextField(null,647,349,100,28,bente));
        addFormPanel.add(bodyTempField = new MyTextField(null,136,417,100,28,bente));


        levelofpainField = new JComboBox<>(level);
        levelofpainField.setBounds(136,478,100,28);
        levelofpainField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        addFormPanel.add(levelofpainField);


        confirm = new MyButton(new ImageIcon("confirm_logo.png"),768,521,161,41,null,null);
        confirm.setBackground(new Color(255,255,255,0));
        confirm.addActionListener(this);
        addFormPanel.add(confirm);
    }

    private void setBGDesign(){
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
        add(new ImageLabel(new ImageIcon("element_opacity.png"),248,25,817,660));

    }

    private void inputPatient(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String surname = surnameField.getText();
        String firstname = firstnameField.getText();
        String middlename = middlenameField.getText();
        String dateofBirth = ((JTextField)birthField.getDateEditor().getUiComponent()).getText();
        String[] dob = dateofBirth.split("/");
        int day = Integer.parseInt(dob[0]);
        int month = Integer.parseInt(dob[1]);
        int year = Integer.parseInt(dob[2]);
        LocalDate selectedDate = LocalDate.of(year,month,day);
        LocalDate currentDate = LocalDate.now();
        int result = Period.between(selectedDate,currentDate).getYears();
        String age = Integer.toString(result);
        String weight = weightField.getText();
        String height = heightField.getText();
        String bloodpressure = bloodPressureField1.getText() + "/" + bloodPressureField2.getText();
        String bodyTemp = bodyTempField.getText();
        String painlevel = (String)levelofpainField.getSelectedItem();
        String pulse = pulseField.getText();

        if(maleRadioButton.isSelected()){
            gender ="Male";
        }
        if(femaleRadioButton.isSelected()){
            gender = "Female";
        }
        String bloodtype = (String) bloodtypeField.getSelectedItem();
        String address = addressField.getText();

        String insertValue = "INSERT INTO patientst VALUES(idpatient,'"+surname+"','"+firstname+"','"+middlename+"'," +
                "'"+gender+"'," + "'"+address+"','"+age+"','"+month+"','"+day+"','"+year+"','"+weight+"','"+height+
                "','"+bloodtype+"','"+bloodpressure+"','"+bodyTemp+"','"+painlevel+"','"+pulse+"'," +
                "'"+ LoginForm.dept+"',default,null,null,null,null,'"+getNurseID()+"');";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertValue);
        }catch (Exception exc){
            exc.getCause();
            exc.printStackTrace();
        }
    }

    private String getNurseID() {
        String nurseID = null;
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();

        String Identify = "SELECT NurseID FROM nurse_t WHERE accountID = '"+ LoginForm.ida +"';";

        try{
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(Identify);
            if(rs.next()) {
                nurseID = rs.getString("NurseID");
            }
        }catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }

        return nurseID;

    }
    private void clearinput(){
        birthField.setDate(null);
        Gender.clearSelection();
        surnameField.setText("");
        firstnameField.setText("");
        middlenameField.setText("");
        weightField.setText("");
        heightField.setText("");
        bloodPressureField1.setText("");
        bloodPressureField2.setText("");
        bodyTempField.setText("");
        levelofpainField.setSelectedIndex(0);
        addressField.setText("");
        pulseField.setText("");
        bloodtypeField.setSelectedIndex(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == confirm){
            if(surnameField.getText().equals("")||
                    firstnameField.getText().equals("")||
                    middlenameField.getText().equals("")||
                    weightField.getText().equals("")||
                    heightField.getText().equals("")||
                    bloodPressureField1.getText().equals("")||
                    bloodPressureField2.getText().equals("")||
                    bodyTempField.getText().equals("")||
                    levelofpainField.getSelectedIndex() ==0||
                    addressField.getText().equals("")||
                    pulseField.getText().equals("")||
                    bloodtypeField.getSelectedIndex() == 0 ||
                    Gender.getSelection()==null ||
                    birthField.getDate()==null){
                JOptionPane.showMessageDialog(null, "Please complete all required fields!");
            }else{
                inputPatient();
                JOptionPane.showMessageDialog(null, "Patient Information has Been Added!");
                clearinput();
                NurseSearch.gettableData(new String[] {"","Name"});
            }        }
    }
}
