package channel_logic.processing;

import com.google.common.eventbus.Subscribe;
import events.BusEvent;

/**
 * Created by Boss on 2015-08-21.
 *
 * Interfejs pozwalaj�cy na przyjmowanie danych na wej�cie
 *
 */
public interface Listenable extends Busable {
    @Subscribe
    void listen(double [][] data);

    void registerToParent(Emitable e);

    void unregisterToParent(Emitable e);
}
