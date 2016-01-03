package events;

import events.BusEvent;

/**
 * Created by Boss on 2015-08-20.
 */
public class PullBusEvent extends BusEvent {
    int [][]data;
    public int[][] getData() {
        return data;
    }
}
