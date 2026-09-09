import java.io.*;
import java.util.*;

class Package implements Comparable<Package> {
    long duration;
    long deadline;

    public Package(long duration, long deadline) {
        this.duration = duration;
        this.deadline = deadline;
    }

    @Override
    public int compareTo(Package other) {
        return Long.compare(this.deadline, other.deadline);
    }
}

public class Main {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    String line = reader.readLine();
                    if (line == null) return null;
                    tokenizer = new StringTokenizer(line);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(String[] args) {
        InputReader in = new InputReader();
        String firstToken = in.next();
        if (firstToken == null) return;
        
        int testCases = Integer.parseInt(firstToken);
        StringBuilder output = new StringBuilder();

        for (int t = 0; t < testCases; t++) {
            int n = in.nextInt();
            Package[] packages = new Package[n];
            
            for (int i = 0; i < n; i++) {
                long duration = in.nextLong();
                long deadline = in.nextLong();
                packages[i] = new Package(duration, deadline);
            }

            Arrays.sort(packages);
            
            long currentTime = 0;
            PriorityQueue<Long> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

            for (Package pkg : packages) {
                currentTime += pkg.duration;
                maxHeap.offer(pkg.duration);

                if (currentTime > pkg.deadline) {
                    currentTime -= maxHeap.poll();
                }
            }
            output.append(maxHeap.size()).append("\n");
        }
        System.out.print(output);
    }
}
