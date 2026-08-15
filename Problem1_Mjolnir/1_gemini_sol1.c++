// 1_gemini_sol1.cpp
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

void solve()
{
    int n;
    long long r;
    cin >> n >> r;

    long long s_cap, w_cap;
    cin >> s_cap >> w_cap;

    long long max_capacity = s_cap + w_cap - r;

    vector<long long> impacts(n);
    vector<long long> costs(n);
    long long total_cost = 0;

    for (int i = 0; i < n; ++i)
    {
        long long s, w, c;
        cin >> s >> w >> c;
        impacts[i] = max(0LL, s - w);
        costs[i] = c;
        total_cost += c;
    }

    if (max_capacity < 0)
    {
        cout << -1 << "\n";
        return;
    }

    vector<long long> dp(max_capacity + 1, 0);

    for (int i = 0; i < n; ++i)
    {
        long long weight = impacts[i];
        long long value = costs[i];
        for (long long j = max_capacity; j >= weight; --j)
        {
            if (dp[j - weight] + value > dp[j])
            {
                dp[j] = dp[j - weight] + value;
            }
        }
    }

    cout << total_cost - dp[max_capacity] << "\n";
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    if (cin >> t)
    {
        while (t--)
        {
            solve();
        }
    }
    return 0;
}