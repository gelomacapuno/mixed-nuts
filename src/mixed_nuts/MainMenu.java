package mixed_nuts;
import mixed_nuts.components.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MainMenu  implements ActionListener {
    private int posX, posY;
    private MyButton addButton,searchButton, homeButton;
    public static JFrame frame = new JFrame();
    public final CardLayout cl = new CardLayout(); //for setLayout();
    public final MyPanel cardPanel = new MyPanel(new Color(0x283469),286,0,994,720);
    public MainMenu(){
        setNavPanel();
        setDisplayPanel();
        frame.setSize(1280, 720);
        frame.setLayout(null);
        frame.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setVisible(true);

    }
    private void setNavPanel(){
        MyButton help, about;
        MyPanel navPanel = new MyPanel(new Color(0xFFAE52),0, 40, 286, 720);
        MyPanel Spacer = new MyPanel(new Color(0xFFAE52),0,0,286,40);
        navPanel.setLayout(null);
        navPanel.add(homeButton = new MyButton(null,0,0,286,56,null,null));
        navPanel.add(addButton = new MyButton(null,0,57,286,56,null,null));
        navPanel.add(searchButton = new MyButton(null,0,114,286,56,null,null));
        navPanel.add(help = new MyButton(new ImageIcon("help.png"),30,590,41,41,null,null));
        navPanel.add(about = new MyButton(new ImageIcon("about.png"),85,590,41,41,null,null));

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
                frame.setLocation(evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);
            }
        });
        help.navButton(0, null,
                BorderFactory.createEmptyBorder(0, 0, 0, 0),new Color(0x142959));
        about.navButton(0,null,
                BorderFactory.createEmptyBorder(0, 0, 0, 0),new Color(0x142959));
        homeButton.navButton(25,"HOME",
                BorderFactory.createEmptyBorder(0, 31, 0, 0),new Color(0x142959));
        homeButton.setBackground(new Color(0xF7D8B7));
        addButton.navButton(25,"ADD PATIENT",
                BorderFactory.createEmptyBorder(0, 31, 0, 0),new Color(0x142959));
        searchButton.navButton(23, "SEARCH PATIENT",
                BorderFactory.createEmptyBorder(0, 29, 0, 0),new Color(0x142959));
        help.addActionListener(e -> new HelpWindow());
        about.addActionListener(e -> new AboutWindow());
        homeButton.addActionListener(this);
        addButton.addActionListener(this);
        searchButton.addActionListener(this);



        frame.add(Spacer);
        frame.add(navPanel);
    }
    private void setActiveHoverButton(JButton button){
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

    private void setHoverButton(JButton button){
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
        cardPanel.add("Home",new HomePage());
        cardPanel.add("add",new AddForm());
        cardPanel.add("search", new SearchForm());
        cardPanel.setBounds(286,0,994,720);
        cl.show(cardPanel,"Home");
        setActiveHoverButton(homeButton);
        setHoverButton(addButton);
        setHoverButton(searchButton);
        frame.add(cardPanel);
    }

    private void hideAllDisplayPanel(){
        homeButton.setBackground(new Color(0xFFAE52));
        addButton.setBackground(new Color(0xFFAE52));
        searchButton.setBackground(new Color(0xFFAE52));
    }

    public static void close(){
        frame.dispose();
        new LoginForm();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == homeButton){
            hideAllDisplayPanel();
            setActiveHoverButton(homeButton);
            setHoverButton(searchButton);
            setHoverButton(addButton);
            cl.show(cardPanel, "Home");
            homeButton.setBackground(new Color(0xF7D8B7));
        }

        if(e.getSource() == addButton){
            setActiveHoverButton(addButton);
            setHoverButton(searchButton);
            setHoverButton(homeButton);
            hideAllDisplayPanel();
            cl.show(cardPanel, "add");
            addButton.setBackground(new Color(0xF7D8B7));
        }

        if(e.getSource() == searchButton) {
            setActiveHoverButton(searchButton);
            setHoverButton(homeButton);
            setHoverButton(addButton);
            hideAllDisplayPanel();
            cl.show(cardPanel, "search");
            searchButton.setBackground(new Color(0xF7D8B7));
        }

}
    }
