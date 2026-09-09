import java.util.*;
import java.io.*;

public class ai_generated_java {

    static class Package {
        long processingTime;
        long deadline;

        Package(long processingTime, long deadline) {
            this.processingTime = processingTime;
            this.deadline = deadline;
        }
    }

    private static int solveTestCase(List<Package> packages) {
        packages.sort(Comparator.comparingLong(pkg -> pkg.deadline));

        PriorityQueue<Long> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        long accumulatedTime = 0;

        for (Package currentPackage : packages) {
            maxHeap.add(currentPackage.processingTime);
            accumulatedTime += currentPackage.processingTime;

            if (accumulatedTime > currentPackage.deadline) {
                long largestProcessingTime = maxHeap.poll();
                accumulatedTime -= largestProcessingTime;
            }
        }

        return maxHeap.size();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer tokenizer = new StreamTokenizer(reader);

        tokenizer.nextToken();
        int numberOfTestCases = (int) tokenizer.nval;

        StringBuilder outputBuilder = new StringBuilder();

        for (int testCaseIndex = 0; testCaseIndex < numberOfTestCases; testCaseIndex++) {
            tokenizer.nextToken();
            int numberOfPackages = (int) tokenizer.nval;

            List<Package> packages = new ArrayList<>(numberOfPackages);
            for (int i = 0; i < numberOfPackages; i++) {
                tokenizer.nextToken();
                long processingTime = (long) tokenizer.nval;
                tokenizer.nextToken();
                long deadline = (long) tokenizer.nval;
                packages.add(new Package(processingTime, deadline));
            }

            int successfulDeliveries = solveTestCase(packages);
            outputBuilder.append(successfulDeliveries).append("\n");
        }

        System.out.print(outputBuilder);
    }
}
