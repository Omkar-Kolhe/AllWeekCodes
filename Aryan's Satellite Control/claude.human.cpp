#include <bits/stdc++.h>
using namespace std;

struct Fenwick {
    int n;
    vector<int> bit;

    Fenwick(int n) : n(n), bit(n + 1) {}

    void add(int i, int v) {
        for (i++; i <= n; i += i & -i) bit[i] += v;
    }

    int sum(int i) {
        int res = 0;
        for (i++; i; i -= i & -i) res += bit[i];
        return res;
    }

    int kth(int k) {
        int pos = 0;
        int pw = 1;
        while ((pw << 1) <= n) pw <<= 1;

        for (; pw; pw >>= 1) {
            int nxt = pos + pw;
            if (nxt <= n && bit[nxt] < k) {
                pos = nxt;
                k -= bit[nxt];
            }
        }
        return pos;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int n, k;
        cin >> n >> k;

        vector<pair<long long, long long>> a(n);
        vector<long long> coords;

        for (auto &[l, r] : a) {
            cin >> l >> r;
            coords.push_back(r);
        }

        sort(a.begin(), a.end(), [](auto &x, auto &y) {
            return x.second < y.second;
        });

        coords.push_back(0);
        sort(coords.begin(), coords.end());
        coords.erase(unique(coords.begin(), coords.end()), coords.end());

        Fenwick fw(coords.size());
        fw.add(0, k);

        int ans = 0;

        for (auto [l, r] : a) {
            int idx = upper_bound(coords.begin(), coords.end(), l) - coords.begin() - 1;
            if (idx < 0) continue;

            int available = fw.sum(idx);
            if (available == 0) continue;

            int chosen = fw.kth(available);
            fw.add(chosen, -1);

            int ridx = lower_bound(coords.begin(), coords.end(), r) - coords.begin();
            fw.add(ridx, 1);
            ++ans;
        }

        cout << ans << '\n';
    }
}
