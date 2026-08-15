import java.util.*;

public class Main {
    static int gg(int a, int b){
        while(b != 0){
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        StringBuilder res = new StringBuilder();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] a = new int[n];
            for(int i=0;i<n;i++) a[i] = sc.nextInt();
            int g = a[0];
            for(int i=1;i<n;i++) g = gg(g, a[i]);
            int c = 0;
            for(int i=0;i<n;i++) if(a[i] == g) c++;
            res.append(c >= 2 ? "NO" : "YES").append("\n");
        }
        System.out.print(res);
    }
}