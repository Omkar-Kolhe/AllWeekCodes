#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    const long long INF = (1LL << 60);

    while (T--) {
        int M, A;
        cin >> M >> A;
        --A;

        vector<vector<long long>> c(M, vector<long long>(M));
        for (auto &row : c)
            for (auto &x : row)
                cin >> x;

        int total = 1 << M;

        // dp[mask][last][used]
        // used = 0: bag is still available
        // used = 1: bag has already been used
        vector<array<long long, 2>> dp((long long)total * M * 2, {INF, INF});

        auto at = [&](int mask, int last, int used) -> long long& {
            return dp[((long long)mask * M + last) * 2 + used];
        };

        at(1, 0, 0) = 0;

        for (int mask = 1; mask < total; ++mask) {
            if (!(mask & 1)) continue;

            for (int last = 0; last < M; ++last) {
                if (!(mask & (1 << last))) continue;

                for (int used = 0; used <= 1; ++used) {
                    long long cur = at(mask, last, used);
                    if (cur == INF) continue;

                    for (int nxt = 0; nxt < M; ++nxt) {
                        if (mask & (1 << nxt)) continue;

                        long long normal = cur + c[last][nxt];
                        long long &normalState = at(mask | (1 << nxt), nxt, used);

                        if (normal < normalState)
                            normalState = normal;

                        // The bag becomes available only after reaching A.
                        if (used == 0 && (mask & (1 << A))) {
                            long long discounted = cur + c[last][nxt] / 2;
                            long long &bagState =
                                at(mask | (1 << nxt), nxt, 1);

                            if (discounted < bagState)
                                bagState = discounted;
                        }
                    }
                }
            }
        }

        int full = total - 1;
        cout << min(at(full, M - 1, 0), at(full, M - 1, 1)) << '\n';
    }

    return 0;
}