import java.io.*;
import java.util.*;

public class sol2_claude {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(System.in, 1 << 16));
        int T = nextInt(in);
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = nextInt(in);
            long[] keys = new long[N];
            for (int i = 0; i < N; i++) {
                long a = nextInt(in);
                int k = 0;
                while (a % 2 == 0) {
                    a /= 2;
                    k++;
                }
                // pack: high bits = oddPart, low 6 bits = exponent (exponent <= 29, fits in
                // [0,63])
                keys[i] = (a << 6) | k;
            }
            Arrays.sort(keys);
            long ans = 0;
            int i = 0;
            while (i < N) {
                long oddPart = keys[i] >> 6;
                int maxK = (int) (keys[i] & 63L);
                int j = i + 1;
                while (j < N && (keys[j] >> 6) == oddPart) {
                    int k = (int) (keys[j] & 63L);
                    if (k > maxK)
                        maxK = k;
                    j++;
                }
                ans += maxK;
                i = j;
            }
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