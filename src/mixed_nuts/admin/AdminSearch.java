package mixed_nuts.admin;

import mixed_nuts.admin.user.UserDetails;
import mixed_nuts.components.*;
import mixed_nuts.util.DatabaseConnection;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class AdminSearch extends JPanel implements ActionListener{
    public static String value, position;
    private MyButton view;
    private JTable table;
    public static DefaultTableModel model;
    private final Font bente = new Font("Helvetica", Font.PLAIN, 20);
    private MyPanel panel;
    public AdminSearch(){
        searchField();
        setLayout(null);
        setBGDesign();
    }

    private void setBGDesign(){
        setBackground(new Color(0xFFAE52));

        add(panel);
        panel = new MyPanel(new Color(255,255,255,120),15,75, 964,630){
            protected void paintComponent(Graphics g)
            {
                g.setColor( getBackground() );
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        String[] user = {"Welcome back! Admin", "Change Password"};
        JComboBox<String> userMenu = new JComboBox<>(user);
        userMenu.setBounds(630,20,350,41);
        userMenu.setFont(new Font("Helvetica", Font.PLAIN, 22));
        add(userMenu);

        userMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userMenu.getSelectedItem().toString().equals("Change Password")){
                    userMenu.setSelectedIndex(0);
                    AdminMenu.changePass();
                }

            }
        });

        add(new ImageLabel(new ImageIcon("admin_element.png"),248,25,817,660));

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

        MyTextField searchField = new MyTextField(null,120,22,531,40,bente);
        panel.add(searchField);


        String[] sortBy = {"All","Cardiology","Gastroenterology","Gynecology","Nephrology",
        "Neurology","Oncology","Ophthalmology","Orthopaedics","Otolaryngology","Urology"};
        JComboBox<String> sort_by = new JComboBox<>(sortBy);
        sort_by.setBounds(800,22, 100,40);
        sort_by.setFont(new Font("Helvetica", Font.PLAIN, 20));
        sort_by.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] t = {"",""};
                t[0] = searchField.getText();
                t[1] = sort_by.getSelectedItem().toString();
                getTableData(t);
            }
        });
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
                getTableData(new String[]{searchField.getText(),sort_by.getSelectedItem().toString()});
            }
        });
        panel.add(sort_by);
        makeTable();
        getTableData(new String[]{"","All"});
    }

    private void makeTable(){
        //table variable naming and addColumn
        table = new JTable(new DefaultTableModel()){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        table.clearSelection();
        JScrollPane pane = new JScrollPane(table);
        model = (DefaultTableModel) table.getModel();
        model.addColumn("id");
        model.addColumn("Name");
        model.addColumn("Employee Type");
        model.addColumn("Department");

        //table configuration
        table.setTableHeader(new JTableHeader(table.getColumnModel()){
            public Dimension getPreferredSize(){
                Dimension d = super.getPreferredSize();
                d.height = 38;
                return d;
            }
        });
        table.setFont(new Font("Helvetica", Font.PLAIN, 20));
        table.clearSelection();
        table.setBackground(Color.white);
        table.setForeground(Color.BLACK);
        table.setRowHeight(38);
        table.setBorder(BorderFactory.createEmptyBorder());
        table.setGridColor(Color.black);
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(97,6));
        table.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN, 20));
        table.getTableHeader().setBackground(new Color(0x4b5576));
        table.getTableHeader().setForeground(Color.white);
        table.getColumnModel().getColumn(1).setPreferredWidth(240);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumn("id").setCellRenderer(dtcr);
        table.getColumn("Name").setCellRenderer(dtcr);
        table.getColumn("Employee Type").setCellRenderer(dtcr);
        table.getColumn("Department").setCellRenderer(dtcr);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        pane.setVisible(true);
        pane.setBounds(24,105, 921,400);
        panel.add(pane);

        view = new MyButton(new ImageIcon("view.png"),423,550,123,41,null,new Color(0xffd4a4));
        panel.add(view);
        view.setOpaque(true);
        view.setEnabled(false);
        view.addActionListener(this);

        if(table.getSelectionModel().isSelectionEmpty()){
            view.setEnabled(false);
        }
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                table.clearSelection();
                view.setEnabled(false);

            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                view.setEnabled(true);

            }

    });}
    public static void getTableData(String[] text){
        String userinfo;
        model.setRowCount(0);
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            if(text[1].equals("All")){
                if(text[0].equals(""))
                    userinfo = "SELECT * FROM accountst a JOIN nurse_t n ON a.accountID = n.accountID\n" +
                            "UNION \n" +
                            "SELECT * FROM accountst a JOIN doctor_t d ON a.accountID = d.accountID";
                else
                    userinfo = "SELECT * FROM accountst a JOIN nurse_t n ON a.accountID = n.accountID WHERE n.lastName LIKE '"
                                + text[0] + "%' UNION " + "SELECT * FROM accountst a JOIN doctor_t d ON a.accountID = " +
                            "d.accountID WHERE d.lastName LIKE '" + text[0] +"%';";
            }else{
                if(text[0].equals(""))
                    userinfo ="SELECT * FROM accountst a JOIN nurse_t n ON a.accountID = n.accountID " +
                            "WHERE  a.department = '"+text[1]+"' UNION SELECT * FROM accountst a " +
                            "JOIN doctor_t d ON a.accountID = d.accountID " +
                            "WHERE  a.department = '"+text[1]+"';";
                else
                    userinfo = "SELECT * FROM accountst a JOIN nurse_t n ON a.accountID = n.accountID " +
                            "WHERE n.lastName LIKE '"+text[0]+"' AND a.department = '"+text[1]+"' UNION SELECT * FROM accountst a " +
                            "JOIN doctor_t d ON a.accountID = d.accountID " +
                            "WHERE d.lastName LIKE '"+text[0]+"%' AND a.department = '"+text[1]+"';";
            }
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(userinfo);
            while(queryResult.next()){
                int id = queryResult.getInt("accountID");
                String firstname = queryResult.getString("givenName");
                String middlename = queryResult.getString("middleName");
                String surname = queryResult.getString("lastName");
                String name = surname + ", " + firstname + " " + middlename;
                String type = queryResult.getString("empType");
                String dept = queryResult.getString("department");

                model.addRow(new Object[]{id,name,type,dept});
            }
        }catch (Exception e){
            //pass
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view){
            int row = table.getSelectedRow();
            value = table.getModel().getValueAt(row,0).toString();
            position = table.getModel().getValueAt(row,2).toString();
            new UserDetails();
        }
    }
}
