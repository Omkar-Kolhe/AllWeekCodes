#include <bits/stdc++.h>
using namespace std;

using ll = long long;

ll largestPrimeFactor(ll x) {
    ll answer = -1;

    if (x % 2 == 0) {
        answer = 2;
        while (x % 2 == 0) x /= 2;
    }

    for (ll p = 3; p * p <= x; p += 2) {
        if (x % p == 0) {
            answer = p;
            while (x % p == 0) x /= p;
        }
    }

    if (x > 1) answer = x;
    return answer;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;

    while (t--) {
        int n;
        cin >> n;

        ll common = 0;
        for (int i = 0; i < n; ++i) {
            ll x;
            cin >> x;
            common = gcd(common, x);
        }

        cout << largestPrimeFactor(common) << '\n';
    }

    return 0;
}
