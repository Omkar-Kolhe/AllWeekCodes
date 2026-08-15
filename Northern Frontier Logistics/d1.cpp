#include <bits/stdc++.h>
using namespace std;
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int T; cin >> T;
    while (T--) {
        int N, D; cin >> N >> D;
        vector<int> W(N);
        int lo = 0, hi = 0;
        for (int i = 0; i < N; ++i) {
            cin >> W[i];
            lo = max(lo, W[i]);
            hi += W[i];
        }
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int cnt = 1, cur = 0;
            for (int w : W) {
                if (cur + w > mid) { ++cnt; cur = 0; }
                cur += w;
            }
            (cnt <= D) ? hi = mid : lo = mid + 1;
        }
        cout << lo << '\n';
    }
}