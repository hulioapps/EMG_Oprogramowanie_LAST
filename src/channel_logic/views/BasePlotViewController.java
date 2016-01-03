package channel_logic.views;

import channel_logic.processing.Emitable;
import channel_logic.processing.Listenable;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Boss on 2015-12-05.
 */
public class BasePlotViewController extends JPanel implements Listenable {

    public BasePlotViewController(){
        setPreferredSize(new Dimension(1000,200));
    }
    double [][] plotData;
    @Override
    public void listen(double[][] data) {
        plotData = data;
        repaint();
    }

    @Override
    public void registerToParent(Emitable e) {
        e.registerChild(this);
    }

    @Override
    public void unregisterToParent(Emitable e) {
        e.unregisterChild(this);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        if(plotData!=null){
            for(int i =1;i<1000;i++){
                g.drawLine(
                        (int)(i-1),
                        (int)(100-plotData[i-1][1]),
                        (int)(i),
                        (int)(100-plotData[i][1]));
            }
        }

    }
}
