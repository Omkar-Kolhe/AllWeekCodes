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

        vector<long long> e(n);
        for (auto &x : e) cin >> x;

        int s, d;
        cin >> s >> d;

        if (s == d) {
            cout << 0 << '\n';
            continue;
        }

        long long a = 0;
        long long b = e[s + 1];

        if (d == s + 1) {
            cout << b << '\n';
            continue;
        }

        for (int i = s + 2; i <= d; i++) {
            long long cur = e[i] + min(a, b);
            a = b;
            b = cur;
        }

        cout << b << '\n';
    }
}
