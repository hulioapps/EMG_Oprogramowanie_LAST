package channel_logic.processing.pipes;

import channel_logic.CyclicBuffer;

import java.util.concurrent.Executors;

/**
 * Created by Boss on 2016-01-03.
 */
public class MovAvgPipe extends Pipe {

    TYPE pipeType = TYPE.SIMPLE;
    CyclicBuffer lastInputs;

    int order;
    double [] weights;
    public enum TYPE{
        SIMPLE,
        WEIGHTED,
        EXPONENTIAL
    }

    public MovAvgPipe(int order){
        this.order = order;
        lastInputs = new CyclicBuffer(order);
    }

    public MovAvgPipe(TYPE selectedPipeType, int order){
        pipeType = selectedPipeType;
        this.order = order;
        double [] weights = new double[order];
        lastInputs = new CyclicBuffer(order);
    }

    @Override
    protected void process(double[][] data) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                   switch (pipeType){
                       case SIMPLE:
                            lastInputs.getSamples();

                           break;
                       case WEIGHTED:


                           break;
                       case EXPONENTIAL:


                           break;
                   }


                save(null);
                emit(null);
            }
        });
    }

    private void pipeWeightGenerator(){

    }

}
