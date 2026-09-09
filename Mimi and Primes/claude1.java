import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken();
        int t = (int) st.nval;
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            st.nextToken();
            int n = (int) st.nval;
            long g = 0;
            for (int i = 0; i < n; i++) {
                st.nextToken();
                long x = (long) st.nval;
                g = gcd(g, x);
            }
            long ans = -1;
            for (long p = 2; p * p <= g; p++) {
                if (g % p == 0) {
                    ans = p;
                    while (g % p == 0) g /= p;
                }
            }
            if (g > 1) ans = g;
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
