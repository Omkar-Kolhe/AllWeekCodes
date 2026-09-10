import java.io.*;
import java.util.*;

public class claude {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(System.in, 1 << 16));
        int T = nextInt(in);
        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            int N = nextInt(in);
            int[] A = new int[N];
            for(int i = 0; i < N; i++) A[i] = nextInt(in);

            int[] nextGreater = new int[N];
            Arrays.fill(nextGreater, N);
            int[] st = new int[N];
            int top = -1;

            for(int i = 0; i < N; i++){
                while(top >= 0 && A[st[top]] < A[i]){
                    nextGreater[st[top]] = i;
                    top--;
                }
                st[++top] = i;
            }

            for(int i = 0; i < N; i++){
                int ans;
                if(nextGreater[i] != N) ans = nextGreater[i] - i;
                else ans = N - 1 - i;
                sb.append(ans);
                sb.append(i + 1 < N ? ' ' : '\n');
            }
        }

        System.out.print(sb);
    }

    private static int nextInt(DataInputStream in) throws IOException {
        int ret = 0;
        int b = in.read();
        while(b < '0' || b > '9') b = in.read();
        while(b >= '0' && b <= '9'){
            ret = ret * 10 + (b - '0');
            b = in.read();
        }
        return ret;
    }
}
