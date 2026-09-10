import java.util.*;

public class Main {
    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    
    static long largestPrimeFactor(long n) {
        if (n <= 1) return -1;
        long largest = -1;
        
        if (n % 2 == 0) {
            largest = 2;
            while (n % 2 == 0) n /= 2;
        }
        
        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                largest = i;
                while (n % i == 0) n /= i;
            }
        }
        
        if (n > 1) largest = n;
        
        return largest;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while (t-- > 0) {
            int n = sc.nextInt();
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextLong();
            }
            
            long g = a[0];
            for (int i = 1; i < n; i++) {
                g = gcd(g, a[i]);
            }
            
            System.out.println(largestPrimeFactor(g));
        }
    }
}
