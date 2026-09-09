#include <bits/stdc++.h>
using namespace std;

int main(){
    int T;
    scanf("%d", &T);
    while(T--){
        int N;
        scanf("%d", &N);
        vector<int> H(N+1);
        for(int i=1;i<=N;i++) scanf("%d", &H[i]);

        vector<char> reachable(N+1, 0);
        reachable[1] = 1;

        for(int j=2;j<=N;j++){
            int h = H[j];
            for(int d=1; (long long)d*d <= h; d++){
                if(h % d != 0) continue;
                int d1 = d, d2 = h/d;
                if(d1 >= 2 && j-d1 >= 1 && reachable[j-d1]){
                    reachable[j] = 1;
                    break;
                }
                if(d2 >= 2 && d2 != d1 && j-d2 >= 1 && reachable[j-d2]){
                    reachable[j] = 1;
                    break;
                }
            }
        }

        printf(reachable[N] ? "YES\n" : "NO\n");
    }
    return 0;
}