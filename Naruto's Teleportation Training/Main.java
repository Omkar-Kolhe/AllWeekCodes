import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int tc=Integer.parseInt(br.readLine().trim());
        StringBuilder res=new StringBuilder();
        while(tc-->0){
            StringTokenizer st=new StringTokenizer(br.readLine());
            long a=Long.parseLong(st.nextToken());
            long b=Long.parseLong(st.nextToken());
            long c=Long.parseLong(st.nextToken());
            long d=Long.parseLong(st.nextToken());
            if(d<b){res.append(-1).append("\n");continue;}
            long k=d-b;
            long z=a+k;
            if(z<c)res.append(-1).append("\n");
            else res.append(k+z-c).append("\n");
        }
        System.out.print(res);
    }
}