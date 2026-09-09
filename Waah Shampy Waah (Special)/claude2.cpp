#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int T;
    cin >> T;
    
    while (T--) {
        long long N, M;
        cin >> N >> M;
        
        long long maxAntichainSize = (M + 1) / 2;
        
        if (N > maxAntichainSize) {
            cout << -1 << "\n";
        } else {
            long long start = M - N + 1;
            ostringstream oss;
            for (long long i = start; i <= M; i++) {
                oss << i;
                if (i != M) oss << ' ';
            }
            cout << oss.str() << "\n";
        }
    }
    
    return 0;
}