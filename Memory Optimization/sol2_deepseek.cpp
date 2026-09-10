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
        vector<pair<long long, int>> v;
        v.reserve(N);
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
            v.emplace_back(x, cnt);
        }
        sort(v.begin(), v.end());
        long long ans = 0;
        int i = 0;
        while (i < N)
        {
            long long odd = v[i].first;
            int mx = 0;
            while (i < N && v[i].first == odd)
            {
                mx = max(mx, v[i].second);
                ++i;
            }
            ans += mx;
        }
        cout << ans << '\n';
    }
    return 0;
}