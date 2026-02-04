import java.util.*;
class RED{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        System.out.println("Max Packets"); int max = sc.nextInt();
        System.out.println("Max Prob"); double maxp = sc.nextDouble();
        System.out.println("MIn Prob"); double minp = sc.nextDouble();
        
        System.out.println("Queue Size"); int qSize = sc.nextInt();
        System.out.println("Threshold "); int th = sc.nextInt();

        int qlen = 0;

        for(int i=0; i <max; i++){
            double p = minp + (maxp - minp) * (qlen - th) / (qSize - th);

            if(qlen >= th && r.nextDouble() < p){
                System.out.println("Packet Dropped (Congestion Avoidance)");
            }else{
                System.out.println("Packet Accepted: " + (i+1));
                // dont forget
                qlen++;
            }
        }
        sc.close();
    }
}
