#include <iostream>
#include <vector>
#include <string>

using namespace std;

void solve() {
    int t;
    if (!(cin >> t)) return;
    while (t--) {
        int n, m, k;
        cin >> n >> m >> k;
        vector<string> grid(n);
        for (int i = 0; i < n; ++i) {
            cin >> grid[i];
        }
        string s;
        cin >> s;
        
        int r = 0, c = 0;
        for (char move : s) {
            int nr = r, nc = c;
            if (move == 'U') nr--;
            else if (move == 'D') nr++;
            else if (move == 'L') nc--;
            else if (move == 'R') nc++;
            
            if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == '.') {
                r = nr;
                c = nc;
            }
        }
        cout << r + 1 << " " << c + 1 << "\n";
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    solve();
    return 0;
}