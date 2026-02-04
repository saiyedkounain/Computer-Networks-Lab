import java.util.Scanner;

public class RSA{
    public static int gcd(int a, int b){
        return (b==0) ? a : gcd(b, a%b);
    }
    public static int power(int a, int b , int n){
        int r = 1;
        for(int i = 0; i< b; i++){
            r = (r * a) % n;
        }
        return r;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("P: "); int p = sc.nextInt();
        System.out.println("Q: "); int q = sc.nextInt();

        int n = p * q;
        int phi = (p-1) * (q-1);

        // select e
        int e = 2;
        while(gcd(e,phi) != 1){
            e++;
        }
        // get d
        int d = 1;
        while((d*e) % phi != 1){
            d++;
        }

        System.out.println("Enter message:"); int m = sc.nextInt();

        int c = power(m,e,n);
        int dec = power(c,d,n);

        System.out.println("Cipher text: " + c);
        System.out.println("Message after decryption: " + dec);
    }
}
