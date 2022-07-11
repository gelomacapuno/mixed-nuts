package mixed_nuts;
import mixed_nuts.components.MyButton;
import mixed_nuts.components.MyLabel;
import mixed_nuts.components.MyPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AboutWindow extends JFrame{
    public AboutWindow(){
        setLayout(null);
        setSize(854,480);
        setUndecorated(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);

        MyPanel titlePanel = new MyPanel(new Color(0x212C58),0,0,854,104);
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

        MyButton back = new MyButton(new ImageIcon("back.png"),22,26,86,63,null,null);
        back.setVisible(true);
        back.addActionListener(e -> dispose());
        titlePanel.add(back);
        MyLabel aboutTitle = new MyLabel("About This Software",Color.white,new Font("Helvetica",
                Font.PLAIN, 36),264,36,327,54);
        titlePanel.add(aboutTitle);
        MyPanel panel = new MyPanel(new Color(0x212C58),0,104,854,376);
        panel.setLayout(null);
        add(panel);
        panel.add(roundedpanel = new RoundedPanel(50,new Color(0x4d5579)));
        roundedpanel.setBounds(23,0,808,345);
    }

    public RoundedPanel roundedpanel;
    public int X,Y;

    static class RoundedPanel extends JPanel {
        private Color backgroundColor;
        private int cornerRadius = 15;

        public RoundedPanel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
            setLayout(null);
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //Draws the rounded panel with borders.
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setColor(getBackground());
            }
            graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
            graphics.setColor(getForeground());
            graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
        }
    }


}

