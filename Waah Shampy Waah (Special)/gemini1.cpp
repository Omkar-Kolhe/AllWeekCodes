#include <iostream>

using namespace std;

void solve() {
    long long n, m;
    cin >> n >> m;

    long long max_possible = (m + 1) / 2;
    if (n > max_possible) {
        cout << -1 << "\n";
        return;
    }

    long long start = m - n + 1;
    for (int i = 0; i < n; i++) {
        cout << start + i << (i == n - 1 ? "" : " ");
    }
    cout << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while (t--) {
        solve();
    }

    return 0;
}