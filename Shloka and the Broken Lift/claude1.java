import java.util.*;
import java.io.*;

public class claude1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        while(t-- > 0){
            int n = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] h = new int[n+1];
            for(int i=1;i<=n;i++) h[i] = Integer.parseInt(st.nextToken());

            boolean[] ok = new boolean[n+1];
            ok[1] = true;

            for(int j=2;j<=n;j++){
                int val = h[j];
                for(int d=1; (long)d*d<=val; d++){
                    if(val % d != 0) continue;
                    int d1 = d, d2 = val/d;
                    if(d1>=2 && d1<=j-1 && ok[j-d1]) { ok[j]=true; break; }
                    if(d2>=2 && d2<=j-1 && ok[j-d2]) { ok[j]=true; break; }
                }
            }
            sb.append(ok[n] ? "YES" : "NO").append("\n");
        }
        System.out.print(sb);
    }
}