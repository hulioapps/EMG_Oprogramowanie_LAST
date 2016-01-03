package channel_logic.processing;

/**
 * Created by Boss on 2015-08-21.
 *
 * Interfejs pozwalaj�cy na emitowanie danych na wyj�cia. Dane emitowane s� do wszystkich obiekt�w zarejestrowanych jako s�uchacze EventBusa
 *
 */
public interface Emitable extends Busable {

    void registerChild(Object o);

    void unregisterChild(Object o);

}
