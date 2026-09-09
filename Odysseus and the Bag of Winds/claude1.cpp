#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
const ll INF = LLONG_MAX / 2;

int main(){
    int T;
    scanf("%d", &T);
    while(T--){
        int M, A;
        scanf("%d %d", &M, &A);
        int Aidx = A - 1;

        vector<vector<int>> C(M, vector<int>(M));
        for(int i = 0; i < M; i++)
            for(int j = 0; j < M; j++)
                scanf("%d", &C[i][j]);

        int full = 1 << M;
        vector<vector<array<ll,2>>> dp(full, vector<array<ll,2>>(M, {INF, INF}));
        dp[1][0][0] = 0;

        for(int mask = 1; mask < full; mask++){
            if(!(mask & 1)) continue;
            bool aReached = (mask & (1 << Aidx)) != 0;

            for(int last = 0; last < M; last++){
                if(!(mask & (1 << last))) continue;

                for(int bag = 0; bag < 2; bag++){
                    ll cur = dp[mask][last][bag];
                    if(cur >= INF) continue;

                    for(int j = 0; j < M; j++){
                        if(mask & (1 << j)) continue;
                        int nmask = mask | (1 << j);

                        ll nc = cur + C[last][j];
                        if(nc < dp[nmask][j][bag])
                            dp[nmask][j][bag] = nc;

                        if(bag == 0 && aReached){
                            ll hc = cur + C[last][j] / 2;
                            if(hc < dp[nmask][j][1])
                                dp[nmask][j][1] = hc;
                        }
                    }
                }
            }
        }

        int fullmask = full - 1;
        ll ans = min(dp[fullmask][M-1][0], dp[fullmask][M-1][1]);
        printf("%lld\n", ans);
    }
    return 0;
}