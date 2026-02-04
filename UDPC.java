
import java.io.*;
import java.net.*;


public class UDPC {
    public static void main(String[] args) throws Exception{
        // pre-process 3
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        InetAddress ip = InetAddress.getByName("localhost");
        DatagramSocket ds = new DatagramSocket();
        System.out.print("Enter message: ");

        byte [] send = br.readLine().getBytes();

        ds.send(new DatagramPacket(send, send.length,ip, 9786 ));

        byte buf[] = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);
        ds.receive(dp);
        String msg = new String(dp.getData(), 0, dp.getLength());
        System.out.println("Result"+ msg);
    }
}
