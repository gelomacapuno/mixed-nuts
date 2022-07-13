package mixed_nuts.admin;

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

public class AdminSearch extends JPanel {
    private final Font bente = new Font("Helvetica", Font.PLAIN, 20);
    private MyPanel panel;
    public AdminSearch(){
        searchField();
        makeTable();
        setLayout(null);
        setBGDesign();
    }

    private void setBGDesign(){
        setBackground(new Color(0x142959));

        add(panel = new MyPanel(new Color(255,255,255,120),15,75, 964,630));
        String[] user = {"Welcome back! Admin", "Change Password", "Logout"};
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
                if (userMenu.getSelectedItem().toString().equals("Logout")){
                    userMenu.setSelectedIndex(0);
                    AdminMenu.close();
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
        table.getColumnModel().getColumn(0).setPreferredWidth(250);
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumn("Name").setCellRenderer(dtcr);
        table.getColumn("Employee Type").setCellRenderer(dtcr);
        table.getColumn("Department").setCellRenderer(dtcr);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pane.setVisible(true);
        pane.setBounds(24,105, 921,440);
        panel.add(pane);


        //sample data
        model.addRow(new Object[]{"Angelo Macapuno","Doctor","Cardiology"});
        model.addRow(new Object[]{"Anne Marie Therese Alejandro","Doctor","Oncology"});
        model.addRow(new Object[]{"Kenneth Matthew Aboleda","Nurse","Urology"});
        model.addRow(new Object[]{"Hazel Luna Brizo","Nurse","Nephrology"});
        model.addRow(new Object[]{"John Leonardo Larraga","Nurse","Orthopaedics"});
        model.addRow(new Object[]{"Charles Joseph Tolentino","Doctor","Gynecology"});
        model.addRow(new Object[]{"Janiza Kane Cortez","Doctor","Neurology"});
        model.addRow(new Object[]{"Alexander James Cebreiros","Nurse","Oncology"});
        model.addRow(new Object[]{"Jhaymie Ross Dagohoy","Nurse","Cardiology"});
        model.addRow(new Object[]{"Janel Aguilar","Doctor","Nephrology"});
    }
}
