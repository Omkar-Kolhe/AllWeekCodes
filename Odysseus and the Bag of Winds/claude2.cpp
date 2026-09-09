#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    const ll INF = LLONG_MAX / 2;
    
    int T;
    cin >> T;
    
    while (T--) {
        int M, A;
        cin >> M >> A;
        int Aidx = A - 1;
        
        vector<vector<int>> C(M, vector<int>(M));
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                cin >> C[i][j];
            }
        }
        
        int fullMask = 1 << M;
        vector<vector<vector<ll>>> dp(fullMask, vector<vector<ll>>(M, vector<ll>(2, INF)));
        
        dp[1][0][0] = 0;
        
        for (int mask = 1; mask < fullMask; mask++) {
            if (!(mask & 1)) continue;
            
            bool aVisited = (mask & (1 << Aidx)) != 0;
            
            for (int last = 0; last < M; last++) {
                if (!(mask & (1 << last))) continue;
                
                for (int bagState = 0; bagState < 2; bagState++) {
                    ll currentCost = dp[mask][last][bagState];
                    if (currentCost >= INF) continue;
                    
                    for (int next = 0; next < M; next++) {
                        if (mask & (1 << next)) continue;
                        
                        int newMask = mask | (1 << next);
                        
                        ll normalCost = currentCost + C[last][next];
                        if (normalCost < dp[newMask][next][bagState]) {
                            dp[newMask][next][bagState] = normalCost;
                        }
                        
                        if (bagState == 0 && aVisited) {
                            ll halvedCost = currentCost + C[last][next] / 2;
                            if (halvedCost < dp[newMask][next][1]) {
                                dp[newMask][next][1] = halvedCost;
                            }
                        }
                    }
                }
            }
        }
        
        int finalMask = fullMask - 1;
        ll answer = min(dp[finalMask][M-1][0], dp[finalMask][M-1][1]);
        cout << answer << "\n";
    }
    
    return 0;
}