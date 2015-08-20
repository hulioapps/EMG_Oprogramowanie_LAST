package channel_logic.processing;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by Boss on 2015-08-20.
 */
public abstract class Pipe {
    EventBus eventBus;
    ArrayList<Integer> parrents;
    @Subscribe
    public abstract void listen();

    protected abstract void process();

    public abstract void emit();

    public abstract void registerToParrent();

    public abstract void unregisterToParrent();

    public abstract void registerChild();

    public abstract void unregisterChild();


}
