#include <bits/stdc++.h>
using namespace std;

set<long long> getPrimeFactors(long long x) {
    set<long long> factors;

    while (x % 2 == 0) {
        factors.insert(2);
        x /= 2;
    }

    for (long long p = 3; p * p <= x; p += 2) {
        if (x % p == 0) {
            factors.insert(p);
            while (x % p == 0)
                x /= p;
        }
    }

    if (x > 1)
        factors.insert(x);

    return factors;
}

int main() {
    int n;
    cin >> n;

    vector<long long> a(n);
    for (auto &x : a)
        cin >> x;

    set<long long> common = getPrimeFactors(a[0]);

    for (int i = 1; i < n; i++) {
        set<long long> current = getPrimeFactors(a[i]);

        for (auto it = common.begin(); it != common.end();) {
            if (!current.count(*it))
                it = common.erase(it);
            else
                ++it;
        }

        if (common.empty())
            break;
    }

    if (common.empty())
        cout << "DNE\n";
    else
        cout << *common.rbegin() << '\n';

    return 0;
}
