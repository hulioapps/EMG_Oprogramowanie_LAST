package channel_logic.views;

import channel_logic.processing.Emitable;
import channel_logic.processing.Listenable;

/**
 * Created by Boss on 2015-10-18.
 */
public abstract class BaseViewController implements Listenable {
    @Override
    public abstract void listen(double[][] data);

    @Override
    public void registerToParent(Emitable e) {
        e.registerChild(this);
    }

    @Override
    public void unregisterToParent(Emitable e) {
        e.unregisterChild(this);
    }
}
