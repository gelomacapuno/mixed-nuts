package mixed_nuts.doctor;
import mixed_nuts.components.ImageLabel;
import mixed_nuts.components.MyLabel;
import mixed_nuts.components.MyPanel;
import mixed_nuts.components.MyTextField;
import mixed_nuts.nurse.NurseMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorSearch extends JPanel implements ActionListener {
    private final Font bente = new Font("Helvetica", Font.PLAIN, 20);
    private MyPanel panel;

    public DoctorSearch(){
        searchField();
        makeTable();
        setLayout(null);
        setBackground(new Color(0x142959));
        setBGUI();
    }

    private void setBGUI(){
        add(new MyLabel("",Color.white,new Font("Helvetica", Font.PLAIN, 40),
                17,20,894,68));

        String[] user = {"Welcome back! <user>", "Change Password", "Logout"};
        JComboBox<String> userMenu = new JComboBox<>(user);
        userMenu.setBounds(630,20,350,41);
        userMenu.setFont(new Font("Helvetica", Font.PLAIN, 22));
        add(userMenu);

        userMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userMenu.getSelectedItem().toString().equals("Change Password")){
                    userMenu.setSelectedIndex(0);
                    DoctorMenu.changePass();
                }
                if (userMenu.getSelectedItem().toString().equals("Logout")){
                    userMenu.setSelectedIndex(0);
                    DoctorMenu.close();
                }
            }
        });
        add(new ImageLabel(new ImageIcon("element_opacity.png"),248,25,817,660));
    }

    private void searchField(){
        add(panel = new MyPanel(new Color(255,255,255,120),15,75, 964,630));
        panel.setLayout(null);
        panel.add(new MyLabel("Search:", Color.black,bente,25,22,78,33));
        panel.add(new MyLabel("Search By:", Color.black,bente,680,22,109,33));

        MyTextField searchField = new MyTextField(null,120,22,531,40,bente);
        panel.add(searchField);

        String[] sortBy = {"Name","Date"};
        JComboBox<String> sort_by = new JComboBox<>(sortBy);
        sort_by.setBounds(800,22, 100,40);
        sort_by.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(sort_by);
    }

    private void makeTable(){
        //table variable naming and addColumn
        JTable table = new JTable(new DefaultTableModel());
        JScrollPane pane = new JScrollPane(table);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
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


        //sample data
        model.addRow(new Object[]{1,"Angelo Macapuno","2020-12-12"});
        model.addRow(new Object[]{2,"Anne Marie Therese Alejandro","2020-12-13"});
        model.addRow(new Object[]{3,"Kenneth Matthew Aboleda","2020-12-14"});
        model.addRow(new Object[]{4,"Hazel Luna Brizo","2020-12-15"});
        model.addRow(new Object[]{5,"John Leonardo Larraga","2020-12-15"});
        model.addRow(new Object[]{6,"Charles Joseph Tolentino","2020-12-15"});
        model.addRow(new Object[]{7,"Janiza Kane Cortez","2020-12-16"});
        model.addRow(new Object[]{8,"Alexander James Cebreiros","2020-12-18"});
        model.addRow(new Object[]{9,"Jhaymie Ross Dagohoy","2021-01-2"});
        model.addRow(new Object[]{10,"Janel Aguilar","2021-01-2"});
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
