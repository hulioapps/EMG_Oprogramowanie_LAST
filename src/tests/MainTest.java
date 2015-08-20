package tests;

import channel_logic.ChannelData;
import channel_logic.DataFilesManager;
import channel_logic.FileSaver;
import channel_logic.RecentBuffer;
import inputs.Input;
import inputs.DummyInput;
import org.junit.*;
import static org.junit.Assert.*;
import static com.jayway.awaitility.Awaitility.*;
import com.jayway.awaitility.Duration.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit.*;
/**
 * Created by Boss on 2015-08-20.
 */
public class MainTest {
    Input testSource;
    ChannelData channelData;
    RecentBuffer recentBuffer;
    @Before
    public void setup(){
        // given
        testSource = new DummyInput();
        channelData = new ChannelData(1);
        channelData.registerToInput(testSource);
        recentBuffer = new RecentBuffer();
        channelData.setRecentBuffer(recentBuffer);
        channelData.setFileSaver(new FileSaver());
        channelData.setDataFilesManager(new DataFilesManager());
    }


    @Test
    public void checkPutDataMethod(){
        RecentBuffer tempBuffer = new RecentBuffer();
        tempBuffer.putData(new int[]{1, 1});
    }

    @Test
    public void checkGetSamplesMethod(){

        RecentBuffer tempBuffer = new RecentBuffer();
        /* ---zero samples--- */
        //TODO testy dla rescue
        // method without arg
        int [][] samples = tempBuffer.getSamples();
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
        int data[] = {1,1};
        tempBuffer.putData(data);

        // method without arg
        samples = tempBuffer.getSamples();
        assertArrayEquals(samples[0], data);

        // method with number of last samples arg, good arg 1
        samples = tempBuffer.getSamples(1);
        assertArrayEquals(samples[0], data);

        // method with number of last samples arg, wrong arg 10
        samples = tempBuffer.getSamples(10);
        assertNull(samples);

        // method with number of last samples arg, wrong arg -1
        samples = tempBuffer.getSamples(-1);
        assertNull(samples);

        // method with range arg, good arg 0,0
        samples = tempBuffer.getSamples(0,0);
        assertArrayEquals(samples[0], data);

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
            data = new int[]{1,1};
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
        RecentBuffer tempBuffer = new RecentBuffer();
        int cap = tempBuffer.getCapacity();
        int overCap = cap+1;
        for (int i =0;i<overCap;i++){
            int data[] = {i,i};
            tempBuffer.putData(data);
            assertArrayEquals(data,tempBuffer.getSamples(1)[0]);
        }
        int resCap = tempBuffer.getRescueCapacity();
        for(int j=0;j<resCap;j++){
            assertArrayEquals(new int[]{j,j}, tempBuffer.getRescueSamples()[j]);
        }
    }

    @Test
    public void checkPropagation() throws InterruptedException {

        // when
        int data[] = new int[]{1,1};
        testSource.post(1, data);
        assertNotNull(channelData.getBufferedSamples());
        assertArrayEquals(data, channelData.getBufferedSamples()[0]);

        // then
        data = new int[]{2,2};
        testSource.post(1, data);
        assertArrayEquals(data, channelData.getBufferedSamples()[1]);
    }


}
