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

        vector<long long> prefix(N + 1, 0);

        for (int i = 1; i <= N; i++)
        {
            char C;
            long long X;
            cin >> C >> X;

            if (C == 'L')
            {
                prefix[i] = prefix[i - 1] + X;
            }
            else
            {
                prefix[i] = prefix[i - 1] - X;
            }
        }

        unordered_map<long long, int> firstPos;
        firstPos.reserve(N * 2);
        firstPos[0] = 0;

        int bestLen = 0;
        int bestStart = 1;

        for (int i = 1; i <= N; i++)
        {
            long long currSum = prefix[i];

            auto it = firstPos.find(currSum);
            if (it != firstPos.end())
            {
                int start = it->second + 1;
                int len = i - it->second;

                if (len > bestLen || (len == bestLen && start < bestStart))
                {
                    bestLen = len;
                    bestStart = start;
                }
            }
            else
            {
                firstPos[currSum] = i;
            }
        }

        if (bestLen == 0)
        {
            cout << "0 0 0\n";
        }
        else
        {
            cout << bestLen << " " << bestStart << " " << bestStart + bestLen - 1 << "\n";
        }
    }

    return 0;
}