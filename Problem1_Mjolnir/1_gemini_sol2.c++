// 1_gemini_sol2.cpp
#include <bits/stdc++.h>
using namespace std;

struct Avenger
{
    long long strength;
    long long worthiness;
    long long time_cost;

    long long get_impact() const
    {
        return max(0LL, strength - worthiness);
    }
};

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t_cases;
    if (!(cin >> t_cases))
        return 0;

    while (t_cases--)
    {
        int n;
        long long initial_res;
        cin >> n >> initial_res;

        long long cap_s, cap_w;
        cin >> cap_s >> cap_w;

        long long allowed_impact = cap_s + cap_w - initial_res;

        vector<Avenger> avengers(n);
        long long sum_all_costs = 0;
        for (int i = 0; i < n; i++)
        {
            cin >> avengers[i].strength >> avengers[i].worthiness >> avengers[i].time_cost;
            sum_all_costs += avengers[i].time_cost;
        }

        if (allowed_impact < 0)
        {
            cout << -1 << "\n";
            continue;
        }

        vector<vector<long long>> dp(2, vector<long long>(allowed_impact + 1, 0));

        for (int i = 1; i <= n; i++)
        {
            int curr = i % 2;
            int prev = (i - 1) % 2;
            long long item_weight = avengers[i - 1].get_impact();
            long long item_value = avengers[i - 1].time_cost;

            for (long long w = 0; w <= allowed_impact; w++)
            {
                dp[curr][w] = dp[prev][w];
                if (w >= item_weight)
                {
                    dp[curr][w] = max(dp[curr][w], dp[prev][w - item_weight] + item_value);
                }
            }
        }

        long long max_kept_value = dp[n % 2][allowed_impact];
        cout << sum_all_costs - max_kept_value << "\n";
    }

    return 0;
}