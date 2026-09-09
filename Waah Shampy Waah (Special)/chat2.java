import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int testCases = Integer.parseInt(reader.readLine().trim());

        while (testCases-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            long n = Long.parseLong(tokenizer.nextToken());
            long m = Long.parseLong(tokenizer.nextToken());

            long firstNumber = m / 2 + 1;
            long availableNumbers = m - firstNumber + 1;

            if (availableNumbers < n) {
                answer.append("-1\n");
                continue;
            }

            for (long i = 0; i < n; i++) {
                if (i != 0) {
                    answer.append(' ');
                }
                answer.append(firstNumber + i);
            }

            answer.append('\n');
        }

        System.out.print(answer);
    }
}