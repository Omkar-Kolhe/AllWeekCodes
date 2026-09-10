import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

    static class Submission implements Comparable<Submission> {
        long processingTime;
        long deadline;

        public Submission(long processingTime, long deadline) {
            this.processingTime = processingTime;
            this.deadline = deadline;
        }

        @Override
        public int compareTo(Submission other) {
            if (this.deadline != other.deadline) {
                return Long.compare(this.deadline, other.deadline);
            }
            return Long.compare(other.processingTime, this.processingTime);
        }
    }

    private static boolean isFeasible(int containerCount, Submission[] submissions) {
        PriorityQueue<Long> containerAvailableTimes = new PriorityQueue<>();
        
        for (int i = 0; i < containerCount; i++) {
            containerAvailableTimes.offer(0L);
        }
        
        for (Submission submission : submissions) {
            long earliestAvailableTime = containerAvailableTimes.poll();
            long completionTime = earliestAvailableTime + submission.processingTime;
            
            if (completionTime > submission.deadline) {
                return false;
            }
            
            containerAvailableTimes.offer(completionTime);
        }
        
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        if (line == null || line.trim().isEmpty()) {
            return;
        }
        
        int totalTestCases = Integer.parseInt(line.trim());
        StringBuilder outputBuilder = new StringBuilder();
        
        for (int t = 0; t < totalTestCases; t++) {
            int numberOfSubmissions = Integer.parseInt(reader.readLine().trim());
            Submission[] submissions = new Submission[numberOfSubmissions];
            
            for (int i = 0; i < numberOfSubmissions; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                long processingTime = Long.parseLong(tokenizer.nextToken());
                long deadline = Long.parseLong(tokenizer.nextToken());
                submissions[i] = new Submission(processingTime, deadline);
            }
            
            Arrays.sort(submissions);
            
            int low = 1;
            int high = numberOfSubmissions;
            int minimumContainersRequired = numberOfSubmissions;
            
            while (low <= high) {
                int mid = low + (high - low) / 2;
                
                if (isFeasible(mid, submissions)) {
                    minimumContainersRequired = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            
            outputBuilder.append(minimumContainersRequired).append("\n");
        }
        
        System.out.print(outputBuilder.toString());
    }
}
