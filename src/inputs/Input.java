package inputs;

import com.google.common.eventbus.EventBus;

/**
 * Created by Boss on 2015-08-20.
 */
public abstract class Input {
    EventBus eventBus;

    public Input(){
        eventBus = new EventBus();
    }

    public void register(Object object){
        eventBus.register(object);
    }

    public void unregister(Object object){
        eventBus.unregister(object);
    }

    public abstract void post(int channelNumber,int [] data);
}
