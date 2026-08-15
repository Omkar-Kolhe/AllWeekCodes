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
        int n;
        long long r;
        cin >> n >> r;

        long long capS, capW;
        cin >> capS >> capW;

        vector<int> gain;
        vector<int> price;

        long long current = r;

        for (int i = 0; i < n; ++i)
        {
            int s, w, c;
            cin >> s >> w >> c;

            int extra = max(0, s - w);
            current += extra;

            gain.push_back(extra);
            price.push_back(c);
        }

        long long allowed = capS + capW;

        if (current <= allowed)
        {
            cout << 0 << '\n';
            continue;
        }

        int required = (int)(current - allowed);
        const int BIG = 1000000000;

        vector<int> best(required + 1, BIG);
        best[0] = 0;

        for (int i = 0; i < n; ++i)
        {
            int amount = gain[i];
            int cost = price[i];

            if (amount == 0)
                continue;

            for (int j = required; j >= 0; --j)
            {
                if (best[j] == BIG)
                    continue;

                int covered = min(required, j + amount);
                best[covered] = min(best[covered], best[j] + cost);
            }
        }

        if (best[required] == BIG)
            cout << -1 << '\n';
        else
            cout << best[required] << '\n';
    }

    return 0;
}