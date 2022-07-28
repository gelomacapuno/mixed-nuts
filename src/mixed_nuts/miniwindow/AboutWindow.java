package mixed_nuts.miniwindow;
import mixed_nuts.components.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AboutWindow extends JFrame {
        public AboutWindow() {
            setLayout(null);
            setSize(854, 480);
            setUndecorated(true);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            setVisible(true);

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

            JLabel about_title = new JLabel("About This Software");
            about_title.setForeground(Color.white);
            about_title.setFont(new Font("Montserrat", Font.PLAIN, 32));
            about_title.setVisible(true);
            about_title.setBounds(264, 36, 327, 54);
            title_panel.add(about_title);

            JPanel panel = new JPanel();
            panel.setLayout(null);
            //panel.setBorder(new EmptyBorder(new Insets(23, 40, 100, 40)));
            panel.setBackground(new Color(0x212C58));
            panel.setVisible(true);
            panel.setBounds(0, 104, 854, 376);
            add(panel);

            panel.add(roundedpanel = new RoundedPanel(50, new Color(0x4d5579)));
            roundedpanel.setBounds(23, 0, 808, 345);
            roundedpanel.setOpaque(false);
            roundedpanel.setLayout(null);

            roundedpanel.add(logo = new JLabel(new ImageIcon("aboutsymbol.png")));
            logo.setBounds(309, 14, 190, 123);

            healthbook = new JLabel(new ImageIcon("aboutlabel.png"));
            healthbook.setBounds(195, 153, 418, 45);
            roundedpanel.add(healthbook);


            JLabel message = new JLabel(
                    "<html><div style='text-align: center;'>The <b>HealthBook</b> aims to help our Clinics to have a software-based records<br>" +
                            "for patients and their medical records using database. It has features that stores,<br>" +
                            "manipulates and insert patient information and store them based on what storage " +
                            "location it is placed on. It is made as a requirement for the developers subject " +
                            "in their Object Oriented Programming.</div></html>");
            message.setFont(new Font("Montserrat", Font.PLAIN, 15));
            message.setForeground(Color.white);
            message.setBounds(42, 206, 724, 126);
            roundedpanel.add(message);

        }

        public JButton back;
        public RoundedPanel roundedpanel;
        public JLabel logo, healthbook;
        public int X, Y;
    }
