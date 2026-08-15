import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int t = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();
        
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] a = new int[n];
            int minVal = Integer.MAX_VALUE;
            
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                if (a[i] < minVal) {
                    minVal = a[i];
                }
            }
            
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] == minVal) {
                    count++;
                }
            }
            
            if (count == 1) {
                sb.append("YES\n");
            } else {
                boolean possible = false;
                for (int i = 0; i < n; i++) {
                    if (a[i] % minVal != 0) {
                        possible = true;
                        break;
                    }
                }
                if (possible) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }
        System.out.print(sb);
    }
}