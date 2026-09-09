#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    const int MAXH = 100000;

    // divisors[x] = valid jump lengths (>= 2) that divide x
    vector<vector<int>> divisors(MAXH + 1);

    for (int d = 2; d <= MAXH; d++) {
        for (int x = d; x <= MAXH; x += d) {
            divisors[x].push_back(d);
        }
    }

    int T;
    cin >> T;

    while (T--) {
        int N;
        cin >> N;

        vector<int> H(N);
        for (int &x : H) cin >> x;

        vector<char> dp(N, false);
        dp[0] = true;

        for (int j = 1; j < N; j++) {
            // A jump of d floors reaches j from j-d.
            // d must be at least 2 and must divide H[j].
            for (int d : divisors[H[j]]) {
                if (d > j) break;

                if (dp[j - d]) {
                    dp[j] = true;
                    break;
                }
            }
        }

        cout << (dp[N - 1] ? "YES\n" : "NO\n");
    }

    return 0;
}