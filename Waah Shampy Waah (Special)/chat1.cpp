#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int N;
        long long M;
        cin >> N >> M;

        long long start = M / 2 + 1;

        if (M - start + 1 < N) {
            cout << -1 << '\n';
            continue;
        }

        for (int i = 0; i < N; ++i) {
            if (i) cout << ' ';
            cout << start + i;
        }
        cout << '\n';
    }

    return 0;
}