#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int INF = 1e9;

void solve() {
    int m, a;
    if (!(cin >> m >> a)) return;

    int aeolus = a - 1;
    int ithaca = m - 1;

    vector<vector<int>> cost(m, vector<int>(m));
    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < m; ++j) {
            cin >> cost[i][j];
        }
    }

    int full_mask = 1 << (m - 1);
    // dp[mask][u][bag_used]
    vector<vector<vector<int>>> dp(full_mask, vector<vector<int>>(m, vector<int>(2, INF)));

    dp[1][0][0] = 0;

    int final_intermediate_mask = full_mask - 1;
    int ans = INF;

    for (int mask = 1; mask < full_mask; mask += 2) {
        bool has_bag = (mask & (1 << aeolus)) != 0;

        for (int u = 0; u < m - 1; ++u) {
            for (int bag = 0; bag < 2; ++bag) {
                int cur_dist = dp[mask][u][bag];
                if (cur_dist >= INF) continue;

                if (mask == final_intermediate_mask) {
                    // Next stop must be Ithaca
                    // Option 1: without using bag
                    ans = min(ans, cur_dist + cost[u][ithaca]);
                    // Option 2: using bag if available
                    if (has_bag && bag == 0) {
                        ans = min(ans, cur_dist + cost[u][ithaca] / 2);
                    }
                } else {
                    for (int v = 1; v < m - 1; ++v) {
                        if (!(mask & (1 << v))) {
                            int next_mask = mask | (1 << v);

                            // Sail without bag
                            if (cur_dist + cost[u][v] < dp[next_mask][v][bag]) {
                                dp[next_mask][v][bag] = cur_dist + cost[u][v];
                            }

                            // Sail using bag
                            if (has_bag && bag == 0) {
                                int discounted = cur_dist + cost[u][v] / 2;
                                if (discounted < dp[next_mask][v][1]) {
                                    dp[next_mask][v][1] = discounted;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    cout << ans << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    if (cin >> t) {
        while (t--) {
            solve();
        }
    }
    return 0;
}