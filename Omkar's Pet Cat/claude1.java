import java.util.*;
import java.io.*;

public class Main {
    static int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            int N = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] A = new int[N];
            for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
            int g = A[0];
            for(int i = 1; i < N; i++) g = gcd(g, A[i]);
            int cnt = 0;
            for(int x : A) if(x == g) cnt++;
            sb.append(cnt >= 2 ? "NO" : "YES").append("\n");
        }
        System.out.print(sb);
    }
}