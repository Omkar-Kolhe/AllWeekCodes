import java.io.*;
import java.util.*;

public class sol1_claude {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(System.in, 1 << 16));
        int T = nextInt(in);
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = nextInt(in);
            HashMap<Long, Integer> maxExp = new HashMap<>(N * 2);
            for (int i = 0; i < N; i++) {
                long a = nextInt(in);
                int k = 0;
                while (a % 2 == 0) {
                    a /= 2;
                    k++;
                }
                Integer cur = maxExp.get(a);
                if (cur == null || cur < k) {
                    maxExp.put(a, k);
                }
            }
            long ans = 0;
            for (int v : maxExp.values())
                ans += v;
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }

    private static int nextInt(DataInputStream in) throws IOException {
        int ret = 0;
        int b = in.read();
        while (b < '0' || b > '9') {
            b = in.read();
        }
        while (b >= '0' && b <= '9') {
            ret = ret * 10 + (b - '0');
            b = in.read();
        }
        return ret;
    }
}