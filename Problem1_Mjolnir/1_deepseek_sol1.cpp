#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--)
    {
        int N, R;
        cin >> N >> R;

        int Scap, Wcap;
        cin >> Scap >> Wcap;

        vector<int> S(N), W(N), C(N);
        long long totalImpact = 0;
        int maxCost = 0;

        for (int i = 0; i < N; i++)
        {
            cin >> S[i] >> W[i] >> C[i];
            int impact = max(0, S[i] - W[i]);
            totalImpact += impact;
            maxCost += C[i];
        }

        long long requiredRemove = R + totalImpact - (Scap + Wcap);

        if (requiredRemove <= 0)
        {
            cout << 0 << "\n";
            continue;
        }

        // If even removing all impact isn't enough
        if (requiredRemove > totalImpact)
        {
            cout << -1 << "\n";
            continue;
        }

        // DP: dp[j] = maximum impact removed with cost exactly j
        vector<long long> dp(maxCost + 1, -1);
        dp[0] = 0;

        for (int i = 0; i < N; i++)
        {
            int impact = max(0, S[i] - W[i]);
            int cost = C[i];
            if (impact == 0)
                continue; // No need to convince

            for (int j = maxCost; j >= cost; j--)
            {
                if (dp[j - cost] != -1)
                {
                    dp[j] = max(dp[j], dp[j - cost] + impact);
                }
            }
        }

        int ans = -1;
        for (int j = 0; j <= maxCost; j++)
        {
            if (dp[j] >= requiredRemove)
            {
                ans = j;
                break;
            }
        }

        cout << ans << "\n";
    }

    return 0;
}