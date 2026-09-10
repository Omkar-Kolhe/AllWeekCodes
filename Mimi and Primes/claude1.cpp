#include <bits/stdc++.h>
using namespace std;

int main() {
    int t;
    cin >> t;

    while (t--) {
        int n;
        cin >> n;

        vector<long long> a(n);
        for (auto &x : a) cin >> x;

        set<long long> common;

        long long x = a[0];
        for (long long p = 2; p * p <= x; ++p) {
            if (x % p == 0) {
                common.insert(p);
                while (x % p == 0) x /= p;
            }
        }
        if (x > 1) common.insert(x);

        for (int i = 1; i < n && !common.empty(); ++i) {
            set<long long> current;
            x = a[i];

            for (long long p = 2; p * p <= x; ++p) {
                if (x % p == 0) {
                    current.insert(p);
                    while (x % p == 0) x /= p;
                }
            }
            if (x > 1) current.insert(x);

            for (auto it = common.begin(); it != common.end();) {
                if (!current.count(*it))
                    it = common.erase(it);
                else
                    ++it;
            }
        }

        if (common.empty())
            cout << -1 << '\n';
        else
            cout << *common.rbegin() << '\n';
    }

    return 0;
}
