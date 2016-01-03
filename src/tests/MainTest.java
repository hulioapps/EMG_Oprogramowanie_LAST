package tests;

import biz.source_code.dsp.filter.FilterCharacteristicsType;
import biz.source_code.dsp.filter.FilterPassType;
import biz.source_code.dsp.math.PolynomialUtils;
import biz.source_code.dsp.swing.TransferFunctionPlot;
import channel_logic.ChannelData;
import channel_logic.DataFilesManager;
import channel_logic.FileSaver;
import channel_logic.CyclicBuffer;
import channel_logic.processing.*;
import channel_logic.processing.pipes.DoNothingPipe;
import channel_logic.processing.pipes.FilterPipe;
import graphics.MainFrame;
import inputs.DummyInput;
import inputs.Input;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Boss on 2015-08-20.
 */
public class MainTest {
    Input testSource;
    ChannelData channelData;
    CyclicBuffer cyclicBuffer;
    DataPuller dataPuller;
    DoNothingPipe pipe1,pipe2_1,pipe2_2;
    @Before
    public void setup(){
        // given
        testSource = new DummyInput();
        channelData = new ChannelData(1);
        channelData.registerToInput(testSource);

        cyclicBuffer = new CyclicBuffer();
        channelData.setCyclicBuffer(cyclicBuffer);
        channelData.setFileSaver(new FileSaver());
        channelData.setDataFilesManager(new DataFilesManager());

        dataPuller = new DataPuller();

        pipe1 = new DoNothingPipe(1);
        pipe2_1 = new DoNothingPipe(21);
        pipe2_2 = new DoNothingPipe(22);
    }


    @Test
    public void checkPutDataMethod(){
        CyclicBuffer tempBuffer = new CyclicBuffer();
        tempBuffer.putData(new double[]{1, 1});
    }

    @Test
    public void checkGetSamplesMethod(){

        CyclicBuffer tempBuffer = new CyclicBuffer();
        /* ---zero samples--- */
        //TODO testy dla rescue
        // method without arg
        double [][] samples = tempBuffer.getSamples();
        assertNull(samples);

        // method with number of last samples arg, good arg 1
        samples = tempBuffer.getSamples(1);
        assertNull(samples);

        // method with number of last samples arg, wrong arg 10
        samples = tempBuffer.getSamples(10);
        assertNull(samples);

        // method with number of last samples arg, wrong arg -1
        samples = tempBuffer.getSamples(-1);
        assertNull(samples);

        // method with range arg, good arg 0,0
        samples = tempBuffer.getSamples(0,0);
        assertNull(samples);

        // method with range arg, wrong arg 0,10
        samples = tempBuffer.getSamples(0,10);
        assertNull(samples);

        // method with range arg, wrong arg 10,10
        samples = tempBuffer.getSamples(10,10);
        assertNull(samples);

        // method with range arg, wrong arg 10,0
        samples = tempBuffer.getSamples(10,0);
        assertNull(samples);

        // method with range arg, wrong arg -1,0
        samples = tempBuffer.getSamples(-1,0);
        assertNull(samples);

        /* ---one sample--- */
        //TODO testy dla rescue
        tempBuffer.restart();
        double data[] = {1,1};
        tempBuffer.putData(data);

        // method without arg
        samples = tempBuffer.getSamples();
        assertArrayEquals(samples[0], data,0.1d);

        // method with number of last samples arg, good arg 1
        samples = tempBuffer.getSamples(1);
        assertArrayEquals(samples[0], data,0.1d);

        // method with number of last samples arg, wrong arg 10
        samples = tempBuffer.getSamples(10);
        assertArrayEquals(samples[0], data,0.1d);

        // method with number of last samples arg, wrong arg -1
        samples = tempBuffer.getSamples(-1);
        assertNull(samples);

        // method with range arg, good arg 0,0
        samples = tempBuffer.getSamples(0,0);
        assertArrayEquals(samples[0], data,0.1d);

        // method with range arg, wrong arg 0,10
        samples = tempBuffer.getSamples(0,10);
        assertNull(samples);

        // method with range arg, wrong arg 10,10
        samples = tempBuffer.getSamples(10,10);
        assertNull(samples);

        // method with range arg, wrong arg 10,0
        samples = tempBuffer.getSamples(10,0);
        assertNull(samples);

        // method with range arg, wrong arg -1,0
        samples = tempBuffer.getSamples(-1,0);
        assertNull(samples);

        /* ---many samples---*/
        tempBuffer.restart();
        data = new double[]{1,1};
        tempBuffer.putData(data);
        double []data2 = {2,2};
        tempBuffer.putData(data2);

        // method without arg
        samples = tempBuffer.getSamples();
        assertArrayEquals(samples[0], data,0.1d);

        // method with number of last samples arg, good arg 1
        samples = tempBuffer.getSamples(1);
        assertArrayEquals(samples[0], data2,0.1d);

        // method with number of last samples arg, good arg 2
        samples = tempBuffer.getSamples(2);
        assertArrayEquals(data,samples[0],0.1d);
        assertArrayEquals(data2,samples[1],0.1d);

        // method with number of last samples arg, wrong arg cap+10
        samples = tempBuffer.getSamples(cyclicBuffer.getCapacity()+10);
        assertArrayEquals(data,samples[0],0.1d);
        assertArrayEquals(data2, samples[1],0.1d);


        // method with number of last samples arg, wrong arg -1
        samples = tempBuffer.getSamples(-1);
        assertNull(samples);

        // method with range arg, good arg 0,0
        samples = tempBuffer.getSamples(0,0);
        assertArrayEquals(samples[0], data,0.1d);

        // method with range arg, wrong arg 0,10
        samples = tempBuffer.getSamples(0,10);
        assertNull(samples);

        // method with range arg, wrong arg 10,10
        samples = tempBuffer.getSamples(10,10);
        assertNull(samples);

        // method with range arg, wrong arg 10,0
        samples = tempBuffer.getSamples(10,0);
        assertNull(samples);

        // method with range arg, wrong arg -1,0
        samples = tempBuffer.getSamples(-1,0);
        assertNull(samples);

        /* ---cap number of samples--- */
        //TODO testy dla rescue
        tempBuffer.restart();

        for (int i = 0;i<tempBuffer.getCapacity();i++){
            data = new double[]{1,1};
            tempBuffer.putData(data);
        }

        // method without arg
        samples = tempBuffer.getSamples();
        assertNull(samples);

        // method with number of last samples arg, good arg 1
        samples = tempBuffer.getSamples(1);
        assertNull(samples);

        // method with number of last samples arg, wrong arg 10
        samples = tempBuffer.getSamples(10);
        assertNull(samples);

        // method with number of last samples arg, wrong arg -1
        samples = tempBuffer.getSamples(-1);
        assertNull(samples);

        // method with range arg, good arg 0,0
        samples = tempBuffer.getSamples(0,0);
        assertNull(samples);

        // method with range arg, wrong arg 0,10
        samples = tempBuffer.getSamples(0,10);
        assertNull(samples);

        // method with range arg, wrong arg 10,10
        samples = tempBuffer.getSamples(10,10);
        assertNull(samples);

        // method with range arg, wrong arg 10,0
        samples = tempBuffer.getSamples(10,0);
        assertNull(samples);

        // method with range arg, wrong arg -1,0
        samples = tempBuffer.getSamples(-1,0);
        assertNull(samples);


    }


