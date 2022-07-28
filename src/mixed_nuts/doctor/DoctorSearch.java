package mixed_nuts.doctor;
import mixed_nuts.components.*;
import mixed_nuts.doctor.patient.PatientInformationDoctor;
import mixed_nuts.util.DatabaseConnection;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import static mixed_nuts.doctor.DoctorHome.getDoctorName;

public class DoctorSearch extends JPanel implements ActionListener {

    public DoctorSearch(){
        searchField();
        setLayout(null);
        setBackground(new Color(0x142959));
        setBGUI();
    }

    private void setBGUI(){
        add(new MyLabel("",Color.white,new Font("Helvetica", Font.PLAIN, 40),
                17,20,894,68));
        String greet = "Welcome Dr. " + getDoctorName();
        String[] user = {greet, "Change Password"};
        JComboBox<String> userMenu = new JComboBox<>(user);
        userMenu.setBounds(630,20,350,41);
        userMenu.setFont(new Font("Montserrat", Font.PLAIN, 22));
        add(userMenu);

        userMenu.addActionListener(e -> {
            if (userMenu.getSelectedItem().toString().equals("Change Password")){
                userMenu.setSelectedIndex(0);
                DoctorMenu.changePass();
            }
        });
        add(new ImageLabel(new ImageIcon("element_opacity.png"),248,25,817,660));
    }

    private void searchField(){
        panel = new MyPanel(new Color(255,255,255,120),15,75, 964,630){
            protected void paintComponent(Graphics g)
            {
                g.setColor( getBackground() );
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        add(panel);
        panel.setOpaque(false);
        panel.setLayout(null);
        panel.add(new MyLabel("Search:", Color.black,bente,25,22,78,33));
        panel.add(new MyLabel("Search By:", Color.black,bente,680,22,109,33));

        searchField = new MyTextField(null,120,22,531,40,bente);
        panel.add(searchField);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String[] t = {"",""};
                t[0] = searchField.getText();
                t[1] = sort_by.getSelectedItem().toString();
                getTableData(t);
            }
        });

        String[] sortBy = {"Name","Date"};
        sort_by = new JComboBox<>(sortBy);
        sort_by.setBounds(800,22, 100,40);
        sort_by.setFont(new Font("Montserrat", Font.PLAIN, 20));
        panel.add(sort_by);
        sort_by.setFocusable(false);


        sort_by.addActionListener(e -> {
            String[] t = {"",""};
            t[0] = searchField.getText();
            t[1] = sort_by.getSelectedItem().toString();
            getTableData(t);
        });

        makeTable();
        getTableData(new String[]{"","Name"});
    }

    public static void getTableData(String[] text){
        String patientinfo;

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        if(text[0].equals(""))
            patientinfo = "SELECT idpatient, lastName, givenName, middleName, " +
                    "dateOfCheckup FROM patientst WHERE department = '"+ DoctorMenu.dept+"';";
        else if(text[1].equals("Name"))
            patientinfo = "SELECT idpatient, lastName, givenName, middleName, " +
                    "dateOfCheckup FROM patientst WHERE department = '"+ DoctorMenu.dept+"' AND " +
                    "lastName LIKE '"+text[0]+"%'";
        else
            patientinfo = "SELECT idpatient, lastName, givenName, middleName, " +
                    "dateOfCheckup FROM patientst WHERE department = '"+ DoctorMenu.dept+"' AND " +
                    "dateOfCheckup LIKE '"+text[0]+"%'";
        try{
            model.setRowCount(0);
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(patientinfo);
            while(queryResult.next()){
                int id = queryResult.getInt("idpatient");
                String firstname = queryResult.getString("givenName");
                String middlename = queryResult.getString("middleName");
                String surname = queryResult.getString("lastName");
                String name = surname + ", " + firstname + " " + middlename;
                Date date = queryResult.getDate("dateOfCheckup");

                model.addRow(new Object[]{id,name,date});
            }
        }catch (Exception e){
            e.getCause();
            e.printStackTrace();
        }

    }

    private void makeTable(){
        //table variable naming and addColumn
        table = new JTable(new DefaultTableModel()){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        JScrollPane pane = new JScrollPane(table);
        model = (DefaultTableModel) table.getModel();
        model.addColumn("Patient No.");
        model.addColumn("Name");
        model.addColumn("Date of Checkup");

        //table configuration
        table.setTableHeader(new JTableHeader(table.getColumnModel()){
            public Dimension getPreferredSize(){
                Dimension d = super.getPreferredSize();
                d.height = 38;
                return d;
            }
        });
        table.setFont(new Font("Montserrat", Font.PLAIN, 20));
        table.clearSelection();
        table.setBackground(Color.white);
        table.setForeground(Color.BLACK);
        table.setRowHeight(38);
        table.setBorder(BorderFactory.createEmptyBorder());
        table.setGridColor(Color.black);
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(97,6));
        table.getTableHeader().setFont(new Font("Montserrat", Font.PLAIN, 20));
        table.getTableHeader().setBackground(new Color(0x4b5576));
        table.getTableHeader().setForeground(Color.white);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(230);
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumn("Patient No.").setCellRenderer(dtcr);
        table.getColumn("Name").setCellRenderer(dtcr);
        table.getColumn("Date of Checkup").setCellRenderer(dtcr);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pane.setVisible(true);
        pane.setBounds(24,105, 921,440);
        panel.add(pane);


        view = new MyButton(new ImageIcon("view.png"),722-286,656-100,
                123,41,null,new Color(0x888ca4));
        view.addActionListener(this);

        if(table.getSelectionModel().isSelectionEmpty()){
            view.setEnabled(false);
        }

        view.setOpaque(true);
        table.getSelectionModel().addListSelectionListener(e -> view.setEnabled(true));
        panel.add(view);



    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== view){
            int row = table.getSelectedRow();
            value = table.getModel().getValueAt(row,0).toString();
            date = table.getModel().getValueAt(row,2).toString();
            table.clearSelection();
            searchField.setText("");
            sort_by.setSelectedIndex(0);
            new PatientInformationDoctor();
        }
    }
    private MyButton view;
    private JTable table;
    private MyTextField searchField;
    private static DefaultTableModel model;
    public static String value,date;
    private JComboBox<String> sort_by;
    private final Font bente = new Font("Montserrat", Font.PLAIN, 20);
    private MyPanel panel;
}
