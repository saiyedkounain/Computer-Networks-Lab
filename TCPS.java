package TCP;

import java.net.*;
import java.io.*;

public class TCPS {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(4000);
        System.out.println("SR");
        Socket s = ss.accept();
        System.out.println("CC");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        String file = in.readLine();
        BufferedReader fr = new BufferedReader(new FileReader(file));

        String line;

        while ((line = fr.readLine()) != null) {
            out.println(line);
        }

        fr.close();
        ss.close();
        s.close();

    }
}
