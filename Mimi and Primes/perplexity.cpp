#include <bits/stdc++.h>
using namespace std;
using ll = long long;

ll gcd(ll a, ll b) {
    while (b) {
        a %= b;
        swap(a, b);
    }
    return a;
}

ll largestPrimeFactor(ll n) {
    if (n <= 1) return -1;
    ll largest = -1;
    
    if (n % 2 == 0) {
        largest = 2;
        while (n % 2 == 0) n /= 2;
    }
    
    for (ll i = 3; i * i <= n; i += 2) {
        if (n % i == 0) {
            largest = i;
            while (n % i == 0) n /= i;
        }
    }
    
    if (n > 1) largest = n;
    
    return largest;
}

void solve() {
    int n;
    cin >> n;
    vector<ll> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];
    
    ll g = a[0];
    for (int i = 1; i < n; i++) {
        g = gcd(g, a[i]);
    }
    
    cout << largestPrimeFactor(g) << "\n";
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
