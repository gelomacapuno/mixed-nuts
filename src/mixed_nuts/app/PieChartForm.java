package mixed_nuts.app;

import mixed_nuts.util.DatabaseConnection;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleInsets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PieChartForm extends JFrame{

    public PieChartForm(){
        this.setUndecorated(true);
        this.setContentPane(rootPanel);
        rootPanel.setBackground(new Color(0x283469));
        rootPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ev) {
                posX = ev.getX();
                posY = ev.getY();
            }
        });
        rootPanel.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent evt) {
                setLocation(evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);
            }
        });
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(1000,700);
        this.setTitle("View Records by Department");
        this.setVisible(true);

        JFreeChart pieChart = ChartFactory.createPieChart("", createDataSet(),true,true,false);
        pieChart.setBackgroundPaint(new Color(0x4b5576));
        pieChart.setPadding(new RectangleInsets(40,0,0,0));
        pieChart.setBorderVisible(false);
        PiePlot plot = (PiePlot) pieChart.getPlot();
        ChartPanel chartPanel = new ChartPanel(pieChart);


        plot.setBackgroundPaint(new Color(0x4b5576));
        plot.setOutlinePaint(new Color(0x4b5576));
        plot.setLabelLinkPaint(Color.white);
        plot.setLabelBackgroundPaint(new Color(0x4d5579));
        plot.setLabelFont(new Font("Montserrat", Font.PLAIN, 20));
        plot.setLabelShadowPaint(null);
        plot.setLabelPaint(Color.white);
        plot.setLabelOutlinePaint(new Color(0x4d5579));

        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} = {1}");
        plot.setLabelGenerator(labelGenerator);


        graphPanel.removeAll();
        graphPanel.add(chartPanel, BorderLayout.CENTER);
        graphPanel.revalidate();

        for(c=0;c<10;c++){
            plot.setSectionPaint(deptset[c],new Color(palette[c]));
        }

        button1.addActionListener(e -> dispose());
        titleLabel.setFont(new Font("Montserrat", Font.BOLD, 36));
        button1.setFont(new Font("Montserrat", Font.BOLD, 26));
    }

    public int getValue(int j){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();

        String Cardio = "SELECT COUNT(*) FROM patientst WHERE department = '"+deptset[j]+"'";
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(Cardio);
            if(rs.next()) {
                count = rs.getInt("COUNT(*)");
            }
        }
        catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }
        return count;
    }

    @SuppressWarnings("removal")
    private PieDataset createDataSet(){
        DefaultPieDataset dataset = new DefaultPieDataset();
        for(i=0;i<10;i++){
            dataset.setValue(deptset[i], new Double(getValue(i)));
        }
        return dataset;
    }
    public int i,c;
    public int count;
    public String[] deptset = {"Cardiology", "Gastroenterology", "Gynecology", "Nephrology", "Neurology",
            "Oncology", "Ophthalmology", "Orthopaedics", "Otolaryngology", "Urology"};
    public int[] palette = {0x145DA0,0x0C2D48,0x2E8BC0,0xB1D4E0,0x2B5876, 0x808080, 0x55829B,0x00C0DA,0x272A2C,0x3C416F};
    private JPanel graphPanel;
    private JPanel rootPanel;
    private JButton button1;
    private JScrollPane ff;
    private JLabel titleLabel;
    private int posX, posY;
}
