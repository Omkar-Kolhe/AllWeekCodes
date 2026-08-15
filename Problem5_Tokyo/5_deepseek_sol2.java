import java.io.*;
import java.util.*;

class Main {
    static class Answer {
        int length;
        int start;
        int end;

        Answer(int length, int start, int end) {
            this.length = length;
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());

            long[] prefSum = new long[N + 1];

            for (int i = 1; i <= N; i++) {
                String line = br.readLine().trim();
                String[] parts = line.split("\\s+");
                char direction = parts[0].charAt(0);
                long angle = Long.parseLong(parts[1]);

                long val = (direction == 'L') ? angle : -angle;
                prefSum[i] = prefSum[i - 1] + val;
            }

            Map<Long, Integer> earliestPos = new TreeMap<>();
            earliestPos.put(0L, 0);

            Answer best = new Answer(0, 0, 0);

            for (int i = 1; i <= N; i++) {
                long sum = prefSum[i];

                if (earliestPos.containsKey(sum)) {
                    int s = earliestPos.get(sum) + 1;
                    int e = i;
                    int len = e - s + 1;

                    if (len > best.length || (len == best.length && s < best.start)) {
                        best = new Answer(len, s, e);
                    }
                } else {
                    earliestPos.put(sum, i);
                }
            }

            if (best.length == 0) {
                pw.println("0 0 0");
            } else {
                pw.println(best.length + " " + best.start + " " + best.end);
            }
        }

        pw.flush();
        pw.close();
        br.close();
    }
}