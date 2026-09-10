#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;
    while (T--)
    {
        int N;
        cin >> N;
        unordered_map<long long, int> maxExp;
        maxExp.reserve(N * 2);
        for (int i = 0; i < N; i++)
        {
            long long a;
            cin >> a;
            int k = 0;
            while (a % 2 == 0)
            {
                a /= 2;
                k++;
            }
            auto it = maxExp.find(a);
            if (it == maxExp.end() || it->second < k)
            {
                maxExp[a] = k;
            }
        }
        long long ans = 0;
        for (auto &p : maxExp)
            ans += p.second;
        cout << ans << "\n";
    }
    return 0;
}