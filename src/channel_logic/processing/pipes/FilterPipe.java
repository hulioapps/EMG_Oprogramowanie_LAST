package channel_logic.processing.pipes;

import biz.source_code.dsp.filter.*;
import channel_logic.CyclicBuffer;
import channel_logic.processing.pipes.Pipe;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.concurrent.Executors;

/**
 * Created by Boss on 2015-12-06.
 */
public class FilterPipe extends Pipe {
    double[] filterCoeffs;
    double[] filterCoeffsA; // [1,a1,a2,a3...]
    double[] filterCoeffsB; // [b0,b1,b2,b3...]
    int order;
    CyclicBuffer lastInputs;
    IirFilterCoefficients iirFilterCoefficients;

    public FilterPipe(FilterPassType type,FilterCharacteristicsType filterCharacteristicsType, double ripple, int filterOrder, float fcf1, float fcf2){

        order = filterOrder;
        lastInputs = new CyclicBuffer(order);
        iirFilterCoefficients = IirFilterDesignFisher.design(
            type,                                               //type
            filterCharacteristicsType,                          //characteristics
            filterOrder,                                        //order - do 50 najlepiej
            ripple,                                             //ripple
            fcf1,                                               //cutoff 1
            fcf2                                                //cutoff 2
            );


        filterCoeffs = new double[iirFilterCoefficients.b.length+ iirFilterCoefficients.a.length-1];
        filterCoeffsA = iirFilterCoefficients.a;
        filterCoeffsB = iirFilterCoefficients.b;
        for(int i = 0; i< iirFilterCoefficients.b.length+ iirFilterCoefficients.a.length-1; i++){
            filterCoeffs[i] = i< iirFilterCoefficients.a.length-1 ? iirFilterCoefficients.a[i+1] : iirFilterCoefficients.b[i-(iirFilterCoefficients.a.length-1)];
        }

        System.out.println("Buttercoefs a: "+ Arrays.toString(iirFilterCoefficients.a));
        System.out.println("Buttercoefs b: "+ Arrays.toString(iirFilterCoefficients.b));
        System.out.println("Filtercoefs : "+ Arrays.toString(filterCoeffs));
    }

    @Override
    protected void process(final double[][] data) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                double [][] filteredData = new double[data.length][2];
                int i = 0;
                for (double[] sample : data){
                    lastInputs.putData(sample);
                    filteredData[i] = step(sample);
                }
                save(filteredData);
                emit(filteredData);
            }
        });

    }

    public IirFilterCoefficients getIirCoeffs(){
        return iirFilterCoefficients;
    }

    public double[] getFilterCoeffs() {
        return filterCoeffs;
    }

    public double[] step(double[] samples){
        double filtered = 0;

        double[][] x = lastInputs.getSamples();
        int xlen = x.length;

        // x(n)b(0)+x(n-1)b(1)...
        for(int i = 0; i<xlen; i++){
            filtered += x[xlen - i - 1][1]*filterCoeffsB[i];
        }

        double[][] y = recentSamples.getSamples(order-1);
        int ylen = x.length;

        // y(n-1)a(1) + y(n-2)a(2)...
        for(int i = 0; i<ylen; i++){
            filtered += y[ylen - i - 1][1]*filterCoeffsA[i+1];
        }

        recentSamples.putData(new double[]{samples[0],filtered});
        return new double[]{samples[0],filtered};

    }
//    public double step(int[] samples){
//        double filtered = 0;
//        ArrayUtils.reverse(samples);
//        for (int i = 0; i < filterCoeffs.length ;i++){
//            filtered = filtered + (samples [i] * filterCoeffs[i]);
//        }
//        return filtered;
//    }
}
