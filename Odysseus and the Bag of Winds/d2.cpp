#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const int INF = 1e9 + 7;

void solve() {
    int M, A;
    cin >> M >> A;
    
    vector<vector<int>> cost(M + 1, vector<int>(M + 1));
    for (int i = 1; i <= M; i++) {
        for (int j = 1; j <= M; j++) {
            cin >> cost[i][j];
        }
    }
    
    int totalMask = (1 << M) - 1;
    vector<vector<int>> dpNoBag(totalMask + 1, vector<int>(M + 1, INF));
    vector<vector<int>> dpWithBag(totalMask + 1, vector<int>(M + 1, INF));
    
    dpNoBag[1][1] = 0;
    dpWithBag[1][1] = INF;
    
    for (int mask = 1; mask <= totalMask; mask++) {
        for (int current = 1; current <= M; current++) {
            if (!((mask >> (current - 1)) & 1)) continue;
            
            if (dpNoBag[mask][current] < INF) {
                for (int next = 1; next <= M; next++) {
                    if ((mask >> (next - 1)) & 1) continue;
                    
                    int newMask = mask | (1 << (next - 1));
                    
                    if (current == A) {
                        int costWithBag = dpNoBag[mask][current] + cost[current][next] / 2;
                        dpWithBag[newMask][next] = min(dpWithBag[newMask][next], costWithBag);
                    }
                    
                    int costWithoutBag = dpNoBag[mask][current] + cost[current][next];
                    dpNoBag[newMask][next] = min(dpNoBag[newMask][next], costWithoutBag);
                }
            }
            
            if (dpWithBag[mask][current] < INF) {
                for (int next = 1; next <= M; next++) {
                    if ((mask >> (next - 1)) & 1) continue;
                    
                    int newMask = mask | (1 << (next - 1));
                    int newCost = dpWithBag[mask][current] + cost[current][next];
                    dpWithBag[newMask][next] = min(dpWithBag[newMask][next], newCost);
                }
            }
        }
    }
    
    cout << dpWithBag[totalMask][M] << endl;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int T;
    cin >> T;
    
    while (T--) {
        solve();
    }
    
    return 0;
}