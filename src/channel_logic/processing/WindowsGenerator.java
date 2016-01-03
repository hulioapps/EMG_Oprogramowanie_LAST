package channel_logic.processing;

/**
 * Created by Boss on 2015-12-06.
 */
public class WindowsGenerator {

    public enum WindowType{
        RECTANGULAR,
        TRIANGULAR, //Bartlett
        HANNING,
        HAMMING,
        BLACKMAN
    }

    public static double[] generateWindow(WindowType windowType, int size){
        int m = size / 2;
        double pi = Math.PI;
        double [] currentWindow = new double[size];
        switch(windowType){
            case RECTANGULAR:
                for(int i =0;i<size;i++){
                    currentWindow[i] = 1.0d;
                }
                break;
            case TRIANGULAR:
                for(int i =0;i<size;i++){
                    currentWindow[i] = 1.0d - Math.abs((i-((double)size-1)/2)/((double)(size-1)/2));
                }
                break;
            case HANNING:
                for(int i =0;i<size;i++){
                    currentWindow[i] = 0.5d*(1.0d-Math.cos((2*pi*i)/((double)size-1)));
                }
                break;
            case HAMMING:
                double a = 25.0d/46.0d;
                double b = 1.0d-a;
                for(int i =0;i<size;i++){
                    currentWindow[i] = a - b*(Math.cos((2*pi*i)/((double)size-1)));
                }
                break;
            case BLACKMAN:
                /* PRZYBLIZONE
                double alpha = 0.16d;
                double a0 = (1.0d-alpha)/2.0d;
                double a1 = 0.5d;
                double a2 = alpha/2.0d;
                */
                double a0 = 7938.0d/18608.0d;
                double a1 = 9240.0d/18608.0d;
                double a2 = 1430.0d/18608.0d;
                for(int i =0;i<size;i++){
                    currentWindow[i] = a0 - a1*(Math.cos((2*pi*i)/((double)size-1))) + a2*(Math.cos((4*pi*i)/((double)size-1)));
                }
                break;

        }

        return currentWindow;
    }
}
