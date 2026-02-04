import java.util.Arrays;
import java.util.Scanner;
class BellmanFord{
    // 2 globals
    public static int n;
    public static int[][] graph;

    public static void printSoln(int[] dist){
        System.out.println("Vector \t Distance from src");
        for(int i=0; i<n; i++){
            System.out.println((i+1) + "\t\t" + dist[i]);
        }
    }

    public static void ford(int src){
        // preprocess 3
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        //
        for(int i=0; i<n;i++){
            for(int u=0; u < n; u++){
                for(int v=0; v <n ;v++){
                    // main thing
                    if(graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && (dist[u] + graph[u][v]) < dist[v]){
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }
        }

        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                // main thing
                if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && (dist[u] + graph[u][v]) < dist[v]) {
                    System.out.println("Negative values detected!");
                    return;
                }
            }
        }
        printSoln(dist);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter n"); 
        n = sc.nextInt();
        System.out.println("Enter the weight matrix of graph"); 
        graph = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0;j<n;j++){
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter source"); int src = sc.nextInt();
        ford(src - 1);
    }
}
