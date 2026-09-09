#include <iostream>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        long long N, M;
        cin >> N >> M;

        long long first = M / 2 + 1;
        long long available = M - first + 1;

        if (available < N) {
            cout << -1 << '\n';
            continue;
        }

        for (long long i = 0; i < N; ++i) {
            if (i > 0) cout << ' ';
            cout << first + i;
        }
        cout << '\n';
    }

    return 0;
}