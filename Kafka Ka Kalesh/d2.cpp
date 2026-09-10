#include <bits/stdc++.h>
using namespace std;

void solve() {
    long long P, C, S, L;
    cin >> P >> C >> S >> L;

    long long produced = L / P;

    long long attempts = 0;
    if (S <= L) {
        attempts = (L - S) / C + 1;
    }

    long long successful = attempts;
    if (S < P && S <= L) {
        successful = max(0LL, attempts - 1);
    }

    long long lag = produced - successful;
    cout << lag << "\n";
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int N;
    cin >> N;
    while (N--) {
        solve();
    }
    return 0;
}