#include <bits/stdc++.h>
using namespace std;

using ll = long long;

struct Line {
    ll m, b;

    ll get(ll x) const {
        return m * x + b;
    }
};

// Returns true when the middle line can never give the minimum.
bool useless(const Line& a, const Line& b, const Line& c) {
    __int128 left = (__int128)(b.b - a.b) * (b.m - c.m);
    __int128 right = (__int128)(c.b - b.b) * (a.m - b.m);
    return left >= right;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    ll C;
    cin >> n >> C;

    vector<ll> h(n);
    for (ll& x : h) cin >> x;

    vector<ll> dp(n);
    deque<Line> hull;

    // For milestone 1:
    // dp[0] = 0, so its line is y = -2*h[0]*x + h[0]^2.
    hull.push_back({-2 * h[0], h[0] * h[0]});

    for (int j = 1; j < n; ++j) {
        ll x = h[j];

        while (hull.size() >= 2 && hull[0].get(x) >= hull[1].get(x)) {
            hull.pop_front();
        }

        dp[j] = x * x + C + hull.front().get(x);

        Line next = {-2 * x, dp[j] + x * x};

        while (hull.size() >= 2 &&
               useless(hull[hull.size() - 2], hull.back(), next)) {
            hull.pop_back();
        }

        hull.push_back(next);
    }

    cout << dp[n - 1] << '\n';
    return 0;
}
