#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int n;
        cin >> n;

        vector<long long> a(n);
        long long mn = LLONG_MAX;

        for (auto &x : a) {
            cin >> x;
            mn = min(mn, x);
        }

        int countMin = 0;
        bool hasNonMultiple = false;

        for (long long x : a) {
            if (x == mn) {
                countMin++;
            }

            if (x % mn != 0) {
                hasNonMultiple = true;
            }
        }

        if (countMin == 1 || hasNonMultiple) {
            cout << "YES\n";
        } else {
            cout << "NO\n";
        }
    }

    return 0;
}