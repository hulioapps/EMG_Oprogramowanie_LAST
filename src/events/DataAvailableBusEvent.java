package events;

/**
 * Created by Boss on 2015-08-19.
 */
public class DataAvailableBusEvent extends BusEvent {
    int channelNumber;
    double[] data;
    public DataAvailableBusEvent(int number, double[] data){
        channelNumber = number;
        this.data = data;
    }

    public int getChannelNumber() {
        return channelNumber;
    }

    public double[] getData() {
        return data;
    }
}
