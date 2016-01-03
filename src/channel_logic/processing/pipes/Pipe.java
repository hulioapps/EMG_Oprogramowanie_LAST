package channel_logic.processing.pipes;

import channel_logic.CyclicBuffer;
import channel_logic.processing.Emitable;
import channel_logic.processing.Listenable;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import events.BusEvent;

import java.util.ArrayList;

/**
 * Created by Boss on 2015-08-20.
 */
public abstract class Pipe implements Listenable,Emitable {
    ArrayList<Integer> parrents;
    EventBus eventBus = new EventBus();
    CyclicBuffer recentSamples = new CyclicBuffer();
    protected abstract void process(double[][] data);

    protected final void emit(double[][] data){
        eventBus.post(data);
    };

    protected final void save(double[][] data) {
        for (double [] sample: data) {
            recentSamples.putData(sample);
        }
    };

    @Override
    public void registerChild(Object o) {
        eventBus.register(o);
    }

    @Override
    public void unregisterChild(Object o) {
        eventBus.unregister(o);
    }

    @Override
    public void listen(double[][] data) {
        process(data);
    }

    @Override
    public void registerToParent(Emitable e) {
        e.registerChild(this);
    }

    @Override
    public void unregisterToParent(Emitable e) {
        e.unregisterChild(this);
    }
}
