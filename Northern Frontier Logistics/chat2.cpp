#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int n, d;
        cin >> n >> d;

        vector<long long> w(n);

        long long lo = 0;
        long long hi = 0;

        for (int i = 0; i < n; ++i) {
            cin >> w[i];
            lo = max(lo, w[i]);
            hi += w[i];
        }

        while (lo <= hi) {
            long long cap = lo + (hi - lo) / 2;

            int usedDays = 1;
            long long load = 0;

            for (int i = 0; i < n; ++i) {
                if (load + w[i] <= cap) {
                    load += w[i];
                } else {
                    ++usedDays;
                    load = w[i];
                }
            }

            if (usedDays <= d) {
                hi = cap - 1;
            } else {
                lo = cap + 1;
            }
        }

        cout << lo << '\n';
    }

    return 0;
}