import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        in.nextToken(); int t = (int) in.nval;
        StringBuilder sb = new StringBuilder();

        while(t-- > 0){
            in.nextToken(); long p = (long) in.nval;
            in.nextToken(); long c = (long) in.nval;
            in.nextToken(); long s = (long) in.nval;
            in.nextToken(); long l = (long) in.nval;

            long made = l / p;
            long got = 0;
            if(s <= l){
                long k = (l - s) / c + 1;
                got = (s / p >= 1) ? k : Math.max(0, k - 1);
            }
            sb.append(made - got).append('\n');
        }
        System.out.print(sb);
    }
}