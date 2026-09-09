#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int T;
    cin >> T;
    
    while (T--) {
        int N;
        cin >> N;
        
        vector<int> H(N + 1);
        for (int i = 1; i <= N; i++) {
            cin >> H[i];
        }
        
        vector<bool> reachable(N + 1, false);
        reachable[1] = true;
        
        for (int i = 1; i <= N; i++) {
            if (!reachable[i]) continue;
            
            // Try all possible jump lengths that divide H[i]
            // Instead of iterating j, iterate divisors of H[i]
            for (int d = 1; d * d <= H[i]; d++) {
                if (H[i] % d == 0) {
                    // d is a divisor, so we can jump to i + d if valid
                    if (d >= 2) {
                        int j = i + d;
                        if (j <= N) {
                            reachable[j] = true;
                        }
                    }
                    
                    // H[i]/d is also a divisor
                    int other = H[i] / d;
                    if (other >= 2 && other != d) {
                        int j = i + other;
                        if (j <= N) {
                            reachable[j] = true;
                        }
                    }
                }
            }
        }
        
        cout << (reachable[N] ? "YES" : "NO") << '\n';
    }
    
    return 0;
}