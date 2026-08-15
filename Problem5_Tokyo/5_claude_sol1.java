import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(System.in, 1 << 16));
        int T = nextInt(in);
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int N = nextInt(in);
            long[] prefix = new long[N + 1];

            for (int i = 1; i <= N; i++) {
                char dir = nextNonSpaceChar(in);
                long x = nextInt(in);
                long delta = (dir == 'L') ? x : -x;
                prefix[i] = prefix[i - 1] + delta;
            }

            HashMap<Long, Integer> firstOcc = new HashMap<>();
            firstOcc.put(0L, 0);

            long bestLen = 0;
            int bestS = 0, bestE = 0;

            for (int i = 1; i <= N; i++) {
                Integer occ = firstOcc.get(prefix[i]);
                if (occ == null) {
                    firstOcc.put(prefix[i], i);
                } else {
                    long len = i - occ;
                    if (len > bestLen) {
                        bestLen = len;
                        bestS = occ + 1;
                        bestE = i;
                    }
                }
            }

            if (bestLen == 0) {
                sb.append("0 0 0\n");
            } else {
                sb.append(bestLen).append(' ').append(bestS).append(' ').append(bestE).append('\n');
            }
        }

        System.out.print(sb);
    }

    private static int nextInt(DataInputStream in) throws IOException {
        int ret = 0;
        int b = in.read();
        while (b < '0' || b > '9') {
            if (b == -1)
                return -1;
            b = in.read();
        }
        while (b >= '0' && b <= '9') {
            ret = ret * 10 + (b - '0');
            b = in.read();
        }
        return ret;
    }

    private static char nextNonSpaceChar(DataInputStream in) throws IOException {
        int b = in.read();
        while (b == ' ' || b == '\n' || b == '\r' || b == '\t') {
            b = in.read();
        }
        return (char) b;
    }
}