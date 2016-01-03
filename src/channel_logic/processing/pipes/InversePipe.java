package channel_logic.processing.pipes;

import channel_logic.processing.pipes.Pipe;

import java.util.concurrent.Executors;

/**
 * Created by Boss on 2015-10-18.
 */
public class InversePipe extends Pipe {
    int number;
    int order = 0;
    public InversePipe(int number) {
        this.number = number;
    }



    @Override
    protected void process(final double[][] data) {

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                double [][] inversedData = new double [data.length][2];
                for(double[] sample:data) {
                    data[i++][1] = -sample[1];
                }
                save(data);
                emit(data);
            }
        });

    }
}
