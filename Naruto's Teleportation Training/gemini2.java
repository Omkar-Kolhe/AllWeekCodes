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
        int t = Integer.parseInt(tok);

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            long a = Long.parseLong(nextToken());
            long b = Long.parseLong(nextToken());
            long c = Long.parseLong(nextToken());
            long d = Long.parseLong(nextToken());

            if (d < b) {
                sb.append("-1\n");
                continue;
            }

            long diagMoves = d - b;
            long targetX = a + diagMoves;

            if (c > targetX) {
                sb.append("-1\n");
            } else {
                long leftMoves = targetX - c;
                sb.append(diagMoves + leftMoves).append("\n");
            }
        }
        System.out.print(sb);
    }
}