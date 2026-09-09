import java.io.*;
import java.util.*;

public class dp1{
    public static void main(String[]a)throws Exception{
        BufferedReader _b=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer _s=new StringTokenizer(_b.readLine());
        int _T=Integer.parseInt(_s.nextToken());
        StringBuilder _out=new StringBuilder();

        while(_T-->0){
            int _N=Integer.parseInt(_b.readLine().trim());
            String _S=_b.readLine().trim();
            int _K=Integer.parseInt(_b.readLine().trim());

            int _l=0,_wc=0,_mx=0;
            for(int _r=0;_r<_N;_r++){
                if(_S.charAt(_r)=='W')_wc++;
                while(_wc>_K){
                    if(_S.charAt(_l)=='W')_wc--;
                    _l++;
                }
                _mx=Math.max(_mx,_r-_l+1);
            }
            _out.append(_mx).append('\n');
        }
        System.out.print(_out);
    }
}