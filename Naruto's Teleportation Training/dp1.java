import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out=new PrintWriter(System.out);
        StringTokenizer st=new StringTokenizer("");
        String line=br.readLine();
        if(line==null)return;
        st=new StringTokenizer(line);
        int t=Integer.parseInt(st.nextToken());
        while(t-->0){
            while(!st.hasMoreTokens()){
                line=br.readLine();
                if(line==null)break;
                st=new StringTokenizer(line);
            }
            long a=Long.parseLong(st.nextToken());
            long b=Long.parseLong(st.nextToken());
            long c=Long.parseLong(st.nextToken());
            long d=Long.parseLong(st.nextToken());
            if(b>d || (d-c)<(b-a)){
                out.println(-1);
            }else{
                long ans=(a-c)+2*(d-b);
                out.println(ans);
            }
        }
        out.flush();
    }
}