package mixed_nuts;

import mixed_nuts.components.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchForm extends JPanel implements ActionListener {
    private Font bente = new Font("Helvetica", Font.PLAIN, 20);
    private MyPanel panel;
    public MyButton logout;
    public SearchForm(){
        searchField();
        setLayout(null);
        setBGUI();
    }

    private void setBGUI(){
        setBackground(new Color(0x142959));


        add(new MyLabel("",Color.white,new Font("Helvetica", Font.PLAIN, 40),
                17,20,894,68));
        add(new ImageLabel(new ImageIcon("element_opacity.png"),248,25,817,660));
        add(logout = new MyButton(new ImageIcon("log_out.png"),841,20,123,41,null,null));
        logout.addActionListener(this);
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
        sort_by.setOpaque(false);
        sort_by.setUI(new MyComboBoxUI());
        sort_by.setFont(new Font("Helvetica", Font.PLAIN, 20));
        sort_by.setBackground(new Color(0x212C58));
        sort_by.setForeground(Color.white);

        sort_by.setRenderer(new DefaultListCellRenderer(){
            @Override
            public void paint(Graphics g) {
                setBackground(Color.white);
                setForeground(Color.black);
                super.paint(g);
            }
        });
        sort_by.setFocusable(false);
        sort_by.setBounds(800,22, 100,40);
        panel.add(sort_by);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu.close();
    }
}

class MyComboBoxUI extends BasicComboBoxUI {
    @Override
    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.uninstallBorder(comboBox); //Uninstalls the LAF border for both button and label of combo box.
    }

    @Override
    protected JButton createArrowButton() {
        //Feel free to play with the colors:
        final Color background = Color.white;
        final Color pressedButtonBorderColor = background;
        final Color triangle = Color.WHITE;
        final Color highlight = background;
        final JButton button = new BasicArrowButton(BasicArrowButton.SOUTH, background, pressedButtonBorderColor, triangle, highlight);
        button.setName("ComboBox.arrowButton"); //Mandatory, as per BasicComboBoxUI#createArrowButton().
        return button;
    }
}

