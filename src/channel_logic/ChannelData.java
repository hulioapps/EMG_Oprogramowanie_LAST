package channel_logic;

import com.google.common.eventbus.Subscribe;
import events.DataAvailableBusEvent;
import inputs.Input;

public class ChannelData {
    private int channelNumber;
    RecentBuffer recentBuffer;              // Bufor najnowszych pr�bek
    FileSaver fileSaver;                    // Obiekt zapisuj�cy pr�bki do pliku
    DataFilesManager dataFilesManager;      // Obiekt zarz�dzaj�cy zapisanymi plikami

    // Publiczny konstruktor
    public ChannelData(int channelNumber){
      this.channelNumber = channelNumber;
    }

    // Metoda pozawalaj�ca ustawi� bufor
    public void setRecentBuffer(RecentBuffer recentBuffer) {
        this.recentBuffer = recentBuffer;
    }

    // Metoda pozwalaj�ca ustawi� obiekt zapisuj�cy pr�bki do pliku
    public void setFileSaver(FileSaver fileSaver) {
        this.fileSaver = fileSaver;
    }

    // Metoda pozwalaj�ca ustawi� obiekt zarz�dzaj�cy zapisanymi plikami
    public void setDataFilesManager(DataFilesManager dataFilesManager) {
        this.dataFilesManager = dataFilesManager;
    }

    // Metoda pozwalaj�ca zarejestrowa� kana� do wej�cia
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
