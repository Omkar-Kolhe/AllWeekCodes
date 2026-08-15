#include <bits/stdc++.h>
using namespace std;

static const long long INF_COST = LLONG_MAX / 4;

int main()
{
    int T;
    scanf("%d", &T);

    while (T--)
    {
        int N;
        long long R;
        scanf("%d %lld", &N, &R);
        long long Scap, Wcap;
        scanf("%lld %lld", &Scap, &Wcap);

        vector<long long> imp(N), cst(N);
        for (int i = 0; i < N; i++)
        {
            long long S, W, C;
            scanf("%lld %lld %lld", &S, &W, &C);
            long long v = S - W;
            imp[i] = (v > 0) ? v : 0;
            cst[i] = C;
        }

        long long capTotal = Scap + Wcap;
        if (R > capTotal)
        {
            printf("-1\n");
            continue;
        }

        int allowed = (int)(capTotal - R);

        // dp[w] = minimum total convincing cost so that the impact of
        // avengers we choose NOT to convince stays within w.
        vector<long long> dp(allowed + 1, 0LL);

        for (int i = 0; i < N; i++)
        {
            long long wt = imp[i];
            long long c = cst[i];
            for (int w = allowed; w >= 0; w--)
            {
                long long convinceOption = dp[w] + c;
                long long keepOption = (w >= wt) ? dp[w - (int)wt] : INF_COST;
                dp[w] = min(convinceOption, keepOption);
            }
        }

        printf("%lld\n", dp[allowed]);
    }
    return 0;
}