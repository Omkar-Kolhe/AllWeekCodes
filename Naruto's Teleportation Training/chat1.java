import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();

        while(t-->0){
            long a=s.nextLong(),b=s.nextLong(),c=s.nextLong(),d=s.nextLong();

            if(d<b){
                System.out.println(-1);
                continue;
            }

            long k=d-b;
            long z=a+k;

            if(z<c) System.out.println(-1);
            else System.out.println(k+z-c);
        }
    }
}