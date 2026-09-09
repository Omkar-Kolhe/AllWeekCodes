import java.util.*;
import java.io.*;

public class claude1 {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(System.in, 1<<16));
        int t = nextInt(in);
        StringBuilder sb = new StringBuilder();

        while(t-- > 0){
            int n = nextInt(in), m = nextInt(in), k = nextInt(in);
            char[][] grid = new char[n+1][];
            for(int i=1;i<=n;i++){
                grid[i] = nextToken(in, m).toCharArray();
            }
            String s = nextToken(in, k);

            int r = 1, c = 1;
            for(int i=0;i<k;i++){
                int nr = r, nc = c;
                char ch = s.charAt(i);
                if(ch=='U') nr--;
                else if(ch=='D') nr++;
                else if(ch=='L') nc--;
                else nc++;

                if(nr>=1 && nr<=n && nc>=1 && nc<=m && grid[nr][nc-1]=='.'){
                    r = nr; c = nc;
                }
            }
            sb.append(r).append(" ").append(c).append("\n");
        }
        System.out.print(sb);
    }

    private static int nextInt(DataInputStream in) throws IOException {
        int ret = 0, b;
        boolean neg = false;
        do { b = in.read(); } while (b < '0' && b != '-');
        if(b=='-'){ neg = true; b = in.read(); }
        while(b >= '0'){ ret = ret*10 + b - '0'; b = in.read(); }
        return neg ? -ret : ret;
    }

    private static String nextToken(DataInputStream in, int len) throws IOException {
        StringBuilder res = new StringBuilder(len);
        int b;
        do { b = in.read(); } while (b == ' ' || b == '\n' || b == '\r');
        while(b != -1 && b != ' ' && b != '\n' && b != '\r'){
            res.append((char)b);
            b = in.read();
        }
        return res.toString();
    }
}