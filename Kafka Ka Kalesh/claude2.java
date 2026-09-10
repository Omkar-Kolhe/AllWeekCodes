import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < N; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long P = Long.parseLong(st.nextToken());
            long C = Long.parseLong(st.nextToken());
            long S = Long.parseLong(st.nextToken());
            long L = Long.parseLong(st.nextToken());

            long produced = L / P;
            long reads = 0;
            if (S <= L) {
                long K = (L - S) / C + 1;
                if (S / P >= 1) {
                    reads = K;
                } else {
                    reads = Math.max(0L, K - 1);
                }
            }
            sb.append(produced - reads).append("\n");
        }
        System.out.print(sb);
    }
}