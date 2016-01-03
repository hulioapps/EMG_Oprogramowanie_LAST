package channel_logic;

import com.google.common.eventbus.Subscribe;
import events.DataAvailableBusEvent;
import inputs.Input;

import java.util.Arrays;

public class ChannelData {
    int lastPull = 0;                       // numer sampla z ostatniego pulla
    private int channelNumber;
    CyclicBuffer cyclicBuffer;              // Bufor najnowszych pr�bek
    FileSaver fileSaver;                    // Obiekt zapisuj�cy pr�bki do pliku
    DataFilesManager dataFilesManager;      // Obiekt zarz�dzaj�cy zapisanymi plikami

    // Publiczny konstruktor
    public ChannelData(int channelNumber){
      this.channelNumber = channelNumber;
    }

    // Metoda pozawalaj�ca ustawi� bufor
    public void setCyclicBuffer(CyclicBuffer cyclicBuffer) {
        this.cyclicBuffer = cyclicBuffer;
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
            double data[] = busEvent.getData();
            cyclicBuffer.putData(data);
        }
    }

    public int getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(int channelNumber) {
        this.channelNumber = channelNumber;
    }

    public double[][] getAllBufferedSamples(){
        return cyclicBuffer.getSamples();

    }

    public double[][] getBufferedSamples(int sizeOfPuller) {
        double[][] temp = cyclicBuffer.getSamples(sizeOfPuller);
        int dif = temp.length-sizeOfPuller;
        int [] filler = {-1,0};
        if(dif<0){
            double[][] temp2 = new double[-dif][2];
            Arrays.fill(temp2,filler);
            return concat(temp2,temp);
        }
        return temp;
    }

    public double[][] concat(double[][] a, double[][] b) {
        int aLen = a.length;
        int bLen = b.length;
        double[][] c= new double[aLen+bLen][2];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

}
