#include <iostream>
#include <vector>
#include <string>

using namespace std;

void solve() {
    int n, m, k;
    cin >> n >> m >> k;
    
    vector<string> grid(n);
    for (int i = 0; i < n; i++) {
        cin >> grid[i];
    }
    
    string s;
    cin >> s;
    
    // 0-based indexing for the grid
    int r = 0, c = 0;
    
    for (char move : s) {
        int nr = r, nc = c;
        
        if (move == 'U') nr--;
        else if (move == 'D') nr++;
        else if (move == 'L') nc--;
        else if (move == 'R') nc++;
        
        // Boundary and wall check
        if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] != '#') {
            r = nr;
            c = nc;
        }
    }
    
    // Output 1-based indexing
    cout << r + 1 << " " << c + 1 << "\n";
}

int main() {
    // Standard CP template for blazing fast I/O
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