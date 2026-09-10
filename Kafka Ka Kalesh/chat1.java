import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            long P = Long.parseLong(st.nextToken());
            long C = Long.parseLong(st.nextToken());
            long S = Long.parseLong(st.nextToken());
            long L = Long.parseLong(st.nextToken());

            // How many messages are produced by time L?
            long messages = L / P;

            // How many times does the consumer try to read?
            long reads = 0;

            if (L >= S) {
                reads = (L - S) / C + 1;
            }

            /*
             * If the consumer's first attempt happens before
             * the first message is produced, that attempt fails.
             */
            if (S < P && reads > 0) {
                reads--;
            }

            long lag = messages - reads;

            answer.append(lag).append('\n');
        }

        System.out.print(answer);
    }
}