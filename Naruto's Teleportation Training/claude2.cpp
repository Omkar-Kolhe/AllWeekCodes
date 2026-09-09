#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;

    while (t--) {
        long long a, b, c, d;
        cin >> a >> b >> c >> d;

        if (d < b) {
            cout << -1 << '\n';
            continue;
        }

        long long diagonalMoves = d - b;
        long long xAfterDiagonal = a + diagonalMoves;

        if (xAfterDiagonal < c) {
            cout << -1 << '\n';
        } else {
            long long leftMoves = xAfterDiagonal - c;
            cout << diagonalMoves + leftMoves << '\n';
        }
    }

    return 0;
}