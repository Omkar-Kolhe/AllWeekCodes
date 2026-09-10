import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            while (line != null && line.trim().isEmpty()) {
                line = br.readLine();
            }
            st = new StringTokenizer(line);
            long P = Long.parseLong(st.nextToken());
            long C = Long.parseLong(st.nextToken());
            long S = Long.parseLong(st.nextToken());
            long L = Long.parseLong(st.nextToken());

            long produced = L / P;

            long attempts = 0;
            if (S <= L) {
                attempts = (L - S) / C + 1;
            }

            long successful = attempts;
            if (S < P && S <= L) {
                successful = Math.max(0L, attempts - 1);
            }

            long lag = produced - successful;
            sb.append(lag).append('\n');
        }
        System.out.print(sb.toString());
    }
}