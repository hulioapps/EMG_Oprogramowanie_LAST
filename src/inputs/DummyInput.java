package inputs;

import events.DataAvailableBusEvent;

/**
 * Created by Boss on 2015-08-19.
 */
public class DummyInput extends Input{

    @Override
    public void post(int chanelNumber,int[] data) {
        eventBus.post(new DataAvailableBusEvent(chanelNumber,data));
    }

}
