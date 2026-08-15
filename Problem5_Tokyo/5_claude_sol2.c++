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
            prefix[i] = prefix[i - 1] + (c == 'L' ? x : -x);
        }

        // Pair each prefix value with its index, then sort so that equal
        // prefix values are grouped together, ordered by ascending index.
        vector<pair<long long, int>> arr(N + 1);
        for (int i = 0; i <= N; i++)
        {
            arr[i] = make_pair(prefix[i], i);
        }
        sort(arr.begin(), arr.end());

        long long bestLen = 0;
        int bestS = 0, bestE = 0;

        int i = 0;
        while (i <= N)
        {
            int j = i;
            while (j <= N && arr[j].first == arr[i].first)
                j++;
            // group spans [i, j-1]; since sorted by (value, index), the
            // smallest and largest index in the group are the endpoints.
            int minIdx = arr[i].second;
            int maxIdx = arr[j - 1].second;
            long long len = (long long)maxIdx - minIdx;
            if (len > 0)
            {
                if (len > bestLen || (len == bestLen && (minIdx + 1) < bestS))
                {
                    bestLen = len;
                    bestS = minIdx + 1;
                    bestE = maxIdx;
                }
            }
            i = j;
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