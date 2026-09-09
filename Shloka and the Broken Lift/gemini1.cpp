#include <iostream>
#include <vector>

using namespace std;

void solve() {
    int n;
    cin >> n;
    vector<int> h(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> h[i];
    }

    vector<bool> dp(n + 1, false);
    dp[1] = true;

    for (int j = 2; j <= n; j++) {
        // Find factors of h[j] up to sqrt(h[j])
        for (int d = 1; d * d <= h[j]; d++) {
            if (h[j] % d == 0) {
                // Check jump distance d
                if (d >= 2 && j - d >= 1 && dp[j - d]) {
                    dp[j] = true;
                    break;
                }
                // Check the paired factor (h[j] / d)
                int paired_d = h[j] / d;
                if (paired_d >= 2 && j - paired_d >= 1 && dp[j - paired_d]) {
                    dp[j] = true;
                    break;
                }
            }
        }
    }

    if (dp[n]) cout << "YES\n";
    else cout << "NO\n";
}

int main() {
    // Fast I/O
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int t;
    if (cin >> t) {
        while (t--) {
            solve();
        }
    }
    return 0;
}