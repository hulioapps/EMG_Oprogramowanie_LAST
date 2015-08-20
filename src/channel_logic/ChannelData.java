package channel_logic;

import com.google.common.eventbus.Subscribe;
import events.DataAvailableBusEvent;
import inputs.Input;

public class ChannelData {
    private int channelNumber;
    RecentBuffer recentBuffer;              // Bufor najnowszych próbek
    FileSaver fileSaver;                    // Obiekt zapisuj¹cy próbki do pliku
    DataFilesManager dataFilesManager;      // Obiekt zarz¹dzaj¹cy zapisanymi plikami

    // Publiczny konstruktor
    public ChannelData(int channelNumber){
      this.channelNumber = channelNumber;
    }

    // Metoda pozawalaj¹ca ustawiæ bufor
    public void setRecentBuffer(RecentBuffer recentBuffer) {
        this.recentBuffer = recentBuffer;
    }

    // Metoda pozwalaj¹ca ustawiæ obiekt zapisuj¹cy próbki do pliku
    public void setFileSaver(FileSaver fileSaver) {
        this.fileSaver = fileSaver;
    }

    // Metoda pozwalaj¹ca ustawiæ obiekt zarz¹dzaj¹cy zapisanymi plikami
    public void setDataFilesManager(DataFilesManager dataFilesManager) {
        this.dataFilesManager = dataFilesManager;
    }

    // Metoda pozwalaj¹ca zarejestrowaæ kana³ do wejœcia
    public void registerToInput(Input input){
        input.register(this);
    }

    @Subscribe
    public void listen(DataAvailableBusEvent busEvent){
        if(busEvent.getChannelNumber()==channelNumber){
            int data[] = busEvent.getData();
            recentBuffer.putData(data);
        }
    }

    public int getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(int channelNumber) {
        this.channelNumber = channelNumber;
    }

    public int[][] getBufferedSamples(){
        return recentBuffer.getSamples();
    }
}
