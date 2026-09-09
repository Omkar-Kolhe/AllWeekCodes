import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = new StringTokenizer("");

    static String nextToken() throws IOException {
        while (!st.hasMoreTokens()) {
            String line = br.readLine();
            if (line == null) return null;
            st = new StringTokenizer(line);
        }
        return st.nextToken();
    }

    public static void main(String[] args) throws IOException {
        String tok = nextToken();
        if (tok == null) return;
        int T = Integer.parseInt(tok);

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(nextToken());
            String S = nextToken();
            int K = Integer.parseInt(nextToken());

            int maxLen = 0;
            int wCount = 0;
            int left = 0;

            for (int right = 0; right < N; right++) {
                if (S.charAt(right) == 'W') {
                    wCount++;
                }
                while (wCount > K) {
                    if (S.charAt(left) == 'W') {
                        wCount--;
                    }
                    left++;
                }
                maxLen = Math.max(maxLen, right - left + 1);
            }
            sb.append(maxLen).append("\n");
        }
        System.out.print(sb);
    }
}