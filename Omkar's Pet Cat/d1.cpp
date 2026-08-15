#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int N;
        cin >> N;

        vector<long long> A(N);
        long long mn = LLONG_MAX;

        for (auto &x : A) {
            cin >> x;
            mn = min(mn, x);
        }

        bool hasNonDiv = false;
        int cntMin = 0;

        for (auto x : A) {
            if (x % mn != 0) hasNonDiv = true;
            if (x == mn) cntMin++;
        }

        if (hasNonDiv || cntMin == 1)
            cout << "YES\n";
        else
            cout << "NO\n";
    }

    return 0;
}