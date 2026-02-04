package TCP;
import java.net.*;
import java.io.*;
public class TCPC {
    public static void main(String[] args) throws Exception{
        Socket s = new Socket("127.0.0.1", 4000);

        BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        System.out.println("Enter file name");
        out.println(key.readLine());

        String line;

        while((line = in.readLine())!=null){
            System.out.println(line);
        }

        s.close();
    }
    
}
