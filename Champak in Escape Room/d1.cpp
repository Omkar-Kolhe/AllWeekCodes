#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int T;
    cin >> T;
    
    while (T--) {
        int N, M, K;
        cin >> N >> M >> K;
        
        vector<string> grid(N);
        for (int i = 0; i < N; i++) {
            cin >> grid[i];
        }
        
        string S;
        cin >> S;
        
        int r = 0, c = 0; // 0-indexed
        
        for (char ch : S) {
            int nr = r, nc = c;
            
            if (ch == 'U') nr--;
            else if (ch == 'D') nr++;
            else if (ch == 'L') nc--;
            else if (ch == 'R') nc++;
            
            // Check if valid move
            if (nr >= 0 && nr < N && nc >= 0 && nc < M && grid[nr][nc] == '.') {
                r = nr;
                c = nc;
            }
        }
        
        cout << r + 1 << " " << c + 1 << "\n";
    }
    
    return 0;
}