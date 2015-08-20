package tests;

import channel_logic.ChannelData;
import inputs.DummyInput;
import inputs.Input;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Boss on 2015-08-20.
 */
public class ChannelDataInputSubscribeTest {
    @Test
    public void TestIfSubscribeCorrect(){
        DummyInput input = new DummyInput();
        ChannelData channelData = new ChannelData(1);
        channelData.registerToInput(input);
    }
}
