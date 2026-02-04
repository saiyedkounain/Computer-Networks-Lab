import java.util.Scanner;

public class LeakyBucket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("ENter bucket capacity:"); int cap = sc.nextInt();
        System.out.println("ENter o.p rate :"); int rate = sc.nextInt();
        System.out.println("ENter number of packets :"); int n = sc.nextInt();

        int[] p = new int[n];
        int bucket = 0;
        System.out.println("ENter size of each packete");
        for(int i=0; i< n; i++){
            p[i] = sc.nextInt();
        }
        System.out.println("\n Size\tBucket\tSent\tRemain\tStatus");
        // main thing
        for(int size : p){

            if(bucket + size <= cap){
                bucket+=size;
                System.out.println(size + "\t" + bucket + "\t" + Math.min(bucket, rate) + "\t" + Math.max(0,bucket-rate) + "\tAccepted");
            }else{
                 System.out.println(size + "\t" + bucket + "\t" + Math.min(bucket, rate) + "\t" + Math.max(0,bucket-rate) + "\tDropped");
            }

            // dont forget
            bucket = Math.max(0, bucket-rate);
        }
        sc.close();
    }
}
