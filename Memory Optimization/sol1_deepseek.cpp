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
        cin >> N;
        unordered_map<long long, int> maxExp;
        for (int i = 0; i < N; ++i)
        {
            long long x;
            cin >> x;
            int cnt = 0;
            while ((x & 1) == 0)
            {
                x >>= 1;
                ++cnt;
            }
            // x is now odd part
            auto it = maxExp.find(x);
            if (it == maxExp.end() || it->second < cnt)
            {
                maxExp[x] = cnt;
            }
        }
        long long ans = 0;
        for (auto &p : maxExp)
        {
            ans += p.second;
        }
        cout << ans << '\n';
    }
    return 0;
}