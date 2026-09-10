import java.io.*;
import java.util.*;

public class sol2_deepseek {
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        StringBuilder sb = new StringBuilder();
        int T = fs.nextInt();
        while (T-- > 0) {
            int N = fs.nextInt();
            long[] arr = new long[N];
            for (int i = 0; i < N; i++) {
                long x = fs.nextLong();
                int cnt = 0;
                while ((x & 1) == 0) {
                    x >>= 1;
                    cnt++;
                }
                // pack: odd part in high bits, exponent in low 6 bits
                arr[i] = (x << 6) | cnt;
            }
            Arrays.sort(arr);
            long ans = 0;
            int i = 0;
            while (i < N) {
                long odd = arr[i] >>> 6;
                int mx = 0;
                while (i < N && (arr[i] >>> 6) == odd) {
                    int exp = (int) (arr[i] & 63);
                    if (exp > mx)
                        mx = exp;
                    i++;
                }
                ans += mx;
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }

    static class FastScanner {
        BufferedInputStream in = new BufferedInputStream(System.in);
        byte[] buf = new byte[1 << 16];
        int ptr = 0, len = 0;

        int nextByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buf);
                ptr = 0;
                if (len <= 0)
                    return -1;
            }
            return buf[ptr++];
        }

        int nextInt() throws IOException {
            int c = nextByte();
            while (c <= ' ')
                c = nextByte();
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = nextByte();
            }
            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = nextByte();
            }
            return val * sign;
        }

        long nextLong() throws IOException {
            int c = nextByte();
            while (c <= ' ')
                c = nextByte();
            long sign = 1;
            if (c == '-') {
                sign = -1;
                c = nextByte();
            }
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = nextByte();
            }
            return val * sign;
        }
    }
}