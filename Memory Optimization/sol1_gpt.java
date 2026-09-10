import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder output = new StringBuilder();

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());

            StringTokenizer st = new StringTokenizer(br.readLine());

            HashMap<Integer, Integer> maxExponent = new HashMap<>();

            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(st.nextToken());

                int exponent = 0;

                while (x % 2 == 0) {
                    x /= 2;
                    exponent++;
                }

                int oldValue = maxExponent.getOrDefault(x, 0);

                if (exponent > oldValue) {
                    maxExponent.put(x, exponent);
                }
            }

            long answer = 0;

            for (int exponent : maxExponent.values()) {
                answer += exponent;
            }

            output.append(answer).append('\n');
        }

        System.out.print(output);
    }
}