import java.io.*;
import java.util.*;

/*
 * Problem: Kafka Ka Kalesh
 * 
 * Producer: 1 message every P seconds, first at t = P.
 * Consumer: tries every C seconds, first at t = S.
 * Consumer can only read if buffer >= 1; otherwise skips.
 * If write and read happen together, write happens first.
 * C >= P is guaranteed.
 * 
 * Goal: number of unread messages at time L.
 *
 * Logic:
 * - produced = floor(L / P)
 * - attempts = number of k >= 0 such that S + k*C <= L
 *            = 0 if S > L, else floor((L - S) / C) + 1
 * - Because C >= P, at most the first attempt (at t = S) can fail,
 *   and that happens only if S < P and S <= L.
 * - successful = attempts, or attempts - 1 in that special case.
 * - lag = produced - successful
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        while (line != null && line.trim().isEmpty()) {
            line = br.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        int N = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            // Read next non-empty line for test case
            line = br.readLine();
            while (line != null && line.trim().isEmpty()) {
                line = br.readLine();
            }
            if (line == null) break;

            st = new StringTokenizer(line);
            long P = Long.parseLong(st.nextToken());
            long C = Long.parseLong(st.nextToken());
            long S = Long.parseLong(st.nextToken());
            long L = Long.parseLong(st.nextToken());

            // Total messages produced by time L
            long produced = L / P;

            // Number of consumer attempts up to time L
            long attempts = 0;
            if (S <= L) {
                attempts = (L - S) / C + 1;
            }

            // Determine successful reads
            long successful = attempts;

            // If consumer starts before first message and has at least one attempt,
            // the first attempt fails.
            if (S < P && S <= L) {
                successful = Math.max(0L, attempts - 1);
            }

            long lag = produced - successful;
            sb.append(lag).append('\n');
        }

        System.out.print(sb.toString());
    }
}