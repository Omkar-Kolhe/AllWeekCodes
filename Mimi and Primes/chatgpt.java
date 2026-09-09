import java.io.*;
import java.util.*;

public class Main {

    static Set<Long> primeFactors(long x) {
        Set<Long> factors = new HashSet<>();

        while (x % 2 == 0) {
            factors.add(2L);
            x /= 2;
        }

        for (long p = 3; p * p <= x; p += 2) {
            if (x % p == 0) {
                factors.add(p);

                while (x % p == 0) {
                    x /= p;
                }
            }
        }

        if (x > 1) {
            factors.add(x);
        }

        return factors;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }

        Set<Long> common = primeFactors(a[0]);

        for (int i = 1; i < n; i++) {
            Set<Long> current = primeFactors(a[i]);
            common.retainAll(current);

            if (common.isEmpty())
                break;
        }

        if (common.isEmpty()) {
            System.out.println("DNE");
        } else {
            long answer = Collections.max(common);
            System.out.println(answer);
        }
    }
}
