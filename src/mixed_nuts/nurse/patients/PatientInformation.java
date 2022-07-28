package mixed_nuts.nurse.patients;

import mixed_nuts.app.LoginForm;
import mixed_nuts.components.*;
import mixed_nuts.nurse.NurseHome;
import mixed_nuts.nurse.NurseSearch;
import mixed_nuts.util.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;

public class PatientInformation extends JFrame implements ActionListener {
    private String[] level = {"--","1","2","3","4","5","6","7","8","9","10",};
    private JComboBox<String> painEntry;
    private JButton confirm_add_old;
    private JTextArea addressField;
    private JTextField bpEntry0,bpEntry1,tempEntry,pulseEntry,heightEntry,weightEntry;
    private String lastname,firstname,midname,gend,addrress,month,day,year,bt,
            ag,heigh,weigh,bloodpressure,btp,pulse,lop,department,doctor,
                obp,obt,opr,olop,ohei,owei,ebp,nurse;
    private JPanel panel = new JPanel();
    private RoundedPanel vitalsigns, body_panel, addOldPatient,adminAuthentication;
    private MyTextField givennameField,middlenameFIeld,surnameField,genderField,monthField,dayField,
    yearField,btypeField,ageField,heightField,weightField,blpField0,tempeField,pulField,plevelField;
    private JScrollPane scrollPane = new JScrollPane(panel);
    private MyButton confirm_edit,cancel_edit,back_button,addRecordButton,editPatient;
    private int X, Y;
    private Font benteSingko = new Font("Montserrat", Font.PLAIN,25);
    private JPasswordField adminPass;

