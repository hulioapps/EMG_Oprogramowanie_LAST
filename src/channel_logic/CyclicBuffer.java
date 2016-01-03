package channel_logic;

import java.util.Arrays;

/**
 * Created by Boss on 2015-08-20.
 *
 * Bufor cykliczny ostatnich pr�bek
 *
 */
public class CyclicBuffer {



    int rescueCapacity =  120000;
    int capacity = 120000;
    int size = 0;
    int current=1;
    double [][] rescueData;
    double [][] recentData;                                     //[wiersz,kolumna(x,y)] struktura: [x0,y0]
                                                                //                                 [x1,y1]
                                                                //                                 [x2,y2]

    int numberOfSamplesSinceLastPull = 0;

    public CyclicBuffer(){
        rescueData = new double[rescueCapacity][2];
        recentData = new double [capacity][2];
    }

    public CyclicBuffer(int size){
        rescueCapacity = size;
        capacity = size;
        rescueData = new double[rescueCapacity][2];
        recentData = new double [capacity][2];
    }


    public void putData(double[] data){
        recentData[size] = data;
        current = size;
        size++;
        if(size == capacity){
            rescueData = Arrays.copyOfRange(recentData,capacity-rescueCapacity,capacity);
            size = 0;
            current = -1;
        }
        //System.out.println("current "+ current + " size " + size);
    }
    public void restart(){
        size = 0;
        current = -1;
        rescueData = new double[rescueCapacity][2];
        recentData = new double[capacity][2];
    }
    public double[][] getSamples(){
        if(current==-1){
          //  System.out.println("null");
            return null;
        }
        if(current==0){
            double [][] temp = new double[1][2];
            temp[0] = recentData[0];
            return temp;
        } else {
           // System.out.println("precopy");
            return Arrays.copyOfRange(recentData, 0, current+1);
        }
    }

    public double[][] getSamples(int numberOfLastSamples){
        int number = numberOfLastSamples-1;

        if(size<numberOfLastSamples){
            return getSamples();
        }

        if(numberOfLastSamples<=0){
            return null;
        }

        if(numberOfLastSamples==1){
            double [][]temp = new double [1][2];
            temp[0] = recentData[current];
            return temp;
        }

        else {
            return Arrays.copyOfRange(recentData,size-numberOfLastSamples,size);
        }
    }

    public double[][] getSamples(int from,int to){
        if(from > current
                || to > current
                || from < 0
                || to < 0
                || from<to)
        {
            return null;
        }

        if(from==to){
            double [][]temp = new double [1][2];
            temp[0] = recentData[from];
            return temp;
        }
        return Arrays.copyOfRange(recentData,from,to+1);
    }

    public double[][] getRescueSamples(){
        return Arrays.copyOfRange(rescueData,0,rescueCapacity);
    }

    public double[][] getRescueSamples(int numberOfLastSamples){
        if(rescueCapacity<numberOfLastSamples){
            return getSamples();
        }
        else {
            return Arrays.copyOfRange(rescueData,rescueCapacity-numberOfLastSamples,rescueCapacity);
        }
    }

    public double[][] getRescueSamples(int from,int to){
        if(from>rescueCapacity || to>rescueCapacity){
            //TODO
        }
        return Arrays.copyOfRange(rescueData,from,to);
    }


    public int getRescueCapacity() {
        return rescueCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }
}
