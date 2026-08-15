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
        int N;
        long long R, Scap, Wcap;
        cin >> N >> R;
        cin >> Scap >> Wcap;

        vector<pair<int, int>> avengers;
        long long resistance = R;

        for (int i = 0; i < N; ++i)
        {
            int S, W, C;
            cin >> S >> W >> C;

            int impact = max(0, S - W);
            resistance += impact;
            avengers.push_back({impact, C});
        }

        long long power = Scap + Wcap;

        if (resistance <= power)
        {
            cout << 0 << '\n';
            continue;
        }

        int need = static_cast<int>(resistance - power);
        const int INF = 1e9;

        vector<int> dp(need + 1, INF);
        dp[0] = 0;

        for (auto [damage, cost] : avengers)
        {
            for (int removed = need; removed >= 0; --removed)
            {
                if (dp[removed] == INF)
                    continue;

                int next = min(need, removed + damage);
                dp[next] = min(dp[next], dp[removed] + cost);
            }
        }

        cout << (dp[need] == INF ? -1 : dp[need]) << '\n';
    }

    return 0;
}