#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int n;
        long long x;
        cin >> n >> x;

        vector<int> w(n);
        long long sum = 0;
        for (int &v : w) {
            cin >> v;
            sum += v;
        }

        if (sum == x) {
            cout << "NO\n";
            continue;
        }

        sort(w.begin(), w.end());

        long long pref = 0;
        for (int i = 0; i < n; ++i) {
            if (pref + w[i] == x && i + 1 < n)
                swap(w[i], w[i + 1]);
            pref += w[i];
        }

        cout << "YES\n";
        for (int i = 0; i < n; ++i)
            cout << w[i] << (i + 1 == n ? '\n' : ' ');
    }
}
