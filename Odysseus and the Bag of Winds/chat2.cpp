#include <iostream>
#include <vector>
#include <array>
#include <algorithm>
#include <limits>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    const long long INF = numeric_limits<long long>::max() / 4;

    while (T--) {
        int M, A;
        cin >> M >> A;
        --A;

        vector<vector<long long>> cost(M, vector<long long>(M));

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < M; ++j) {
                cin >> cost[i][j];
            }
        }

        const int MASKS = 1 << M;

        vector<vector<array<long long, 2>>> dp(
            MASKS, vector<array<long long, 2>>(M, {INF, INF})
        );

        dp[1][0][0] = 0;

        for (int mask = 1; mask < MASKS; ++mask) {
            if ((mask & 1) == 0) {
                continue;
            }

            for (int last = 0; last < M; ++last) {
                if ((mask & (1 << last)) == 0) {
                    continue;
                }

                for (int used = 0; used <= 1; ++used) {
                    long long current = dp[mask][last][used];

                    if (current == INF) {
                        continue;
                    }

                    for (int next = 0; next < M; ++next) {
                        if (mask & (1 << next)) {
                            continue;
                        }

                        int nextMask = mask | (1 << next);

                        dp[nextMask][next][used] = min(
                            dp[nextMask][next][used],
                            current + cost[last][next]
                        );

                        if (used == 0 && (mask & (1 << A))) {
                            dp[nextMask][next][1] = min(
                                dp[nextMask][next][1],
                                current + cost[last][next] / 2
                            );
                        }
                    }
                }
            }
        }

        int fullMask = MASKS - 1;

        cout << min(
            dp[fullMask][M - 1][0],
            dp[fullMask][M - 1][1]
        ) << '\n';
    }

    return 0;
}