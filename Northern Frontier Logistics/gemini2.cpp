#include <bits/stdc++.h>
using namespace std;

int a[50005];

int main() {
    cin.tie(0)->sync_with_stdio(0);
    int t;
    if (!(cin >> t)) return 0;
    while (t--) {
        int n, d;
        cin >> n >> d;
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            cin >> a[i];
            l = max(l, a[i]);
            r += a[i];
        }
        int res = r;
        while (l <= r) {
            int mid = (l + r) / 2;
            int days = 1, cur = 0;
            for (int i = 0; i < n; i++) {
                if (cur + a[i] > mid) {
                    days++;
                    cur = 0;
                }
                cur += a[i];
            }
            if (days <= d) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        cout << res << '\n';
    }
    return 0;
}   