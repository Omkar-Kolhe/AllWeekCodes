import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCases = scanner.nextInt();

        while (testCases-- > 0) {
            int numBlocks = scanner.nextInt();

            long[] values = new long[numBlocks];
            long minimum = Long.MAX_VALUE;

            for (int i = 0; i < numBlocks; i++) {
                values[i] = scanner.nextLong();
                minimum = Math.min(minimum, values[i]);
            }

            boolean hasNonMultiple = false;
            int minFrequency = 0;

            for (long v : values) {
                if (v % minimum != 0)
                    hasNonMultiple = true;
                if (v == minimum)
                    minFrequency++;
            }

            boolean possible = hasNonMultiple || (minFrequency == 1);

            System.out.println(possible ? "YES" : "NO");
        }

        scanner.close();
    }
}