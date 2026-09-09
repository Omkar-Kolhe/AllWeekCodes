#include <iostream>

using namespace std;

void solve() {
    long long a, b, c, d;
    cin >> a >> b >> c >> d;

    if (d < b) {
        cout << -1 << "\n";
        return;
    }

    long long diag_moves = d - b;
    long long target_x = a + diag_moves;

    if (c > target_x) {
        cout << -1 << "\n";
    } else {
        long long left_moves = target_x - c;
        cout << diag_moves + left_moves << "\n";
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    if (cin >> t) {
        while (t--) {
            solve();
        }
    }
    return 0;
}