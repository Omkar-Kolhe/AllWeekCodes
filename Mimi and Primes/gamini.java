import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MimiAndPrimes {
    
    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        String line = br.readLine();
        if (line == null) return;
        int t = Integer.parseInt(line.trim());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            st = new StringTokenizer(br.readLine());
            long g = 0;
            for (int i = 0; i < n; i++) {
                long a = Long.parseLong(st.nextToken());
                g = gcd(g, a);
            }

            if (g == 1) {
                sb.append("-1\n");
                continue;
            }

            long maxPrime = -1;
            long tempG = g;
            for (long i = 2; i * i <= tempG; i++) {
                if (tempG % i == 0) {
                    maxPrime = i;
                    while (tempG % i == 0) {
                        tempG /= i;
                    }
                }
            }
            if (tempG > 1) {
                maxPrime = Math.max(maxPrime, tempG);
            }

            sb.append(maxPrime).append("\n");
        }
        System.out.print(sb);
    }
}
