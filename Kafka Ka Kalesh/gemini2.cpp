#include <iostream>

using namespace std;

void solve() {
    long long P, C, S, L;
    if (!(cin >> P >> C >> S >> L)) return;

    long long produced = L / P;
    long long consumed = 0;

    if (S <= L) {
        long long attempts = 1 + (L - S) / C;
        long long failed_attempts = (S < P) ? 1 : 0;
        consumed = attempts - failed_attempts;
    }

    long long lag = produced - consumed;
    cout << lag << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    if (cin >> t) {
        while (t--) {
            solve();
        }
    }
    return 0;
}