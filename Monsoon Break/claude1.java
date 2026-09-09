import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int tc=Integer.parseInt(br.readLine().trim());
        StringBuilder res=new StringBuilder();
        while(tc-->0){
            int n=Integer.parseInt(br.readLine().trim());
            char[] a=br.readLine().trim().toCharArray();
            int k=Integer.parseInt(br.readLine().trim());
            int l=0,c=0,mx=0;
            for(int r=0;r<n;r++){
                if(a[r]=='W')c++;
                while(c>k){
                    if(a[l]=='W')c--;
                    l++;
                }
                mx=Math.max(mx,r-l+1);
            }
            res.append(mx).append("\n");
        }
        System.out.print(res);
    }
}