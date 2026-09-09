import java.util.*;
import java.io.*;

public class gemini1 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        if(!sc.hasNextInt()) return;
        int t = sc.nextInt();
        
        StringBuilder out = new StringBuilder();
        while(t-- > 0){
            int len = sc.nextInt();
            String s = sc.next();
            int limit = sc.nextInt();
            
            int ans = 0, cur_w = 0, p1 = 0;
            for(int p2 = 0; p2 < len; p2++){
                if(s.charAt(p2) == 'W') cur_w++;
                while(cur_w > limit){
                    if(s.charAt(p1) == 'W') cur_w--;
                    p1++;
                }
                int curLen = p2 - p1 + 1;
                if(curLen > ans) ans = curLen;
            }
            out.append(ans).append("\n");
        }
        System.out.print(out);
    }
}