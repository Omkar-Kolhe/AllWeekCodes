#include <bits/stdc++.h>
using namespace std;

int main(){
    int t;
    scanf("%d", &t);
    while(t--){
        int n;
        scanf("%d", &n);
        vector<int> h(n+1);
        for(int i=1;i<=n;i++) scanf("%d", &h[i]);

        vector<char> ok(n+1, 0);
        ok[1] = 1;

        for(int j=2;j<=n;j++){
            int val = h[j];
            for(int d=1; (long long)d*d<=val; d++){
                if(val % d) continue;
                int d1 = d, d2 = val/d;
                if(d1>=2 && d1<=j-1 && ok[j-d1]) { ok[j]=1; break; }
                if(d2>=2 && d2<=j-1 && ok[j-d2]) { ok[j]=1; break; }
            }
        }
        puts(ok[n] ? "YES" : "NO");
    }
}