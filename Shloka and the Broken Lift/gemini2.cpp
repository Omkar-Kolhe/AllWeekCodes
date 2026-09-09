#include <iostream>
#include <vector>

using namespace std;

const int MAXH = 100000;
vector<int> divisors[MAXH + 1];

void precompute() {
    for (int i = 1; i <= MAXH; ++i) {
        for (int j = i; j <= MAXH; j += i) {
            divisors[j].push_back(i);
        }
    }
}

void solve() {
    int n;
    cin >> n;
    vector<int> h(n + 1);
    for (int i = 1; i <= n; ++i) {
        cin >> h[i];
    }
    vector<bool> dp(n + 1, false);
    dp[1] = true;
    for (int j = 2; j <= n; ++j) {
        for (int d : divisors[h[j]]) {
            if (d >= 2 && d < j) {
                if (dp[j - d]) {
                    dp[j] = true;
                    break;
                }
            } else if (d >= j) {
                break;
            }
        }
    }
    if (dp[n]) {
        cout << "YES\n";
    } else {
        cout << "NO\n";
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    precompute();
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}