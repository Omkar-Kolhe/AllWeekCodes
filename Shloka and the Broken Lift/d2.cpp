#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
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
        
        for (int j = 3; j <= N; j++) {
            int h = H[j];
            for (int d = 1; d * d <= h; d++) {
                if (h % d == 0) {
                    int dist1 = d;
                    int dist2 = h / d;
                    
                    if (dist1 >= 2 && j - dist1 >= 1 && reachable[j - dist1]) {
                        reachable[j] = true;
                        break;
                    }
                    if (dist2 >= 2 && dist2 != dist1 && j - dist2 >= 1 && reachable[j - dist2]) {
                        reachable[j] = true;
                        break;
                    }
                }
            }
        }
        
        cout << (reachable[N] ? "YES" : "NO") << '\n';
    }
    
    return 0;
}