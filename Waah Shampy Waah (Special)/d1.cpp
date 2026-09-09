#include <bits/stdc++.h>
using namespace std;

void solve() {
    int N;
    long long M;
    cin >> N >> M;
    
    long long start = M / 2 + 1;
    long long count = M - start + 1;
    
    if (count < N) {
        cout << -1 << "\n";
        return;
    }
    
    for (int i = 0; i < N; i++) {
        cout << (start + i) << (i == N - 1 ? "" : " ");
    }
    cout << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int T;
    cin >> T;
    
    while (T--) {
        solve();
    }
    
    return 0;
}