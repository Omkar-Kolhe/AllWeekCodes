import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        
        StringBuilder sb = new StringBuilder();
        
        while(t-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());
            long m = Long.parseLong(st.nextToken());
            
            long maxSize = (m + 1) / 2;
            
            if(n > maxSize){
                sb.append(-1).append("\n");
            } else {
                long start = m - n + 1;
                for(long i = start; i <= m; i++){
                    sb.append(i);
                    if(i != m) sb.append(" ");
                }
                sb.append("\n");
            }
        }
        
        System.out.print(sb);
    }
}