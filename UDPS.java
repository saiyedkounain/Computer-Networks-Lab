import java.util.*;
import java.net.*;
import java.io.*;
public class UDPS {
    public static void main(String[] args) throws Exception{
        DatagramSocket ds = new DatagramSocket(9786); System.out.println("SR");
        byte[] buf = new byte[1024];
        while (true) {
            // dp
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            ds.receive(dp);

            String msg = new String(dp.getData(),0, dp.getLength());
            msg = msg.toUpperCase();

            byte[] send = msg.getBytes();
            DatagramPacket reply = new DatagramPacket(send, send.length, dp.getAddress(), dp.getPort());
            ds.send(reply);
        }
    }
}
