#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int T;
    cin >> T;
    
    while (T--) {
        int M, A;
        cin >> M >> A;
        
        vector<vector<int>> C(M + 1, vector<int>(M + 1));
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= M; j++) {
                cin >> C[i][j];
            }
        }
        
        int fullMask = (1 << M) - 1;
        vector<vector<int>> dp(fullMask + 1, vector<int>(M + 1, INF));
        vector<vector<int>> dpBag(fullMask + 1, vector<int>(M + 1, INF));
        
        dp[1][1] = 0;
        dpBag[1][1] = INF;
        
        for (int mask = 1; mask <= fullMask; mask++) {
            for (int last = 1; last <= M; last++) {
                if (!(mask & (1 << (last - 1)))) continue;
                
                if (dp[mask][last] < INF) {
                    for (int next = 1; next <= M; next++) {
                        if (mask & (1 << (next - 1))) continue;
                        
                        int newMask = mask | (1 << (next - 1));
                        int newCost = dp[mask][last] + C[last][next];
                        dp[newMask][next] = min(dp[newMask][next], newCost);
                        
                        if (last == A || next == A) {
                            int newCostBag = dp[mask][last] + C[last][next] / 2;
                            dpBag[newMask][next] = min(dpBag[newMask][next], newCostBag);
                        } else {
                            int newCostBag = dp[mask][last] + C[last][next];
                            dpBag[newMask][next] = min(dpBag[newMask][next], newCostBag);
                        }
                    }
                }
                
                if (dpBag[mask][last] < INF) {
                    for (int next = 1; next <= M; next++) {
                        if (mask & (1 << (next - 1))) continue;
                        
                        int newMask = mask | (1 << (next - 1));
                        int newCost = dpBag[mask][last] + C[last][next];
                        dpBag[newMask][next] = min(dpBag[newMask][next], newCost);
                    }
                }
            }
        }
        
        cout << dpBag[fullMask][M] << "\n";
    }
    
    return 0;
}