    @Test
    public void checkRecentBufferRescue(){
        CyclicBuffer tempBuffer = new CyclicBuffer();
        int cap = tempBuffer.getCapacity();
        int overCap = cap+1;
        for (int i =0;i<overCap;i++){
            double data[] = {i,i};
            tempBuffer.putData(data);
        }
        int resCap = tempBuffer.getRescueCapacity();
        for(int j=0;j<resCap;j++){
            assertArrayEquals(new double[]{j,j}, tempBuffer.getRescueSamples()[j],0.1d);
        }
    }

    @Test
    public void checkPropagation() throws InterruptedException {
        dataPuller.setPullSource(channelData);

        pipe1.registerToParent(dataPuller);
        pipe2_1.registerToParent(pipe1);
        pipe2_2.registerToParent(pipe1);

        // when
        double data[] = new double[]{1,1};
        testSource.post(1, data);
        assertNotNull(channelData.getAllBufferedSamples());
        assertArrayEquals(data, channelData.getAllBufferedSamples()[0],0.1d);

        dataPuller.startPulling();


        // then
        data = new double[]{2,2};
        testSource.post(1, data);
        double [][] bufsamps = channelData.getAllBufferedSamples();
        double []temp = bufsamps[1];
        assertArrayEquals(data,temp,0.1d);


    }
    @Test
    public void testWindowsGenerator(){

        //test RECT window
        double [] testArray = {1.0d,1.0d,1.0d,1.0d,1.0d};
        double [] generatedTestArray = WindowsGenerator.generateWindow(WindowsGenerator.WindowType.RECTANGULAR,5);
        assertArrayEquals(testArray,generatedTestArray,0.001d);

        //test TRIANGULAR window
        generatedTestArray = WindowsGenerator.generateWindow(WindowsGenerator.WindowType.TRIANGULAR,6);
        System.out.println("Triangular parzysta: "+Arrays.toString(generatedTestArray));

        generatedTestArray = WindowsGenerator.generateWindow(WindowsGenerator.WindowType.TRIANGULAR,7);
        System.out.println("Triangular nieparzysta: "+Arrays.toString(generatedTestArray));

        //test HANNING window
        generatedTestArray = WindowsGenerator.generateWindow(WindowsGenerator.WindowType.HANNING,6);
        System.out.println("HANNING parzysta: "+Arrays.toString(generatedTestArray));

        generatedTestArray = WindowsGenerator.generateWindow(WindowsGenerator.WindowType.HANNING,7);
        System.out.println("HANNING nieparzysta: "+Arrays.toString(generatedTestArray));

        //test HANNING window
        generatedTestArray = WindowsGenerator.generateWindow(WindowsGenerator.WindowType.HAMMING,6);
        System.out.println("HAMMING parzysta: "+Arrays.toString(generatedTestArray));

        generatedTestArray = WindowsGenerator.generateWindow(WindowsGenerator.WindowType.HAMMING,7);
        System.out.println("HAMMING nieparzysta: "+Arrays.toString(generatedTestArray));

        //test BLACKMAN window
        generatedTestArray = WindowsGenerator.generateWindow(WindowsGenerator.WindowType.BLACKMAN,6);
        System.out.println("BLACKMAN parzysta: "+Arrays.toString(generatedTestArray));

        generatedTestArray = WindowsGenerator.generateWindow(WindowsGenerator.WindowType.BLACKMAN,7);
        System.out.println("BLACKMAN nieparzysta: "+Arrays.toString(generatedTestArray));
    }

    @Test
    public void filterCreationTest(){
        FilterPipe filterPipe = new FilterPipe(
                FilterPassType.bandpass,
                FilterCharacteristicsType.butterworth,
                0,
                2,
                0.02f,
                0.4f);
        JFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        PolynomialUtils.RationalFraction rf = new PolynomialUtils.RationalFraction();
        rf.top = filterPipe.getIirCoeffs().b;
        rf.bottom = filterPipe.getIirCoeffs().a;
        frame.getContentPane().add(new TransferFunctionPlot(rf,true));
        frame.setVisible(true);
        frame.pack();
        frame.revalidate();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
