#include <bits/stdc++.h>
using namespace std;

struct Avenger
{
    int impact;
    int cost;
};

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

        vector<Avenger> avengers(N);
        long long totalImpact = 0;
        int totalCost = 0;

        for (int i = 0; i < N; i++)
        {
            int S, W, C;
            cin >> S >> W >> C;
            int impact = max(0, S - W);
            avengers[i] = {impact, C};
            totalImpact += impact;
            totalCost += C;
        }

        long long threshold = R + totalImpact - (Scap + Wcap);

        if (threshold <= 0)
        {
            cout << 0 << "\n";
            continue;
        }

        if (threshold > totalImpact)
        {
            cout << -1 << "\n";
            continue;
        }

        // DP with unordered_map for sparse states
        unordered_map<int, long long> dp;
        dp[0] = 0;

        for (auto &av : avengers)
        {
            if (av.impact == 0)
                continue;
            vector<pair<int, long long>> additions;

            for (auto &[cost, impact] : dp)
            {
                int newCost = cost + av.cost;
                long long newImpact = impact + av.impact;
                auto it = dp.find(newCost);
                if (it == dp.end())
                {
                    additions.push_back({newCost, newImpact});
                }
                else
                {
                    it->second = max(it->second, newImpact);
                }
            }

            for (auto &p : additions)
            {
                dp[p.first] = max(dp[p.first], p.second);
            }
        }

        int ans = INT_MAX;
        for (auto &[cost, impact] : dp)
        {
            if (impact >= threshold)
            {
                ans = min(ans, cost);
            }
        }

        cout << (ans == INT_MAX ? -1 : ans) << "\n";
    }

    return 0;
}