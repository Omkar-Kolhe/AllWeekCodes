#include <bits/stdc++.h>
using namespace std;

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

        vector<long long> impact(N), cost(N);
        long long totalC = 0;
        for (int i = 0; i < N; i++)
        {
            long long S, W, C;
            scanf("%lld %lld %lld", &S, &W, &C);
            long long imp = S - W;
            if (imp < 0)
                imp = 0;
            impact[i] = imp;
            cost[i] = C;
            totalC += C;
        }

        long long cap = Scap + Wcap;
        if (R > cap)
        {
            printf("-1\n");
            continue;
        }

        long long allowedL = cap - R; // max impact we may still keep (not convince)
        int allowed = (int)allowedL;

        // dp[w] = maximum total cost of avengers we can KEEP (not convince)
        // such that their combined impact does not exceed w.
        vector<long long> dp(allowed + 1, 0);

        for (int i = 0; i < N; i++)
        {
            long long wt = impact[i];
            long long val = cost[i];
            if (wt > allowed)
                continue; // this avenger can never be kept alone, forced to convince
            int wti = (int)wt;
            for (int w = allowed; w >= wti; w--)
            {
                long long cand = dp[w - wti] + val;
                if (cand > dp[w])
                    dp[w] = cand;
            }
        }

        long long bestKept = dp[allowed];
        printf("%lld\n", totalC - bestKept);
    }
    return 0;
}