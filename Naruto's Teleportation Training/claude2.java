import java.io.*;
import java.util.*;

public class claude2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            long d = Long.parseLong(st.nextToken());

            if (d < b) {
                sb.append(-1).append('\n');
                continue;
            }

            long diagonalMoves = d - b;
            long xAfterDiagonal = a + diagonalMoves;

            if (xAfterDiagonal < c) {
                sb.append(-1).append('\n');
            } else {
                long leftMoves = xAfterDiagonal - c;
                sb.append(diagonalMoves + leftMoves).append('\n');
            }
        }

        System.out.print(sb);
    }
}