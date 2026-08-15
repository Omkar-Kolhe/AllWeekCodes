import java.util.*;
import java.io.*;

public class Main {
    static boolean canShip(int[] W, int cap, int D){
        int days = 1, cur = 0;
        for(int w : W){
            if(cur + w > cap){
                days++;
                cur = 0;
            }
            cur += w;
        }
        return days <= D;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int[] W = new int[N];
            st = new StringTokenizer(br.readLine());
            int lo = 0, hi = 0;
            for(int i = 0; i < N; i++){
                W[i] = Integer.parseInt(st.nextToken());
                lo = Math.max(lo, W[i]);
                hi += W[i];
            }
            while(lo < hi){
                int mid = lo + (hi - lo) / 2;
                if(canShip(W, mid, D)) hi = mid;
                else lo = mid + 1;
            }
            sb.append(lo).append("\n");
        }
        System.out.print(sb);
    }
}