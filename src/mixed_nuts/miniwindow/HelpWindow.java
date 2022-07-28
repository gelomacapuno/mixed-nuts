package mixed_nuts.miniwindow;
import mixed_nuts.components.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HelpWindow extends JFrame{
        public HelpWindow(){
            setLayout(null);
            setSize(854,480);
            setUndecorated(true);

            setVisible(true);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

            JPanel title_panel = new JPanel();
            title_panel.setLayout(null);
            title_panel.setBackground(new Color(0x212C58));
            title_panel.setBounds(0, 0, 854, 104);
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

            back = new JButton(new ImageIcon("back.png"));
            back.setContentAreaFilled(false);
            back.setVisible(true);
            back.setFocusPainted(false);
            back.setBorder(BorderFactory.createEmptyBorder());
            back.setBounds(22, 26, 86, 63);
            back.addActionListener(e -> dispose());
            title_panel.add(back);

            JLabel about_title = new JLabel("Help");
            about_title.setForeground(Color.white);
            about_title.setFont(new Font("Montserrat", Font.BOLD, 36));
            about_title.setVisible(true);
            about_title.setBounds(390, 38, 327, 54);
            title_panel.add(about_title);

            JPanel panel = new JPanel();
            panel.setLayout(null);
            //panel.setBorder(new EmptyBorder(new Insets(23, 40, 100, 40)));
            panel.setBackground(new Color(0x212C58));
            panel.setVisible(true);
            panel.setBounds(0, 104, 854, 376);
            add(panel);

            panel.add(roundedpanel = new RoundedPanel(50,new Color(0x4d5579)));
            roundedpanel.setBounds(31,0,793,310);
            roundedpanel.setOpaque(false);
            roundedpanel.setLayout(null);

            JLabel message = new JLabel(
                    "<html><div style='text-align: center;'>" +
                            "The Information System is made for the purpose of storing Patient Medical Information in a Clinic Hub Environment. " +
                            "The following are the" +
                            " positions and functionalities for employees:</div></html>");
            message.setFont(new Font("Montserrat", Font.PLAIN, 15));
            message.setForeground(Color.white);
            message.setBounds(162, 21, 470, 62);
            roundedpanel.add(message);

            //for Nurse part:
            JLabel nurseaccountLabel = new JLabel("Nurse Account:");
            nurseaccountLabel.setFont(new Font("Roboto", Font.BOLD, 15));
            nurseaccountLabel.setForeground(Color.white);
            nurseaccountLabel.setBounds(35, 98, 118, 23);
            roundedpanel.add(nurseaccountLabel);

            JLabel addPatienttLabel = new JLabel("Add Patient");
            addPatienttLabel.setFont(new Font("Roboto", Font.BOLD, 12));
            addPatienttLabel.setForeground(Color.white);
            addPatienttLabel.setBounds(35, 126, 65, 12);
            roundedpanel.add(addPatienttLabel);

            JLabel addPatientHelptLabel = new JLabel("<html><u>Able to Add New Patients</u></html>");
            addPatientHelptLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
            addPatientHelptLabel.setForeground(Color.white);
            addPatientHelptLabel.setBounds(77, 150, 145, 18);
            roundedpanel.add(addPatientHelptLabel);

            JLabel searchPatienttLabel = new JLabel("Search Patient");
            searchPatienttLabel.setFont(new Font("Roboto", Font.BOLD, 12));
            searchPatienttLabel.setForeground(Color.white);
            searchPatienttLabel.setBounds(35, 173, 96, 14);
            roundedpanel.add(searchPatienttLabel);

            JLabel searchPatientHelptLabel = new JLabel("<html><u>Able to Add New Records for Old Patient</u></html>");
            searchPatientHelptLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
            searchPatientHelptLabel.setForeground(Color.white);
            searchPatientHelptLabel.setBounds(77, 197, 222, 18);
            roundedpanel.add(searchPatientHelptLabel);

            JLabel editPatienttLabel = new JLabel("Edit Patient Info");
            editPatienttLabel.setFont(new Font("Roboto", Font.BOLD, 12));
            editPatienttLabel.setForeground(Color.white);
            editPatienttLabel.setBounds(35, 219, 96, 14);
            roundedpanel.add(editPatienttLabel);

            JLabel editPatienttHelptLabel = new JLabel("<html><u>Able to Edit Records but needs Admin Authentication</u></html>");
            editPatienttHelptLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
            editPatienttHelptLabel.setForeground(Color.white);
            editPatienttHelptLabel.setBounds(77, 242, 290, 18);
            roundedpanel.add(editPatienttHelptLabel);


            //for Doctor part:
            JLabel docaccountLabel = new JLabel("Doctor Account:");
            docaccountLabel.setFont(new Font("Roboto", Font.BOLD, 15));
            docaccountLabel.setForeground(Color.white);
            docaccountLabel.setBounds(433, 98, 118, 23);
            roundedpanel.add(docaccountLabel);

            JLabel docSearchtLabel = new JLabel("Search Patient");
            docSearchtLabel.setFont(new Font("Roboto", Font.BOLD, 12));
            docSearchtLabel.setForeground(Color.white);
            docSearchtLabel.setBounds(433, 126, 85, 19);
            roundedpanel.add(docSearchtLabel);

            JLabel docSearchHelptLabel = new JLabel("<html><u>Search Patients in their Assigned Departments</u></html>");
            docSearchHelptLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
            docSearchHelptLabel.setForeground(Color.white);
            docSearchHelptLabel.setBounds(475, 150, 265, 18);
            roundedpanel.add(docSearchHelptLabel);

            JLabel docEditLabel = new JLabel("Edit Patient Info");
            docEditLabel.setFont(new Font("Roboto", Font.BOLD, 12));
            docEditLabel.setForeground(Color.white);
            docEditLabel.setBounds(433, 173, 99, 19);
            roundedpanel.add(docEditLabel);

            JLabel docEditHelptLabel = new JLabel("<html><u>Edit Symptoms, Diagnosis and Medication after Selection</u></html>");
            docEditHelptLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
            docEditHelptLabel.setForeground(Color.white);
            docEditHelptLabel.setBounds(475, 192, 349, 18);
            roundedpanel.add(docEditHelptLabel);

            roundedpanel.add(logo1 = new JLabel(new ImageIcon("arrow.png")));
            logo1.setBounds(46, 146, 22, 22);
            roundedpanel.add(logo2 = new JLabel(new ImageIcon("arrow.png")));
            logo2.setBounds(46, 192, 22, 22);
            roundedpanel.add(logo3 = new JLabel(new ImageIcon("arrow.png")));
            logo3.setBounds(46, 238, 22, 22);
            roundedpanel.add(logo4 = new JLabel(new ImageIcon("arrow.png")));
            logo4.setBounds(444, 146, 22, 22);
            roundedpanel.add(logo5 = new JLabel(new ImageIcon("arrow.png")));
            logo5.setBounds(444, 192, 22, 22);

        }

        public JButton back;
        public RoundedPanel roundedpanel;
        public JLabel logo1,logo2,logo3,logo4,logo5;
        public int X,Y;
}

