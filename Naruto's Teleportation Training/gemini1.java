import java.util.*;
import java.io.*;

public class gemini1 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        if(!sc.hasNextInt()) return;
        int t = sc.nextInt();
        
        StringBuilder out = new StringBuilder();
        while(t-- > 0){
            long x1 = sc.nextLong(), y1 = sc.nextLong();
            long x2 = sc.nextLong(), y2 = sc.nextLong();
            
            long stepY = y2 - y1;
            if(stepY < 0){
                out.append("-1\n");
            } else {
                long landX = x1 + stepY;
                if(x2 > landX){
                    out.append("-1\n");
                } else {
                    long stepX = landX - x2;
                    out.append(stepY + stepX).append("\n");
                }
            }
        }
        System.out.print(out);
    }
}