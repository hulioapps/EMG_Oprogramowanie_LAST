package channel_logic;

import java.util.Arrays;

/**
 * Created by Boss on 2015-08-20.
 */
public class RecentBuffer {
    int rescueCapacity = 3;
    int capacity = 3;
    int size = 0;
    int current=-1;
    int [][] rescueData = new int[rescueCapacity][2];
    int [][] recentData = new int[capacity][2];                 //[wiersz,kolumna(x,y)] struktura: [x0,y0]
                                                                //                                 [x1,y1]
                                                                //                                 [x2,y2]

    public void putData(int[] data){
        recentData[size] = data;
        current = size;
        size++;
        if(size == capacity){
            rescueData = Arrays.copyOfRange(recentData,capacity-rescueCapacity,capacity);
            size = 0;
            current = -1;
        }
    }
    public void restart(){
        size = 0;
        current = -1;
        rescueData = new int[rescueCapacity][2];
        recentData = new int[capacity][2];
    }
    public int[][] getSamples(){
        if(current==-1){
            return null;
        }
        if(current==0){
            int [][] temp = new int[1][2];
            temp[0] = recentData[0];
            return temp;
        } else {
            return Arrays.copyOfRange(recentData, 0, current);
        }
    }

    public int[][] getSamples(int numberOfLastSamples){
        int number = numberOfLastSamples-1;

        if(size <numberOfLastSamples || numberOfLastSamples<=0){
            return null;
        }

        if(numberOfLastSamples==1){
            int [][]temp = new int [1][2];
            temp[0] = recentData[current];
            return temp;
        }

        else {
            return Arrays.copyOfRange(recentData,current-numberOfLastSamples,current);
        }
    }

    public int[][] getSamples(int from,int to){
        if(from > current
                || to > current
                || from < 0
                || to < 0
                || from<to)
        {
            return null;
        }

        if(from==to){
            int [][]temp = new int [1][2];
            temp[0] = recentData[from];
            return temp;
        }
        return Arrays.copyOfRange(recentData,from,to);
    }

    public int[][] getRescueSamples(){
        return Arrays.copyOfRange(rescueData,0,rescueCapacity);
    }

    public int[][] getRescueSamples(int numberOfLastSamples){
        if(rescueCapacity<numberOfLastSamples){
            return getSamples();
        }
        else {
            return Arrays.copyOfRange(rescueData,rescueCapacity-numberOfLastSamples,rescueCapacity);
        }
    }

    public int[][] getRescueSamples(int from,int to){
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
