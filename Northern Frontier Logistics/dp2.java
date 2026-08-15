import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCases = scanner.nextInt();

        while (testCases-- > 0) {
            int numCrates = scanner.nextInt();
            int deadlineDays = scanner.nextInt();

            int[] weights = new int[numCrates];
            int left = 0;
            int right = 0;

            for (int i = 0; i < numCrates; i++) {
                weights[i] = scanner.nextInt();
                left = Math.max(left, weights[i]);
                right += weights[i];
            }

            while (left < right) {
                int mid = left + (right - left) / 2;

                int daysNeeded = 1;
                int loadToday = 0;

                for (int w : weights) {
                    if (loadToday + w > mid) {
                        daysNeeded++;
                        loadToday = 0;
                    }
                    loadToday += w;
                }

                if (daysNeeded <= deadlineDays) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            System.out.println(left);
        }

        scanner.close();
    }
}