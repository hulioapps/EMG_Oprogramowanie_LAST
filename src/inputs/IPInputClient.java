package inputs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Boss on 2015-11-02.
 */
public class IPInputClient extends Input {
    String hostName = "localhost";
    int portNumber = 5000;

    @Override
    public void post(int channelNumber, double[] data) {
        Socket kkSocket = null;
        try {
            kkSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(kkSocket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
