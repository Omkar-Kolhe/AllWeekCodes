#include <bits/stdc++.h>
using namespace std;

int main() {
    cin.tie(NULL)->sync_with_stdio(false);
    
    int tc;
    if (!(cin >> tc)) return 0;
    
    while (tc--) {
        long long p, c, s, l;
        cin >> p >> c >> s >> l;
        
        long long total_produced = l / p;
        long long total_consumed = 0;
        
        if (s <= l) {
            long long tries = (l - s) / c + 1;
            // First read is missed only if consumer showed up before 1st msg
            if (s < p) {
                tries--;
            }
            total_consumed = tries;
        }
        
        cout << total_produced - total_consumed << "\n";
    }
    
    return 0;
}