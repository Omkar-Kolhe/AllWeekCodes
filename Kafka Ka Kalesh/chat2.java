import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int N = Integer.parseInt(br.readLine().trim());

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            long P = Long.parseLong(st.nextToken());
            long C = Long.parseLong(st.nextToken());
            long S = Long.parseLong(st.nextToken());
            long L = Long.parseLong(st.nextToken());

            // Number of messages produced by time L
            long produced = L / P;

            // Number of consumer attempts by time L
            long attempts = 0;

            if (L >= S) {
                attempts = (L - S) / C + 1;
            }

            // If the consumer starts before the first message,
            // its first attempt fails.
            long successfulReads = attempts;

            if (S < P && attempts > 0) {
                successfulReads--;
            }

            long lag = produced - successfulReads;

            out.append(lag).append('\n');
        }

        System.out.print(out);
    }
}