package mixed_nuts.components;
import java.awt.*;
public class GridBag extends GridBagConstraints {
    public GridBag(){
        this.fill = GridBagConstraints.BOTH;
    }
    public void setConstraints(double weightX, int gridX, int gridY, int gridWidth, int t, int l, int b, int r){
        this.weightx = weightX;
        this.gridx = gridX;
        this.gridy = gridY;
        this.gridwidth = gridWidth;
        this.insets = new Insets(t,l,b,r);
    }
    public void setConstraints(int gridX, int gridY, int gridWidth, int t, int l, int b, int r){
        this.gridx = gridX;
        this.gridy = gridY;
        this.gridwidth = gridWidth;
        this.insets = new Insets(t,l,b,r);
    }
    public void setConstraints(int fill, double weightX, int gridX, int gridY, int gridWidth, int t, int l, int b, int r){
        this.fill = fill;
        this.weightx = weightX;
        this.gridx = gridX;
        this.gridy = gridY;
        this.gridwidth = gridWidth;
        this.insets = new Insets(t,l,b,r);
    }
    public void setConstraints(int gridX, int gridY, int t, int l, int b, int r){
        this.gridx = gridX;
        this.gridy = gridY;
        this.insets = new Insets(t,l,b,r);
    }
}
