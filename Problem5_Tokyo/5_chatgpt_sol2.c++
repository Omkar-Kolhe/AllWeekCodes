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
        cin >> n;

        vector<long long> pref(n + 1, 0);

        for (int i = 1; i <= n; ++i)
        {
            char c;
            long long x;
            cin >> c >> x;

            pref[i] = pref[i - 1] + (c == 'L' ? x : -x);
        }

        map<long long, int> first;

        int answerLength = 0;
        int answerStart = 0;
        int answerEnd = 0;

        for (int i = 0; i <= n; ++i)
        {
            if (!first.count(pref[i]))
            {
                first[pref[i]] = i;
                continue;
            }

            int previous = first[pref[i]];
            int len = i - previous;
            int start = previous + 1;

            if (len > answerLength ||
                (len == answerLength &&
                 (answerStart == 0 || start < answerStart)))
            {
                answerLength = len;
                answerStart = start;
                answerEnd = i;
            }
        }

        cout << answerLength << ' '
             << answerStart << ' '
             << answerEnd << '\n';
    }

    return 0;
}