import java.util.*;

public class CRC {

    static String div(String data, String key) {
        char[] a = data.toCharArray();
        char[] b = key.toCharArray();
    
        int m = a.length;
        int n = b.length;
    
        for (int i = 0; i <= m - n; i++) {
            if (a[i] == '1') {
                for (int j = 0; j < n; j++) {
                    a[i + j] = (a[i + j] == b[j]) ? '0' : '1';
                }
            }
        }
    
        return new String(a).substring(m - n + 1);
    }


    static String encode(String data, String key) {
        return data + div(data + "0".repeat(key.length() - 1), key);
    }

    static boolean error(String code, String key) {
        return div(code, key).contains("1");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Key: ");
        String key = sc.next();

        System.out.print("Data: ");
        String data = sc.next();
        String code = encode(data, key);

        System.out.println("Encoded: " + code);

        System.out.print("Enter received code: ");
        System.out.println(error(sc.next(), key) ?
                "Error detected" : "No error");
    }
}
