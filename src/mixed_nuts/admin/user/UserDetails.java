package mixed_nuts.admin.user;

import mixed_nuts.admin.AdminSearch;
import mixed_nuts.components.*;
import mixed_nuts.util.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDetails extends JFrame implements ActionListener{
    private MyTextField surnameField,givenField,middleField, usernameField, roleField,passwordField;
    private Font bente = new Font("Montserrat", Font.PLAIN, 20);
    private JComboBox<String> depts;
    private GridBag c;
    private RoundedPanel body_panel,smallFrame;
    private JPanel panel = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(panel);

    private int X = 0, Y = 0;
    private MyButton backButton,editButton,confirm,delete,cancel,confirmDelete;

    private String checker,lastname, firstname,midname,dep,pos,username,password,
    uppassword,upsurname,upgivenname,upmiddlename,upusername,updept;
    public UserDetails(){
        setSize(1280,720);
        setUndecorated(true);
        setResizable(false);
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setLayout(null);

        MyPanel titlePanel = new MyPanel(new Color(0x212C58),0,0,1280,169);
        titlePanel.setLayout(null);
        add(titlePanel);
        titlePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ev) {
                X = ev.getX();
                Y = ev.getY();
            }
        });
        titlePanel.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent evt) {
                setLocation(evt.getXOnScreen() - X, evt.getYOnScreen() - Y);
            }
        });


        titlePanel.add(backButton = new MyButton(new ImageIcon("back.png"),19,20,86,63,null,null));
        backButton.addActionListener(this);
        titlePanel.add(new MyLabel("User Details",Color.white,new Font("Helvetica",Font.PLAIN,40),510,30,260,54));
        titlePanel.add(editButton = new MyButton(new ImageIcon("edit.png"),1037,101,147,48,null,null));
        editButton.addActionListener(this);


        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(23,40,100,40)));
        panel.setBackground(new Color(0x212C58));
        panel.setVisible(true);
        panel.setBounds(0,169,1280,551);


        scrollPane.setVisible(true);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(1280,643));
        GetData();
        info();

        panel.add(Box.createRigidArea((new Dimension(5,15))));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setLayout(new ScrollPaneLayout());

        scrollPane.setBounds(0,169,1280,551);

        add(scrollPane);

    }
    private void GetData(){
        String identify;
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();
        if(AdminSearch.position.equals("D")){
            identify = "SELECT * FROM accountst a JOIN doctor_t d ON a.accountID =" +
                    " d.accountID WHERE a.accountID = '"+AdminSearch.value+"';";
        }else{
            identify = "SELECT * FROM accountst a JOIN nurse_t n ON a.accountID =" +
                    " n.accountID WHERE a.accountID = '"+AdminSearch.value+"';";
        }
        try{
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(identify);
            if(rs.next()){
                lastname = rs.getString("lastName");
                firstname = rs.getString("givenName");
                midname = rs.getString("middleName");
                dep = rs.getString("department");
                pos = rs.getString("empType");
                username = rs.getString("username");
                password = rs.getString("password");
            }
        }catch (Exception e){
            e.getCause();
            e.printStackTrace();
        }
        //gagawin ko pa lang to
    }


    private void info(){
        panel.add(body_panel = new RoundedPanel(50, new Color(0x4d5579)));
        body_panel.setOpaque(false);
        body_panel.setBorder(BorderFactory.createEmptyBorder());
        body_panel.setLayout(new GridBagLayout());
        body_panel.setVisible(true);
        c = new GridBag();

        c.weightx = 1.0;
        c.weighty = 0.0;

        c.setConstraints(GridBagConstraints.HORIZONTAL,0.5,0,0,1,20,33,2,0);
        body_panel.add(new MyLabel("Name:",Color.white,bente,0,0,0,0),c);
        c.setConstraints(GridBagConstraints.HORIZONTAL,1.0,1,1,1,15,33,2,0);
        body_panel.add(new MyLabel("Surname",Color.white,bente,4,0,0,0),c);

        surnameField = new MyTextField(null,0,0,0,0,bente);
        c.gridx = 1;
        c.gridy = 0;
        body_panel.add(surnameField,c);

        c.setConstraints(GridBagConstraints.BOTH,1.0,2,1,1,15,33,2,0);
        body_panel.add(new MyLabel("Given Name",Color.white,bente,4,0,0,0),c);

        givenField = new MyTextField(null,0,0,0,0,bente);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        body_panel.add(givenField,c);

        c.setConstraints(GridBagConstraints.BOTH,1.0,3,1,1,15,33,2,0);
        body_panel.add(new MyLabel("Middle Name",Color.white,bente,4,0,0,0),c);

        middleField = new MyTextField(null,0,0,0,0,bente);
        c.gridx = 3;
        c.gridy = 0;
        body_panel.add(middleField,c);

        c.setConstraints(GridBagConstraints.HORIZONTAL,1.0,4,0,1,20,33,2,0);
        body_panel.add(new MyLabel("",new Color(0x4d5579),bente,0,0,0,0),c);

        c.setConstraints(GridBagConstraints.BOTH,1.0,0,2,1,15,33,2,0);
        body_panel.add(new MyLabel("Department:",Color.white,bente,0,0,0,0),c);

        String[] department_list = {"Cardiology", "Gastroenterology", "Gynecology", "Nephrology",
                "Neurology", "Oncology", "Ophthalmology", "Orthopaedics", "Otolaryngology", "Urology"};

        depts = new JComboBox<>(department_list);
        depts.setFont(bente);
        depts.setSelectedItem(dep);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy =2;
        body_panel.add(depts,c);

        c.setConstraints(GridBagConstraints.BOTH,1,0,3,1,15,33,2,0);
        body_panel.add(new MyLabel("Role:",Color.white,bente,0,0,0,0),c);
        c.setConstraints(GridBagConstraints.BOTH,1,0,4,1,15,33,2,0);
        body_panel.add(new MyLabel("Username",Color.white,bente,0,0,0,0),c);

        usernameField = new MyTextField("",0,0,0,0,bente);
        c.gridx = 1;
        c.gridy = 4;
        body_panel.add(usernameField,c);



        c.setConstraints(GridBagConstraints.BOTH,1,0,5,1,15,33,2,0);
        body_panel.add(new MyLabel("Password",Color.white,bente,0,0,0,0),c);
        passwordField = new MyTextField("",0,0,0,0,bente);
        c.gridx = 1;
        c.gridy = 5;
        body_panel.add(passwordField,c);

        confirm = new MyButton(new ImageIcon("confirm_logo.png"),0,0,0,0,null,null);
        confirm.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 5;
        body_panel.add(confirm,c);

        //Transfer Data
        surnameField.setText(lastname);
        givenField.setText(firstname);
        middleField.setText(midname);
        usernameField.setText(username);
        passwordField.setText(password);
        if(pos.equals("D")){
            roleField = new MyTextField("Doctor",0,0,0,0,bente);
        }
        else if(pos.equals("N")){
            roleField = new MyTextField("Nurse",0,0,0,0,bente);
        }
        c.gridx = 1;
        c.gridy = 3;
        body_panel.add(roleField,c);

        delete = new MyButton(new ImageIcon("deletebutton.png"),0,0,0,0,null,null);
        delete.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
        delete.setAlignmentX(Component.CENTER_ALIGNMENT);
        delete.addActionListener(this);
        panel.add(delete);


        disableAllTextField();
    }

    private void disableAllTextField(){
        confirm.setVisible(false);
        passwordField.setEditable(false);
        roleField.setEnabled(false);
        surnameField.setEditable(false);
        givenField.setEditable(false);
        middleField.setEditable(false);
        depts.setEnabled(false);
        usernameField.setEditable(false);

    }

    private void setAllEditable(){
        usernameField.setEditable(true);
        confirm.setVisible(true);
        editButton.setVisible(false);
        passwordField.setEditable(true);
        surnameField.setEditable(true);
        givenField.setEditable(true);
        middleField.setEditable(true);

    }

    private void transferData(){
        upsurname = surnameField.getText();
        upgivenname = givenField.getText();
        upmiddlename = middleField.getText();
        updept = (String)depts.getSelectedItem();
        upusername = usernameField.getText();
        uppassword = passwordField.getText();
    }

    private void updateData(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();
        String updateAccount = "UPDATE accountst SET password = '"+uppassword+"', username = '" +
                upusername+ "' WHERE accountID = '"+AdminSearch.value+"';";
        String updateDoctor = "UPDATE doctor_t SET lastName = '"+upsurname+"',givenName = '"+upgivenname+
                "',middleName = '" +upmiddlename+"'" + "WHERE accountID = '"+AdminSearch.value+"';";
        String updateNurse = "UPDATE nurse_t SET lastName = '"+upsurname+"',givenName = '"+upgivenname+
                "',middleName = '" +upmiddlename+"'" + "WHERE accountID = '"+AdminSearch.value+"';";
        try{
            Statement st = connect.createStatement();
            st.executeUpdate(updateAccount);
            if(pos.equals("D")){
                st.executeUpdate(updateDoctor);
            }else{
                st.executeUpdate(updateNurse);
            }
            JOptionPane.showMessageDialog(null,"User Information has been updated");
            delete.setVisible(true);
            editButton.setVisible(true);
            disableAllTextField();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }
    private void deleteRec(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String delete = "DELETE FROM accountst WHERE accountID ='" + AdminSearch.value + "';";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(delete);
        }catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }
    }

    private void showConfirmation(){
        smallFrame = new RoundedPanel(30,new Color(0x212C58));
        smallFrame.setSize(500,300);
        smallFrame.setOpaque(false);
        smallFrame.setLayout(new FlowLayout());
        smallFrame.setVisible(true);
        smallFrame.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(smallFrame);

        //logo and title
        smallFrame.add(new ImageLabel(new ImageIcon("x_logo.png"),0,0,0,0));
        smallFrame.add(new MyLabel("Are you sure?",Color.white,new Font("Helvetica",Font.PLAIN,40),0,0,0,0));
        smallFrame.add(new MyLabel("<html><div style = 'text-align: center;'>Do you really want to delete this account?<br>This process cannot be undone.</div></html>",
                new Color(0x8891a6),new Font("Helvetica",Font.PLAIN,20),0,0,0,0));
        cancel = new MyButton(new ImageIcon("cancel.png"),0,0,0,0,null,null);
        smallFrame.add(cancel);
        cancel.addActionListener(this);
        confirmDelete =  new MyButton(new ImageIcon("confirm_logo.png"),0,0,0,0,null,null);
        smallFrame.add(confirmDelete);
        confirmDelete.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton)
            setVisible(false);

        if(e.getSource() == editButton){
            delete.setVisible(false);
            setAllEditable();
        }
        if(e.getSource() == confirm){
            transferData();
            if(upusername.equals(username)){
                AdminSearch.getTableData(new String[]{"","All"});
                updateData();
                AdminSearch.getTableData(new String[]{"","All"});
            }else{
                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connect = connectNow.getConnection();

                String Identify = "Select username from accountst WHERE username ='"
                        +upusername+ "';";
                try {
                    Statement st = connect.createStatement();
                    ResultSet rs = st.executeQuery(Identify);
                    if(rs.next()){
                        checker = rs.getString("username");
                    }if(upusername.equals(checker)){
                        JOptionPane.showMessageDialog(null,
                                "Username is already in use. Please change.");
                    }else{
                        AdminSearch.getTableData(new String[]{"","All"});
                        updateData();
                        AdminSearch.getTableData(new String[]{"","All"});
                    }
                }catch(Exception ex){
                    ex.getCause();
                    ex.printStackTrace();
                }

            }

        }
        if(e.getSource() == delete){
            editButton.setVisible(false);
            delete.setVisible(false);
            showConfirmation();
        }
        if(e.getSource() == cancel){
            smallFrame.setVisible(false);
            delete.setVisible(true);
            editButton.setVisible(true);
        }
        if(e.getSource() == confirmDelete){
            deleteRec();
            smallFrame.setVisible(false);
            delete.setVisible(true);
            AdminSearch.getTableData(new String[]{"","All"});
            JOptionPane.showMessageDialog(null,"User has been deleted!");
            dispose();
        }
    }


}
