#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int T;
    cin >> T;
    
    while (T--) {
        int N, M;
        cin >> N >> M;
        
        vector<string> grid(N);
        queue<pair<int, int>> q;
        vector<vector<int>> dist(N, vector<int>(M, -1));
        
        // Find all rescue bases and push them into queue
        for (int i = 0; i < N; i++) {
            cin >> grid[i];
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 'B') {
                    q.push({i, j});
                    dist[i][j] = 0;
                }
            }
        }
        
        // Directions: up, down, left, right
        int dr[] = {-1, 1, 0, 0};
        int dc[] = {0, 0, -1, 1};
        
        // Multi-source BFS
        int ans = 0;
        
        while (!q.empty()) {
            auto [r, c] = q.front();
            q.pop();
            
            // If we reach a ship, update answer
            if (grid[r][c] == 'S') {
                ans = max(ans, dist[r][c]);
            }
            
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && 
                    grid[nr][nc] != '#' && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    q.push({nr, nc});
                }
            }
        }
        
        cout << ans << "\n";
    }
    
    return 0;
}