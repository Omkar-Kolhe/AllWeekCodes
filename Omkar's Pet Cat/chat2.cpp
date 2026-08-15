#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;

    while (t--) {
        int n;
        cin >> n;

        vector<long long> a(n);

        for (int i = 0; i < n; i++) {
            cin >> a[i];
        }

        long long mn = *min_element(a.begin(), a.end());

        int cnt = 0;
        bool possible = false;

        for (long long x : a) {
            if (x == mn) {
                cnt++;
            }

            if (x % mn != 0) {
                possible = true;
            }
        }

        if (cnt == 1 || possible) {
            cout << "YES\n";
        } else {
            cout << "NO\n";
        }
    }

    return 0;
}