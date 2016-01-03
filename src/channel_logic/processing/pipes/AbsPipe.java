package channel_logic.processing.pipes;

import java.util.concurrent.Executors;

/**
 * Created by Boss on 2015-12-05.
 */
public class AbsPipe extends Pipe {
    int number;

    public AbsPipe(int number) {
        this.number = number;
    }

    @Override
    protected void process(final double[][] data) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                for(double[] sample:data) {
                    data[i++][1] = Math.abs(sample[1]);
                }
                save(data);
                emit(data);
                }
        });
    }
}
