import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        StringBuilder sb = new StringBuilder();
        
        in.nextToken();
        int t = (int) in.nval;
        
        while(t-- > 0){
            in.nextToken();
            int n = (int) in.nval;
            in.nextToken();
            long d = (long) in.nval;
            in.nextToken();
            long f = (long) in.nval;
            
            long[][] depots = new long[n][2];
            for(int i = 0; i < n; i++){
                in.nextToken();
                depots[i][0] = (long) in.nval;
                in.nextToken();
                depots[i][1] = (long) in.nval;
            }
            
            Arrays.sort(depots, (a, b) -> Long.compare(a[0], b[0]));
            
            PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
            long curFuel = f;
            long curPos = 0;
            int stops = 0;
            boolean ok = true;
            
            for(int i = 0; i <= n; i++){
                long target = (i == n) ? d : depots[i][0];
                long dist = target - curPos;
                
                while(curFuel < dist){
                    if(pq.isEmpty()){
                        ok = false;
                        break;
                    }
                    curFuel += pq.poll();
                    stops++;
                }
                
                if(!ok) break;
                
                curFuel -= dist;
                curPos = target;
                
                if(i < n){
                    pq.add(depots[i][1]);
                }
            }
            
            sb.append(ok ? stops : -1).append("\n");
        }
        
        System.out.print(sb);
    }
}