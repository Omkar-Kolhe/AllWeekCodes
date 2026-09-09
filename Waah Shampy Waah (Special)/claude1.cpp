#include <bits/stdc++.h>
using namespace std;

int main(){
    int t;
    scanf("%d", &t);
    while(t--){
        long long n, m;
        scanf("%lld %lld", &n, &m);
        
        long long maxAntichain = (m + 1) / 2;
        
        if(n > maxAntichain){
            printf("-1\n");
        } else {
            long long start = m - n + 1;
            for(long long i = start; i <= m; i++){
                printf("%lld", i);
                if(i != m) printf(" ");
            }
            printf("\n");
        }
    }
    return 0;
}