#include <bits/stdc++.h>
using namespace std;

int main()
{
    int T;
    scanf("%d", &T);
    while (T--)
    {
        int N;
        scanf("%d", &N);

        vector<long long> prefix(N + 1, 0);
        for (int i = 1; i <= N; i++)
        {
            char c;
            long long x;
            scanf(" %c %lld", &c, &x);
            long long delta = (c == 'L') ? x : -x;
            prefix[i] = prefix[i - 1] + delta;
        }

        unordered_map<long long, int> firstOcc;
        firstOcc.reserve((size_t)(N + 1) * 2);
        firstOcc[0] = 0;

        long long bestLen = 0;
        int bestS = 0, bestE = 0;

        for (int i = 1; i <= N; i++)
        {
            auto it = firstOcc.find(prefix[i]);
            if (it == firstOcc.end())
            {
                firstOcc[prefix[i]] = i;
            }
            else
            {
                long long len = i - it->second;
                if (len > bestLen)
                {
                    bestLen = len;
                    bestS = it->second + 1;
                    bestE = i;
                }
            }
        }

        if (bestLen == 0)
        {
            printf("0 0 0\n");
        }
        else
        {
            printf("%lld %d %d\n", bestLen, bestS, bestE);
        }
    }
    return 0;
}