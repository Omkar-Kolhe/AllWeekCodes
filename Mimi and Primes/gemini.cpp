#include <iostream>
#include <numeric>
#include <algorithm>
#include <vector>

using namespace std;

long long gcd(long long a, long long b) {
    while (b) {
        a %= b;
        swap(a, b);
    }
    return a;
}

void solve() {
    int n;
    cin >> n;
    long long g = 0;
    for (int i = 0; i < n; ++i) {
        long long a;
        cin >> a;
        g = gcd(g, a);
    }

    if (g == 1) {
        cout << -1 << "\n";
        return;
    }

    long long max_prime = -1;
    // Find the largest prime factor of g
    for (long long i = 2; i * i <= g; ++i) {
        if (g % i == 0) {
            max_prime = i;
            while (g % i == 0) {
                g /= i;
            }
        }
    }
    if (g > 1) {
        max_prime = max(max_prime, g);
    }

    cout << max_prime << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
