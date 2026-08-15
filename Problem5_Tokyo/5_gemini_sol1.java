
// 2_gemini_sol1.java
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        while (line != null && line.trim().isEmpty()) {
            line = br.readLine();
        }
        if (line == null)
            return;

        StringTokenizer st = new StringTokenizer(line);
        int t = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < t; tc++) {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            int n = Integer.parseInt(st.nextToken());

            Map<Long, Integer> firstOccurrence = new HashMap<>();
            firstOccurrence.put(0L, 0);

            long currentPrefix = 0;
            int maxLen = 0;
            int bestS = 0;
            int bestE = 0;

            for (int i = 1; i <= n; i++) {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
                char c = st.nextToken().charAt(0);
                long x = Long.parseLong(st.nextToken());

                if (c == 'L') {
                    currentPrefix += x;
                } else {
                    currentPrefix -= x;
                }

                if (firstOccurrence.containsKey(currentPrefix)) {
                    int prevIndex = firstOccurrence.get(currentPrefix);
                    int length = i - prevIndex;
                    if (length > maxLen) {
                        maxLen = length;
                        bestS = prevIndex + 1;
                        bestE = i;
                    }
                } else {
                    firstOccurrence.put(currentPrefix, i);
                }
            }

            if (maxLen == 0) {
                sb.append("0 0 0\n");
            } else {
                sb.append(maxLen).append(" ").append(bestS).append(" ").append(bestE).append("\n");
            }
        }

        System.out.print(sb.toString());
    }
}