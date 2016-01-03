package channel_logic.processing;

import channel_logic.ChannelData;
import com.google.common.eventbus.EventBus;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Boss on 2015-08-20.
 */
public class DataPuller implements Emitable{
    EventBus eventBus;
    ScheduledExecutorService scheduledExecutorService;
    int frameRate=20;
    int pullSize = 1000; //60s
    ArrayList<ChannelData> pullSources;
    public DataPuller(){
       eventBus = new EventBus();
        pullSources = new ArrayList<>();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }
    public void setPullSource(ChannelData channelData){
        pullSources.add(channelData);
    }

    public void removePullSource(ChannelData channelData){
        pullSources.remove(channelData);
    }

    public void startPulling(){
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                for (ChannelData channelData : pullSources) {
                    System.out.println(System.currentTimeMillis());
                    emit(channelData.getBufferedSamples(pullSize));
                }
            }
        }, 0, 1000 / frameRate, TimeUnit.MILLISECONDS);
    }

    @Override
    public void registerChild(Object o) {
        eventBus.register(o);
    }

    @Override
    public void unregisterChild(Object o) {
        eventBus.unregister(o);
    }

    private void emit(double[][] data){
        if(data==null){
            System.out.println("why am i throwing nothing...");
           data = new double[][]{{0,0}};
        }
        eventBus.post(data);
    }
}
