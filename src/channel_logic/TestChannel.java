package channel_logic;

import com.google.common.eventbus.Subscribe;
import events.DataAvailableBusEvent;

import java.util.Arrays;

/**
 * Created by Boss on 2015-08-19.
 */
public class TestChannel {
    boolean triggered;
    int channelNumber;

    public TestChannel(int cn) {
        channelNumber = cn;
        triggered = false;
    }
    @Subscribe
    public void listen(DataAvailableBusEvent event) {
        if(event.getChannelNumber()==channelNumber){
            System.out.println("Channel "+channelNumber+" triggered");
            System.out.println("Sample " + Arrays.toString(event.getData()));
        }
    }

    public String isTriggered() {
        return "Channel "+channelNumber+" triggered:"+triggered;
    }
}
