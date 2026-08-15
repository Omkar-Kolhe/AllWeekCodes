import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        StringBuilder res = new StringBuilder();
        while(t-- > 0){
            int n = sc.nextInt();
            int d = sc.nextInt();
            int[] w = new int[n];
            int mx = 0, tot = 0;
            for(int i=0;i<n;i++){
                w[i] = sc.nextInt();
                if(w[i] > mx) mx = w[i];
                tot += w[i];
            }
            int l = mx, r = tot, ans = tot;
            while(l <= r){
                int m = (l+r)/2;
                int day = 1, s = 0;
                boolean ok = true;
                for(int i=0;i<n;i++){
                    if(s + w[i] > m){
                        day++;
                        s = w[i];
                        if(day > d){
                            ok = false;
                            break;
                        }
                    }else{
                        s += w[i];
                    }
                }
                if(ok){
                    ans = m;
                    r = m - 1;
                }else{
                    l = m + 1;
                }
            }
            res.append(ans).append("\n");
        }
        System.out.print(res);
    }
}