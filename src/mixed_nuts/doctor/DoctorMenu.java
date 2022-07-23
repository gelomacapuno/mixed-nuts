package mixed_nuts.doctor;
import mixed_nuts.miniwindow.AboutWindow;
import mixed_nuts.miniwindow.HelpWindow;
import mixed_nuts.app.LoginForm;
import mixed_nuts.components.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class DoctorMenu extends JFrame implements ActionListener {

    public DoctorMenu(String ida,String position,String dept){
        DoctorMenu.ida = ida;
        this.position = position;
        DoctorMenu.dept = dept;
        setNavPanel();
        setDisplayPanel();
        setSize(1280, 720);
        setLayout(null);
        setResizable(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);

    }
    private void setNavPanel(){
        MyButton help, about,power;
        MyPanel navPanel = new MyPanel(new Color(0xFFAE52),0, 40, 286, 720);
        MyPanel Spacer = new MyPanel(new Color(0xFFAE52),0,0,286,40);
        navPanel.setLayout(null);
        navPanel.add(homeButton = new MyButton(null,0,0,286,56,null,null));
        navPanel.add(searchButton = new MyButton(null,0,57,286,56, null,null));
        navPanel.add(help = new MyButton(new ImageIcon("help.png"),30,590,41,41,null,null));
        navPanel.add(about = new MyButton(new ImageIcon("about.png"),85,590,41,41,null,null));
        navPanel.add(power = new MyButton(new ImageIcon("power_button.png"),150,590,41,41,null,null));

        navPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ev) {
                posX = ev.getX();
                posY = ev.getY();
            }
        });
        navPanel.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent evt) {
                setLocation(evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);
            }
        });
        help.navButton(0, null,
                BorderFactory.createEmptyBorder(0, 0, 0, 0),new Color(0x142959));
        about.navButton(0,null,
                BorderFactory.createEmptyBorder(0, 0, 0, 0),new Color(0x142959));
        power.navButton(0, null,
                BorderFactory.createEmptyBorder(0, 0, 0, 0),new Color(0x142959));
        homeButton.navButton(25,"HOME",
                BorderFactory.createEmptyBorder(0, 31, 0, 0),new Color(0x142959));
        homeButton.setBackground(new Color(0xF7D8B7));
        searchButton.navButton(23, "SEARCH PATIENT",
                BorderFactory.createEmptyBorder(0, 29, 0, 0),new Color(0x142959));
        help.addActionListener(e -> new HelpWindow());
        about.addActionListener(e -> new AboutWindow());
        power.addActionListener(e -> close());
        homeButton.addActionListener(this);
        searchButton.addActionListener(this);

        add(Spacer);
        add(navPanel);
    }
    private static void setActiveHoverButton(JButton button){
        button.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                button.setBackground(new Color(0xF7D8B7));
            }
            public void mouseExited(MouseEvent evt)
            {
                button.setBackground(new Color(0xF7D8B7));
            }
        });
    }

    private static void setHoverButton(JButton button){
        button.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                button.setBackground(new Color(0xF7D8B7));
            }
            public void mouseExited(MouseEvent evt)
            {
                button.setBackground(new Color(0xFFAE52));
            }
        });
    }
    private void setDisplayPanel(){

        cardPanel.setLayout(cl);
        cardPanel.add("Home",new DoctorHome());
        cardPanel.add("search", new DoctorSearch());
        cardPanel.add("Change",new DoctorChange());
        cardPanel.setBounds(286,0,994,720);
        cl.show(cardPanel,"Home");
        setActiveHoverButton(homeButton);
        setHoverButton(searchButton);
        add(cardPanel);
    }

    private static void hideAllDisplayPanel(){
        homeButton.setBackground(new Color(0xFFAE52));
        searchButton.setBackground(new Color(0xFFAE52));
    }

    public void close(){
        dispose();
        new LoginForm();
    }


    public static void changePass(){
        cl.show(cardPanel,"Change");
        hideAllDisplayPanel();
        setHoverButton(homeButton);
        setHoverButton(searchButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == homeButton){
            hideAllDisplayPanel();
            setActiveHoverButton(homeButton);
            setHoverButton(searchButton);
            cl.show(cardPanel, "Home");
            homeButton.setBackground(new Color(0xF7D8B7));
        }


        if(e.getSource() == searchButton) {
            setActiveHoverButton(searchButton);
            setHoverButton(homeButton);
            hideAllDisplayPanel();
            cl.show(cardPanel, "search");
            searchButton.setBackground(new Color(0xF7D8B7));
        }
    }
    private int posX, posY;
    public static String ida;
    public String position;
    public static String dept;
    private static MyButton searchButton;
    private static MyButton homeButton;
    public static final CardLayout cl = new CardLayout(); //for setLayout();
    public static final MyPanel cardPanel = new MyPanel(new Color(0x283469),286,0,994,720);
}
