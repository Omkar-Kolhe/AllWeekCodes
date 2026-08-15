#include <bits/stdc++.h>
using namespace std;

struct Result
{
    int len;
    int start;
    int end;
};

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

        vector<long long> pref(N + 1, 0);

        for (int i = 0; i < N; i++)
        {
            char C;
            long long X;
            cin >> C >> X;
            pref[i + 1] = pref[i] + (C == 'L' ? X : -X);
        }

        map<long long, int> earliest;
        earliest[0] = 0;

        Result ans = {0, 0, 0};

        for (int i = 1; i <= N; i++)
        {
            long long val = pref[i];

            if (earliest.count(val))
            {
                int s = earliest[val] + 1;
                int e = i;
                int len = e - s + 1;

                if (len > ans.len || (len == ans.len && s < ans.start))
                {
                    ans = {len, s, e};
                }
            }
            else
            {
                earliest[val] = i;
            }
        }

        if (ans.len == 0)
        {
            cout << "0 0 0\n";
        }
        else
        {
            cout << ans.len << " " << ans.start << " " << ans.end << "\n";
        }
    }

    return 0;
}