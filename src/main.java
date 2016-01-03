import channel_logic.ChannelData;
import channel_logic.DataFilesManager;
import channel_logic.FileSaver;
import channel_logic.CyclicBuffer;
import channel_logic.processing.*;
import channel_logic.processing.pipes.AbsPipe;
import channel_logic.processing.pipes.DoNothingPipe;
import channel_logic.processing.pipes.Pipe;
import channel_logic.views.BasePlotViewController;
import graphics.MainFrame;
import inputs.DummyInput;
import inputs.IPInput;
import inputs.Input;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Boss on 2015-08-19.
 */
public class main {
    public static void main(String [] args){
       /*
        final Input testSource,ipSource;
        ChannelData channelData;
        CyclicBuffer cyclicBuffer;
        DataPuller dataPuller;
        Pipe pipe1,pipe2_1,pipe2_2;
        BasePlotViewController dummyView;
        MainFrame mainFrame;

        mainFrame = new MainFrame();
        dummyView = new BasePlotViewController();
        mainFrame.addPanel(dummyView);

        testSource = new DummyInput();
//        testSource = new IPInputClient();
        ipSource = new IPInput();
        channelData = new ChannelData(1);
        channelData.registerToInput(testSource);

        cyclicBuffer = new CyclicBuffer();
        channelData.setCyclicBuffer(cyclicBuffer);
        channelData.setFileSaver(new FileSaver());
        channelData.setDataFilesManager(new DataFilesManager());

        dataPuller = new DataPuller();

        pipe1 = new DoNothingPipe(1);
        pipe2_1 = new AbsPipe(21);
        //pipe2_2 = new FilterPipe(FilterPipe.Type.BUTTERWORTH);





        dataPuller.setPullSource(channelData);

        pipe1.registerToParent(dataPuller);
        pipe2_1.registerToParent(pipe1);
        dummyView.registerToParent(pipe2_1);
        //pipe2_1.registerToParent(pipe1);
        //pipe2_2.registerToParent(pipe1);

        //dummyView.registerToParent(pipe2_1);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Random rand = new Random();
                double data[] = new double[]{1,rand.nextInt(200)-100};
                testSource.post(1, data);
            }
        }, 0, 20, TimeUnit.MILLISECONDS);

        dataPuller.startPulling();
        */

        CyclicBuffer cyc = new CyclicBuffer(5);
        cyc.putData(new double[]{11,11});
        System.out.println(Arrays.toString(cyc.getSamples()));
        cyc.putData(new double[]{12,12});
        System.out.println(Arrays.toString(cyc.getSamples()));
        cyc.putData(new double[]{13,13});
        System.out.println(Arrays.toString(cyc.getSamples()));
        cyc.putData(new double[]{14,14});
        System.out.println(Arrays.toString(cyc.getSamples()));
        cyc.putData(new double[]{15,15});
        System.out.println(Arrays.toString(cyc.getSamples()));
        cyc.putData(new double[]{16,16});
        System.out.println(Arrays.toString(cyc.getSamples()));
        cyc.putData(new double[]{17,17});
        System.out.println(Arrays.toString(cyc.getSamples()));
    }
}
