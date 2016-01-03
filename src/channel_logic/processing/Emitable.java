package channel_logic.processing;

/**
 * Created by Boss on 2015-08-21.
 *
 * Interfejs pozwalaj¹cy na emitowanie danych na wyjœcia. Dane emitowane s¹ do wszystkich obiektów zarejestrowanych jako s³uchacze EventBusa
 *
 */
public interface Emitable extends Busable {

    void registerChild(Object o);

    void unregisterChild(Object o);

}
