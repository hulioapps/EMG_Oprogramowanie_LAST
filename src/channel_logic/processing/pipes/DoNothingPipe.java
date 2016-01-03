package channel_logic.processing.pipes;

import channel_logic.processing.pipes.Pipe;

import java.util.concurrent.Executors;

/**
 * Created by Boss on 2015-08-20.
 */
public class DoNothingPipe extends Pipe {
    int number;

    public DoNothingPipe(int number) {
        this.number = number;
    }

    @Override
    protected void process(final double[][] data) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Im pipe "+number);
                save(data);
                emit(data);
            }
        });
    }
}

