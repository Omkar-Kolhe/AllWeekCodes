#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int N;
        cin >> N;

        vector<int> H(N + 1);
        for (int i = 1; i <= N; i++) cin >> H[i];

        vector<bool> dp(N + 1, false);
        dp[1] = true;

        for (int j = 3; j <= N; j++) {
            for (int d = 1; d * d <= H[j]; d++) {
                if (H[j] % d) continue;

                int d1 = d;
                int d2 = H[j] / d;

                if (d1 >= 2 && d1 < j && dp[j - d1])
                    dp[j] = true;

                if (d2 != d1 && d2 >= 2 && d2 < j && dp[j - d2])
                    dp[j] = true;

                if (dp[j]) break;
            }
        }

        cout << (dp[N] ? "YES\n" : "NO\n");
    }

    return 0;
}