package channel_logic.views;

/**
 * Created by Boss on 2015-10-18.
 */
public class DoNothingViewController extends BaseViewController {
    @Override
    public void listen(double[][] data) {
        System.out.println("output is " + data[0][1]);
    }
}
