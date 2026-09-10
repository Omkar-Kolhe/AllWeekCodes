import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = null;

        String line = reader.readLine();
        if (line == null) return;
        int t = Integer.parseInt(line.trim());

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < t; i++) {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                String nextLine = reader.readLine();
                if (nextLine == null) break;
                tokenizer = new StringTokenizer(nextLine);
            }

            long P = Long.parseLong(tokenizer.nextToken());
            long C = Long.parseLong(tokenizer.nextToken());
            long S = Long.parseLong(tokenizer.nextToken());
            long L = Long.parseLong(tokenizer.nextToken());

            long produced = L / P;
            long consumed = 0;

            if (S <= L) {
                long attempts = 1 + (L - S) / C;
                long failed = (S < P) ? 1 : 0;
                consumed = attempts - failed;
            }

            long lag = produced - consumed;
            out.append(lag).append("\n");
        }

        System.out.print(out);
    }
}