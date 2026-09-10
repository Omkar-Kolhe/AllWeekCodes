import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
        InputStream in;
        byte[] buffer = new byte[1 << 16];
        int head = 0, tail = 0;

        public FastScanner(InputStream in) {
            this.in = in;
        }

        int read() throws IOException {
            if (head >= tail) {
                head = 0;
                tail = in.read(buffer, 0, buffer.length);
                if (tail <= 0) return -1;
            }
            return buffer[head++];
        }

        int nextInt() throws IOException {
            int c = read();
            while (c <= 32) {
                if (c == -1) return -1;
                c = read();
            }
            int res = 0;
            while (c > 32) {
                res = res * 10 + c - '0';
                c = read();
            }
            return res;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int T = sc.nextInt();
        if (T == -1) return;
        StringBuilder out = new StringBuilder();
        
        for (int t = 0; t < T; t++) {
            int N = sc.nextInt();
            // Encode (odd_part, exponent) into a primitive long to save memory/time
            long[] items = new long[N];
            
            for (int i = 0; i < N; i++) {
                int x = sc.nextInt();
                int k = Integer.numberOfTrailingZeros(x);
                int odd = x >> k;
                items[i] = ((long) odd << 32) | (k & 0xFFFFFFFFL);
            }
            
            Arrays.sort(items);
            
            long ans = 0;
            int currentOdd = -1;
            int currentMaxK = 0;
            
            for (int i = 0; i < N; i++) {
                int odd = (int) (items[i] >> 32);
                int k = (int) items[i];
                
                if (odd != currentOdd) {
                    ans += currentMaxK;
                    currentOdd = odd;
                    currentMaxK = k;
                } else {
                    if (k > currentMaxK) {
                        currentMaxK = k;
                    }
                }
            }
            ans += currentMaxK;
            out.append(ans).append("\n");
        }
        System.out.print(out);
    }
}