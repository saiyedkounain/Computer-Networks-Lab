import java.util.Scanner;

public class TokenBucket {
    public static void main(String[] args) {
        // inputs
        Scanner sc = new Scanner(System.in);
        System.out.println("Capacity: ");
        int cap = sc.nextInt();
        System.out.println("Rate: ");
        int rate = sc.nextInt();
        System.out.println("N: ");
        int n = sc.nextInt();
        int[] p = new int[n];
        int tokens = 0;

        System.out.println("Enter size of each packet:");
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt();
        }

        // main
        System.out.println("\nSize\tTokens\tSent\tRemaining\tStatus");
        for (int size : p) {
            //
            tokens = Math.min(tokens + rate, cap);

            if (size <= tokens) {
                tokens -= size;
                System.out.println(size + "\t" + (tokens + size) + "\t" + size + "\t" + tokens + "\tAccepted");
            } else {

                System.out.println(size + "\t" + tokens + "\t0" + "\t" + tokens + "\tDropped");
            }
        }
        sc.close();
    }
}