    public PatientInformation() {
        super("Patient Information");
        setSize(1280, 720);
        setUndecorated(true);
        setResizable(false);
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setLayout(null);

        JPanel title_panel = new JPanel();
        title_panel.setLayout(null);
        title_panel.setBackground(new Color(0x212C58));
        title_panel.setBounds(0, 0, 1280, 100);
        add(title_panel);
        title_panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ev) {
                X = ev.getX();
                Y = ev.getY();
            }
        });
        title_panel.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent evt) {
                setLocation(evt.getXOnScreen() - X, evt.getYOnScreen() - Y);
            }
        });

        GetData();
        back_button = new MyButton(new ImageIcon("back.png"),12,20,86,63,null,null);
        back_button.addActionListener(this);
        title_panel.add(back_button);

        addRecordButton = new MyButton(new ImageIcon("addnewrecord.png"),1007,34,221,41,null,null);
        addRecordButton.addActionListener(this);
        title_panel.add(addRecordButton);
        title_panel.add(new MyLabel("Patient Details",Color.white,new Font("Montserrat",Font.PLAIN,30),
                510,30,260,54));



        //add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(0x212C58));
        panel.setBorder(new EmptyBorder(new Insets(23, 40, 100, 40)));
        panel.setVisible(true);
        panel.setBounds(0, 100, 1280, 620);

        scrollPane.setVisible(true);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(1280, 643));

        info();
        panel.add(Box.createRigidArea((new Dimension(5, 15))));
        vitalSigns();
        panel.add(Box.createRigidArea((new Dimension(5, 20))));

        editPatient = new MyButton(new ImageIcon("edit.png"),0,0,0,0,null,null);
        editPatient.addActionListener(this);
        panel.add(editPatient);

        scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setBounds(0, 100, 1280, 620);

        add(scrollPane);

        setNotEditable();

        //Transfer Data
        surnameField.setText(lastname);
        givennameField.setText(firstname);
        middlenameFIeld.setText(midname);
        genderField.setText(gend);
        addressField.setText(addrress);
        monthField.setText(month);
        dayField.setText(day);
        yearField.setText(year);
        btypeField.setText(bt);
        ageField.setText(ag);
        heightField.setText(heigh);
        weightField.setText(weigh);
        blpField0.setText(bloodpressure);
        tempeField.setText(btp);
        pulField.setText(pulse);
        plevelField.setText(lop);
    }

    public void setEditable() {
        surnameField.setEditable(true);
        givennameField.setEditable(true);
        middlenameFIeld.setEditable(true);
        genderField.setEditable(true);
        addressField.setEditable(true);
        monthField.setEditable(true);
        dayField.setEditable(true);
        yearField.setEditable(true);
        btypeField.setEditable(true);
        ageField.setEditable(true);
        heightField.setEditable(true);
        weightField.setEditable(true);
        blpField0.setEditable(true);
        tempeField.setEditable(true);
        pulField.setEditable(true);
        plevelField.setEditable(true);
        editPatient.setVisible(false);

        panel.add(Box.createRigidArea((new Dimension(5, 20))));

        panel.add(adminAuthentication = new RoundedPanel(50, new Color(0x212C58)));
        adminAuthentication.setOpaque(false);
        adminAuthentication.setBorder(new LineBorder(new Color(0x212C58), 1));
        adminAuthentication.setLayout(new GridBagLayout());


        GridBag c = new GridBag();
        adminAuthentication.setVisible(true);
        c.setConstraints(GridBagConstraints.BOTH,0,0,0,2,0,0,30,0);
        c.weighty = 0;
        adminAuthentication.add(new MyLabel("<html><div style= 'text-align:center;'>"+
                "To make changes, type the Admin password to<br>allow this, and then click Confirm.<div></html>",Color.white,benteSingko,0,0,0,0),c);

        c.setConstraints(GridBagConstraints.BOTH,0,0,1,1,0,0,0,0);
        adminAuthentication.add(new MyLabel("Administrator",Color.white,benteSingko,0,0,0,0),c);


        adminPass = new JPasswordField(10);
        adminPass.setEchoChar('•');
        adminPass.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminPass.setFont(new Font("Montserrat", Font.PLAIN, 20));
        c.setConstraints(GridBagConstraints.HORIZONTAL,0,2,2,0,0,20,0);
        adminAuthentication.add(adminPass,c);



        c.setConstraints(GridBagConstraints.BOTH,0,0,3,1,0,0,20,0);
        adminAuthentication.add(confirm_edit = new MyButton(new ImageIcon("confirmedit.png"),
                0,0,0,0,null,null));
        confirm_edit.addActionListener(this);
        confirm_edit.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminAuthentication.add(confirm_edit, c);

        c.setConstraints(GridBagConstraints.BOTH,0,1,3,1,0,0,20,0);
        cancel_edit = new MyButton(new ImageIcon("close.png"),0,0,0,0,null,null);
        cancel_edit.addActionListener(this);
        cancel_edit.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminAuthentication.add(cancel_edit, c);


    }

    public void setNotEditable() {
        surnameField.setEditable(false);
        givennameField.setEditable(false);
        middlenameFIeld.setEditable(false);
        genderField.setEditable(false);
        addressField.setEditable(false);
        monthField.setEditable(false);
        dayField.setEditable(false);
        yearField.setEditable(false);
        btypeField.setEditable(false);
        ageField.setEditable(false);
        heightField.setEditable(false);
        weightField.setEditable(false);
        blpField0.setEditable(false);
        tempeField.setEditable(false);
        pulField.setEditable(false);
        plevelField.setEditable(false);
        editPatient.setVisible(true);

    }

    public void GetData() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();

        String Identify = "SELECT * FROM patientst WHERE idpatient = '" + NurseSearch.value + "'";
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(Identify);
            if (rs.next()) {
                lastname = rs.getString("lastName");
                firstname = rs.getString("givenName");
                midname = rs.getString("middleName");
                gend = rs.getString("sex");
                ag = rs.getString("age");
                addrress = rs.getString("address");
                day = rs.getString("day");
                month = rs.getString("month");
                year = rs.getString("year");
                weigh = rs.getString("weight");
                heigh = rs.getString("height");
                bt = rs.getString("bloodType");
                bloodpressure = rs.getString("bloodPressure");
                btp = rs.getString("bodyTemp");
                lop = rs.getString("levelOfPain");
                pulse = rs.getString("pulseRate");
                department = rs.getString("department");
                doctor = rs.getString("doctorID");
                nurse = rs.getString("nurseID");
            }
        } catch (Exception e) {
            e.getCause();
            e.printStackTrace();
        }
    }



    public void vitalSigns() {

        panel.add(vitalsigns = new RoundedPanel(50, new Color(0x4d5579)));
        vitalsigns.setOpaque(false);
        vitalsigns.setLayout(new GridBagLayout());
        vitalsigns.setVisible(true);
        GridBag c = new GridBag();
        c.setConstraints(GridBagConstraints.BOTH,0,0,0,1,40,41,2,0);
        vitalsigns.add(new MyLabel("Vital Signs",Color.white,
                new Font("Montserrat",Font.BOLD,25),0,0,0,0),c);
        c.setConstraints(0,0,1,1,15,41,2,0);
        vitalsigns.add(new MyLabel("Blood Pressure:",Color.white,
                new Font("Montserrat",Font.PLAIN,20),0,0,0,0),c);
        c.setConstraints(GridBagConstraints.HORIZONTAL,0,1,1,1,15,41,2,0);
        blpField0 = new MyTextField("",0,0,0,0,
                new Font("Montserrat",Font.PLAIN, 20));
        blpField0.setColumns(10);
        vitalsigns.add(blpField0, c);
        c.setConstraints(GridBagConstraints.BOTH,0,0,2,1,15,41,2,0);
        vitalsigns.add(new MyLabel("Body Temperature:",Color.white,
                new Font("Montserrat",Font.PLAIN,20),0,0,0,0), c);
        c.setConstraints(GridBagConstraints.HORIZONTAL,0,1,2,1,15,41,2,0);
        tempeField = new MyTextField("",0,0,0,0,new Font("Montserrat",Font.PLAIN,20));
        tempeField.setColumns(10);
        vitalsigns.add(tempeField, c);
        c.setConstraints(GridBagConstraints.BOTH,0,2,2,1,15,41,2,0);
        vitalsigns.add(new MyLabel("°C",Color.white,new Font("Montserrat",Font.PLAIN, 20),
                0,0,0,0), c);
        c.setConstraints(GridBagConstraints.BOTH,0,0,3,1,15,41,2,0);
        vitalsigns.add(new MyLabel("Pulse Rate:",Color.white,new Font("Montserrat", Font.PLAIN, 20),
                0,0,0,0), c);
        pulField = new MyTextField("",0,0,0,0,new Font("Montserrat", Font.PLAIN, 20));
        c.setConstraints(GridBagConstraints.HORIZONTAL,0,1,3,1,15,41,2,0);
        pulField.setColumns(10);
        vitalsigns.add(pulField, c);
        c.setConstraints(GridBagConstraints.BOTH,0,2,3,1,15,41,2,0);
        vitalsigns.add(new MyLabel("bpm", Color.white,new Font("Montserrat",Font.PLAIN,20),
                0,0,0,0), c);
        c.setConstraints(GridBagConstraints.BOTH,0,0,4,1,15,41,14,0);
        vitalsigns.add(new MyLabel("Level of Pain",Color.white,new Font("Montserrat",Font.PLAIN,20),
                0,0,0,0), c);
        plevelField = new MyTextField("",0,0,0,0,new Font("Montserrat",Font.PLAIN,20));
        c.setConstraints(GridBagConstraints.HORIZONTAL,0,1,4,1,15,41,14,0);
        plevelField.setColumns(10);
        vitalsigns.add(plevelField, c);

        c.setConstraints(3,4,0,0,0,40);
        vitalsigns.add(new MyLabel("space",new Color(0x4d5579),new Font("Montserrat",Font.PLAIN,20),
                0,0,0,0),c);


        c.setConstraints(4,4,15,0,14,0);
        vitalsigns.add(new MyLabel("space",new Color(0x4d5579),new Font("Montserrat",Font.PLAIN,20),
                0,0,0,0),c);

        c.setConstraints(5,4,0,0,0,40);
        vitalsigns.add(new MyLabel("space",new Color(0x4d5579),new Font("Montserrat",Font.PLAIN,20),
                0,0,0,0),c);

        c.setConstraints(GridBagConstraints.HORIZONTAL,0,1,5,2,0,0,0,40);
        vitalsigns.add(new MyLabel("space",new Color(0x4d5579),new Font("Montserrat",Font.PLAIN,20),
                0,0,0,0),c);



    }

    public void info() {
        //Basic Info Variables
        String no = NurseSearch.value;
        String date = NurseSearch.date;

        panel.add(body_panel = new RoundedPanel(50, new Color(0x4d5579)));
        body_panel.setOpaque(false);
        body_panel.setBorder(BorderFactory.createEmptyBorder());
        body_panel.setLayout(new GridBagLayout());
        GridBag c = new GridBag();
        c.weightx = 0;
        c.weighty = 0;
        body_panel.setVisible(true);

        //Basic Details Title
        c.setConstraints(GridBagConstraints.BOTH,0,0,0,1,20,33,2,0);
        body_panel.add(new MyLabel("Basic Details",Color.white,new Font("Montserrat",Font.BOLD,25),
                0,0,0,0), c);
        c.setConstraints(GridBagConstraints.BOTH,0,0,1,1,15,33,2,0);
        body_panel.add(new MyLabel("Name:",Color.white,new Font("Montserrat",Font.BOLD,20),
                0,0,0,0), c);

        //Name
        surnameField = new MyTextField("",0,0,0,0,new Font("Montserrat", Font.PLAIN,20));
        c.setConstraints(GridBagConstraints.HORIZONTAL,0,1,1,1,15,33,2,0);
        surnameField.setColumns(10);
        body_panel.add(surnameField, c);
        middlenameFIeld = new MyTextField("",0,0,0,0,new Font("Montserrat", Font.PLAIN,20));
        c.setConstraints(GridBagConstraints.HORIZONTAL,0,3,1,1,15,33,2,0);
        middlenameFIeld.setColumns(10);
        body_panel.add(middlenameFIeld, c);
        givennameField = new MyTextField("",0,0,0,0,new Font("Montserrat", Font.PLAIN,20));
        c.setConstraints(GridBagConstraints.HORIZONTAL,0,2,1,1,15,33,2,0);
        givennameField.setColumns(10);
        body_panel.add(givennameField, c);

        c.setConstraints(GridBagConstraints.BOTH,0,1,2,1,15,33,2,0);
        body_panel.add(new MyLabel("Surname",Color.white,new Font("Montserrat",Font.PLAIN,20),
                0,0,0,0), c);
        c.setConstraints(GridBagConstraints.BOTH,0,2,2,1,15,33,2,0);
        body_panel.add(new MyLabel("Given Name",Color.white,new Font("Montserrat",Font.PLAIN,20),
                0,0,0,0), c);
        c.setConstraints(GridBagConstraints.BOTH,0,3,2,1,15,33,2,0);
        body_panel.add(new MyLabel("Middle Name",Color.white,new Font("Montserrat",Font.PLAIN,20),
                0,0,0,0), c);

        c.setConstraints(GridBagConstraints.BOTH,0,0,3,1,15,33,2,0);
        body_panel.add(new MyLabel("Sex:",Color.white,new Font("Montserrat",Font.PLAIN,20),
                0,0,0,0), c);

        c.setConstraints(GridBagConstraints.HORIZONTAL,0,1,3,1,15,33,2,0);
        genderField = new MyTextField("",0,0,0,0,new Font("Montserrat",Font.PLAIN,20));
        body_panel.add(genderField, c);

        //Address
        c.setConstraints(GridBagConstraints.BOTH,0,0,4,1,15,33,2,0);
        body_panel.add(new MyLabel("Address:",Color.white,new Font("Montserrat",Font.PLAIN,20),
                0,0,0,0), c);


        addressField = new JTextArea();
        addressField.setColumns(20);
        addressField.setRows(2);
        addressField.setLineWrap(true);
        addressField.setWrapStyleWord(true);
        addressField.setEditable(true);
        addressField.setFont(new Font("Montserrat", Font.PLAIN, 20));
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 4;
        body_panel.add(addressField, c);
        c.gridwidth = 1;

        //Date of Birth:
        c.setConstraints(GridBagConstraints.BOTH,0,0,5,1,15,33,2,0);
        body_panel.add(new MyLabel("Date of Birth:",Color.white,new Font("Montserrat",Font.PLAIN,20),
                0,0,0,0),c);

        yearField = new MyTextField("",0,0,0,0,new Font("Montserrat",Font.PLAIN,20));
        c.setConstraints(GridBagConstraints.HORIZONTAL,0,1,5,1,15,33,2,0);
        yearField.setColumns(4);
        body_panel.add(yearField, c);

     /*   c.setConstraints(GridBagConstraints.BOTH,0,0,5,1,15,33,2,0);
        body_panel.add(new MyLabel(":",Color.white,new Font("Helvetica",Font.PLAIN,20),
                0,0,0,0));
*/

        c.setConstraints(GridBagConstraints.BOTH,0,1,6,1,15,33,2,0);
        body_panel.add(new MyLabel("YYYY",Color.white,new Font("Montserrat", Font.PLAIN, 20),
                0,0,0,0),c);


        monthField = new MyTextField("",0,0,0,0,new Font("Montserrat", Font.PLAIN, 20));
        c.setConstraints(GridBagConstraints.HORIZONTAL,0,2,5,1,15,33,2,0);
        monthField.setColumns(2);
        body_panel.add(monthField, c);

        c.setConstraints(GridBagConstraints.BOTH,0,2,6,1,15,33,2,0);
        body_panel.add(new MyLabel("MM",Color.white,new Font("Montserrat", Font.PLAIN, 20),
                0,0,0,0),c);

        dayField = new MyTextField("",0,0,0,0,new Font("Montserrat", Font.PLAIN, 20));
        c.setConstraints(GridBagConstraints.HORIZONTAL,0,3,5,1,15,33,2,0);
        dayField.setColumns(2);
        body_panel.add(dayField, c);

        c.setConstraints(GridBagConstraints.BOTH,0,3,6,1,15,33,2,0);
        body_panel.add(new MyLabel("DD",Color.white,new Font("Montserrat", Font.PLAIN, 20),
                0,0,0,0),c);

        c.setConstraints(GridBagConstraints.BOTH,0,0,7,1,15,33,2,0);
        body_panel.add(new MyLabel("Blood Type:",Color.white,new Font("Montserrat", Font.PLAIN, 20),
                0,0,0,0),c);


        btypeField = new MyTextField("",0,0,0,0,new Font("Montserrat", Font.PLAIN, 20));
        btypeField.setColumns(10);
        c.setConstraints(GridBagConstraints.HORIZONTAL,0,1,7,1,15,33,2,0);
        body_panel.add(btypeField, c);


        c.setConstraints(GridBagConstraints.BOTH,0,0,8,1,15,33,2,0);
        body_panel.add(new MyLabel("Age:",Color.white,new Font("Montserrat", Font.PLAIN, 20),
                0,0,0,0),c);

        ageField = new MyTextField("",0,0,0,0,new Font("Montserrat", Font.PLAIN, 20));
        ageField.setColumns(10);
        c.setConstraints(GridBagConstraints.HORIZONTAL,0,1,8,1,15,33,2,0);
        body_panel.add(ageField, c);


        c.setConstraints(GridBagConstraints.BOTH,0,0,9,1,15,33,2,0);
        body_panel.add(new MyLabel("Height:",Color.white,new Font("Montserrat", Font.PLAIN, 20),
                0,0,0,0),c);


        heightField = new MyTextField("",0,0,0,0,new Font("Montserrat", Font.PLAIN, 20));
        heightField.setColumns(10);
        c.setConstraints(GridBagConstraints.HORIZONTAL,0,1,9,1,15,33,2,0);
        body_panel.add(heightField, c);

        c.setConstraints(GridBagConstraints.BOTH,0,2,9,1,15,33,2,0);
        body_panel.add(new MyLabel("cm",Color.white,new Font("Montserrat", Font.PLAIN, 20),
                0,0,0,0),c);

        c.setConstraints(GridBagConstraints.BOTH,0,0,10,1,15,33,35,0);
        body_panel.add(new MyLabel("Weight:",Color.white,new Font("Montserrat", Font.PLAIN, 20),
                0,0,0,0),c);

        weightField = new MyTextField("",0,0,0,0,new Font("Montserrat", Font.PLAIN, 20));
        weightField.setColumns(10);
        c.setConstraints(GridBagConstraints.HORIZONTAL,0,1,10,1,15,33,35,0);
        body_panel.add(weightField, c);

        c.setConstraints(GridBagConstraints.BOTH,0,2,10,1,15,33,35,0);
        body_panel.add(new MyLabel("kg",Color.white,new Font("Montserrat", Font.PLAIN, 20),
                0,0,0,0),c);



        //Patient No.:
        c.setConstraints(GridBagConstraints.BOTH,0,5,1,1,15,33,5,0);
        body_panel.add(new MyLabel("Patient No.: "+no,Color.white,new Font("Montserrat", Font.PLAIN, 20),
                0,0,0,0),c);
        //Date:
        c.setConstraints(GridBagConstraints.BOTH,0,5,2,1,15,33,5,40);
        body_panel.add(new MyLabel("Date: "+date,Color.white,new Font("Montserrat", Font.PLAIN, 20),
                0,0,0,0),c);

        //Dept:
        c.setConstraints(GridBagConstraints.BOTH,0,5,3,1,15,33,5,0);
        body_panel.add(new MyLabel("Dept: "+department,Color.white,new Font("Montserrat", Font.PLAIN, 20),
                0,0,0,0),c);


        //Date:
        c.setConstraints(GridBagConstraints.BOTH,0,5,4,1,15,33,5,0);
        body_panel.add(new MyLabel("Doctor ID: " + doctor,Color.white,new Font("Montserrat", Font.PLAIN, 20),
                0,0,0,0),c);

    }


    public void OldPatient() {
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() + 3);

        JLabel oldpatientLabel = new JLabel("Add Old Patient");
        oldpatientLabel.setForeground(Color.white);
        oldpatientLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        oldpatientLabel.setFont(new Font("Montserrat", Font.PLAIN, 40));
        oldpatientLabel.setVisible(true);
        panel.add(oldpatientLabel);

        panel.add(addOldPatient = new RoundedPanel(50, new Color(0x4d5579)));
        addOldPatient.setOpaque(false);
        addOldPatient.setLayout(new GridBagLayout());
        GridBag c = new GridBag();

        c.weightx = 1.0;
        c.weighty = 1.0;

        addOldPatient.setVisible(true);
        panel.add(Box.createRigidArea((new Dimension(5, 20))));

        //Vital Signs Title
        JLabel vitalSignsLabel = new JLabel("Vital Signs");
        vitalSignsLabel.setFont(new Font("Montserrat", Font.BOLD, 25));
        vitalSignsLabel.setForeground(Color.white);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(13, 33, 2, 0);
        addOldPatient.add(vitalSignsLabel, c);
        c.weightx = 1.0;

        //Blood Pressure
        JLabel bpLabel = new JLabel("Blood Pressure: ");
        bpLabel.setFont(new Font("Montserrat", Font.PLAIN, 20));
        bpLabel.setForeground(Color.white);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(15, 33, 2, 0);
        c.gridx = 0;
        c.gridy = 1;

        addOldPatient.add(bpLabel, c);

        bpEntry0 = new JTextField(3);
        bpEntry0.setFont(new Font("Montserrat", Font.PLAIN, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        addOldPatient.add(bpEntry0, c);

        JLabel slash = new JLabel("/");
        slash.setFont(new Font("Montserrat", Font.PLAIN, 20));
        slash.setHorizontalAlignment(JLabel.CENTER);
        slash.setForeground(Color.white);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(15, 0, 2, 0);
        c.weightx = 0;
        c.gridx = 2;
        c.gridy = 1;
        addOldPatient.add(slash, c);
        c.weightx = 1;

        bpEntry1 = new JTextField(3);
        bpEntry1.setFont(new Font("Montserrat", Font.PLAIN, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 1;
        addOldPatient.add(bpEntry1, c);


        JLabel space = new JLabel("");
        space.setFont(new Font("Montserrat", Font.PLAIN, 20));
        space.setForeground(new Color(0x4d5579));
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(15, 33, 2, 0);
        c.gridx = 4;
        c.gridy = 1;
        addOldPatient.add(space, c);

        //Body Temperature
        JLabel tempLabel = new JLabel("Body Temperature:");
        tempLabel.setFont(new Font("Montserrat", Font.PLAIN, 20));
        tempLabel.setForeground(Color.white);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(15, 33, 2, 0);
        c.gridx = 0;
        c.gridy = 2;
        addOldPatient.add(tempLabel, c);

        tempEntry = new JTextField(5);
        tempEntry.setFont(new Font("Montserrat", Font.PLAIN, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        tempEntry.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                tempEntry.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'
                        || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() == '.');
            }
        });
        c.gridx = 1;
        c.gridy = 2;
        addOldPatient.add(tempEntry, c);

        JLabel celsius = new JLabel("°C");
        celsius.setFont(new Font("Montserrat", Font.PLAIN, 20));
        celsius.setForeground(Color.white);
        celsius.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(15, 0, 2, 0);
        c.weightx = 0;
        c.gridx = 2;
        c.gridy = 2;
        addOldPatient.add(celsius, c);
        c.weightx = 1;

        //Pulse Rate
        JLabel pulseLabel = new JLabel("Pulse Rate:");
        pulseLabel.setFont(new Font("Montserrat", Font.PLAIN, 20));
        pulseLabel.setForeground(Color.white);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(15, 33, 2, 0);
        c.gridx = 0;
        c.gridy = 3;
        addOldPatient.add(pulseLabel, c);

        pulseEntry = new JTextField(5);
        pulseEntry.setFont(new Font("Montserrat", Font.PLAIN, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        addOldPatient.add(pulseEntry, c);

        JLabel bpm = new JLabel("bpm");
        bpm.setFont(new Font("Montserrat", Font.PLAIN, 20));
        bpm.setForeground(Color.white);
        bpm.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(15, 0, 2, 0);
        c.gridx = 2;
        c.gridy = 3;
        addOldPatient.add(bpm, c);

        //Level of Pain:
        JLabel painLabel = new JLabel("Level of Pain:");
        painLabel.setFont(new Font("Montserrat", Font.PLAIN, 20));
        painLabel.setForeground(Color.white);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(15, 33, 14, 0);
        c.gridx = 0;
        c.gridy = 4;
        addOldPatient.add(painLabel, c);

        painEntry = new JComboBox<>(level);
        painEntry.setFont(new Font("Montserrat", Font.PLAIN, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        addOldPatient.add(painEntry, c);

        //Height:
        JLabel heightLabel = new JLabel("Height:");
        heightLabel.setFont(new Font("Montserrat", Font.PLAIN, 20));
        heightLabel.setForeground(Color.white);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(15, 33, 14, 0);
        c.gridx = 0;
        c.gridy = 5;
        addOldPatient.add(heightLabel, c);

        heightEntry = new JTextField(5);
        heightEntry.setFont(new Font("Montserrat", Font.PLAIN, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        heightEntry.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                heightEntry.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'
                        || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() == '.');
            }
        });
        addOldPatient.add(heightEntry, c);

        JLabel cm = new JLabel("cm");
        cm.setFont(new Font("Montserrat", Font.PLAIN, 20));
        cm.setHorizontalAlignment(JLabel.CENTER);
        cm.setForeground(Color.white);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(15, 0, 2, 0);
        c.gridx = 2;
        c.gridy = 5;
        addOldPatient.add(cm, c);

        //Weight:
        JLabel weightLabel = new JLabel("Weight:");
        weightLabel.setFont(new Font("Montserrat", Font.PLAIN, 20));
        weightLabel.setForeground(Color.white);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(15, 33, 14, 0);
        c.gridx = 0;
        c.gridy = 6;
        addOldPatient.add(weightLabel, c);

        weightEntry = new JTextField(5);
        weightEntry.setFont(new Font("Montserrat", Font.PLAIN, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 6;
        weightEntry.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                weightEntry.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'
                        || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() == '.');
            }
        });
        addOldPatient.add(weightEntry, c);

        JLabel kg = new JLabel("kg");
        kg.setFont(new Font("Montserrat", Font.PLAIN, 20));
        kg.setForeground(Color.white);
        kg.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(15, 0, 2, 0);
        c.gridx = 2;
        c.gridy = 6;
        addOldPatient.add(kg, c);
        panel.add(Box.createRigidArea((new Dimension(5, 20))));

        confirm_add_old = new JButton(new ImageIcon("confirm_logo.png"));
        confirm_add_old.setContentAreaFilled(false);
        confirm_add_old.setVisible(true);
        confirm_add_old.setFocusPainted(false);
        confirm_add_old.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        confirm_add_old.addActionListener(this);
        confirm_add_old.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(confirm_add_old);
        panel.add(Box.createRigidArea((new Dimension(5, 20))));

    }

    public void InsertOldPatient() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        obp = bpEntry0.getText() + "/" + bpEntry1.getText();
        obt = tempEntry.getText();
        opr = pulseEntry.getText();
        olop = (String) painEntry.getSelectedItem();
        ohei = heightEntry.getText();
        owei = weightEntry.getText();

        //Date Parsing
        LocalDate selectedDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        LocalDate currentDate = LocalDate.now();
        int result = Period.between(selectedDate, currentDate).getYears();
        String age = Integer.toString(result);

        String insertFields = "INSERT INTO patientst(lastName,givenName,middleName,sex,address,age,month," +
                "day,year,weight,height,bloodType,bloodPressure,bodyTemp,levelOfPain,pulseRate,department,nurseID) VALUES ('";
        String insertValue = lastname + "','" + firstname + "','" + midname + "','" + gend + "','" + addrress + "','" +
                age + "','" + month + "','" + day + "','" + year + "','" + owei + "','" + ohei + "','" + btp + "','" +
                obp + "','" + obt + "','" + olop + "','" + opr + "','" + LoginForm.dept + "','" + NurseHome.getNurseID() + "')";
        String insertPatient = insertFields + insertValue;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertPatient);
            JOptionPane.showMessageDialog(null, "Patient Record Added!");
            NurseSearch.gettableData(new String[]{"","Name"});
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void editinsert() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        lastname = surnameField.getText();
        firstname = givennameField.getText();
        midname = middlenameFIeld.getText();
        gend = genderField.getText();
        addrress = addressField.getText();
        ag = ageField.getText();
        month = monthField.getText();
        day = dayField.getText();
        year = yearField.getText();
        weigh = weightField.getText();
        heigh = heightField.getText();
        bt = btypeField.getText();
        ebp = blpField0.getText();
        btp = tempeField.getText();
        lop = plevelField.getText();
        pulse = pulField.getText();

        String insertFields = "UPDATE patientst SET lastName = '" + lastname + "',givenName = '" + firstname + "',middleName = " +
                "'" + midname + "',sex = '" + gend + "',address='" + addrress + "',age='" + ag + "',month='" + month + "',day='" + day + "'," +
                "year='" + year + "',weight='" + weigh + "',height='" + heigh + "',bloodType='" + bt + "',bloodPressure='" + ebp + "',bodyTemp='" + btp + "'" +
                ",levelOfPain='" + lop + "',pulseRate='" + pulse + "' WHERE  idpatient = '" + NurseSearch.value + "'";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertFields);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    //admin authentication
   public void autheticate() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM accountst WHERE password = '" + adminPass.getText() + "' AND empType = 'A'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    editinsert();
                    JOptionPane.showMessageDialog(null, "Account has Been Edited");
                    NurseSearch.gettableData(new String[]{"","Name"});
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong Username for Admin.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back_button) {
            NurseSearch.gettableData(new String[] {"","Name"});
            setVisible(false);
        }

        if (e.getSource() == addRecordButton) {
            editPatient.setVisible(false);
            addRecordButton.setVisible(false);
            OldPatient();
        }

        if (e.getSource() == editPatient) {
            addRecordButton.setVisible(false);
            setEditable();
        }

        if (e.getSource() == confirm_add_old) {
            if (bpEntry0.getText().equals("") ||
                    bpEntry1.getText().equals("") ||
                    tempEntry.getText().equals("") ||
                    pulseEntry.getText().equals("") ||
                    painEntry.getSelectedIndex() == 0 ||
                    heightEntry.getText().equals("") ||
                    weightEntry.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please complete all fields.");
            } else {
                editPatient.setVisible(true);
                InsertOldPatient();
                dispose();
            }

        }
        if (e.getSource() == confirm_edit) {
            addRecordButton.setVisible(true);
            adminAuthentication.setVisible(false);
            autheticate();
            setNotEditable();
        }

        if (e.getSource() == cancel_edit) {
            addRecordButton.setVisible(true);
            editPatient.setVisible(true);
            adminAuthentication.setVisible(false);
            setNotEditable();
        }
    }
}
