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

        long long diagonal = d - b;
        long long x = a + diagonal;

        if (x < c) {
            cout << -1 << '\n';
        } else {
            cout << diagonal + (x - c) << '\n';
        }
    }

    return 0;
}