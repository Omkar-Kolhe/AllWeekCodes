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

        unordered_map<long long, int> firstSeen;
        firstSeen.reserve(2 * N + 1);
        firstSeen.max_load_factor(0.7);

        long long prefix = 0;
        int bestLen = 0;
        int bestStart = 0;
        int bestEnd = 0;

        firstSeen[0] = 0;

        for (int i = 1; i <= N; ++i)
        {
            char dir;
            long long angle;
            cin >> dir >> angle;

            if (dir == 'L')
                prefix += angle;
            else
                prefix -= angle;

            auto it = firstSeen.find(prefix);

            if (it == firstSeen.end())
            {
                firstSeen[prefix] = i;
            }
            else
            {
                int start = it->second + 1;
                int length = i - it->second;

                if (length > bestLen ||
                    (length == bestLen && start < bestStart))
                {
                    bestLen = length;
                    bestStart = start;
                    bestEnd = i;
                }
            }
        }

        cout << bestLen << ' ' << bestStart << ' ' << bestEnd << '\n';
    }

    return 0;
}