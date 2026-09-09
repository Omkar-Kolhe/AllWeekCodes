#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    string s;
    cin >> s;
    int k;
    cin >> k;

    int left = 0, w_count = 0, best = 0;
    for (int right = 0; right < n; ++right) {
        if (s[right] == 'W') {
            w_count++;
        }
        while (w_count > k) {
            if (s[left] == 'W') {
                w_count--;
            }
            left++;
        }
        best = max(best, right - left + 1);
    }
    cout << best << "\n";
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}