package graphics;

import channel_logic.views.BasePlotViewController;

import javax.swing.*;

/**
 * Created by Boss on 2015-12-05.
 */
public class MainFrame extends JFrame {
    public MainFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void addPanel(JPanel panel){
        getContentPane().add(panel);
        revalidate();
        pack();
    }

}
