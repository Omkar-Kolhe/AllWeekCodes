import java.io.*;
import java.util.*;

public class sol1_deepseek {
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        StringBuilder sb = new StringBuilder();
        int T = fs.nextInt();
        while (T-- > 0) {
            int N = fs.nextInt();
            HashMap<Long, Integer> maxExp = new HashMap<>();
            for (int i = 0; i < N; i++) {
                long x = fs.nextLong();
                int cnt = 0;
                while ((x & 1) == 0) {
                    x >>= 1;
                    cnt++;
                }
                Integer prev = maxExp.get(x);
                if (prev == null || prev < cnt) {
                    maxExp.put(x, cnt);
                }
            }
            long ans = 0;
            for (int v : maxExp.values()) {
                ans += v;
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