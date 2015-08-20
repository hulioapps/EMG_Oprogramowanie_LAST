package events;

/**
 * Created by Boss on 2015-08-19.
 */
public class DataAvailableBusEvent extends BusEvent {
    int channelNumber;
    int[] data;
    public DataAvailableBusEvent(int number, int[] data){
        channelNumber = number;
        this.data = data;
    }

    public int getChannelNumber() {
        return channelNumber;
    }

    public int[] getData() {
        return data;
    }
}
