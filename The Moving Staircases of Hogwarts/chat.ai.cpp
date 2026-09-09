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

        vector<long long> E(N);
        for (int i = 0; i < N; i++) cin >> E[i];

        int S, D;
        cin >> S >> D;

        vector<long long> dp(D - S + 1, 0);

        for (int i = S + 1; i <= D; i++) {
            if (i == S + 1) {
                dp[i - S] = E[i];
            } else {
                dp[i - S] = E[i] + min(dp[i - S - 1],
                                       i - S >= 2 ? dp[i - S - 2] : 0LL);
            }
        }

        cout << (S == D ? 0 : dp[D - S]) << '\n';
    }
}
