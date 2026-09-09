#include <bits/stdc++.h>
using namespace std;

int main(){
    int T;
    scanf("%d", &T);
    while(T--){
        int N, M;
        scanf("%d %d", &N, &M);
        vector<string> grid(N);
        for(int i=0;i<N;i++){
            char buf[1005];
            scanf("%s", buf);
            grid[i] = buf;
        }

        vector<vector<int>> dist(N, vector<int>(M, -1));
        queue<pair<int,int>> q;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(grid[i][j]=='B'){
                    dist[i][j] = 0;
                    q.push({i,j});
                }
            }
        }

        int dr[4] = {-1,1,0,0};
        int dc[4] = {0,0,-1,1};

        while(!q.empty()){
            auto [r,c] = q.front(); q.pop();
            for(int d=0;d<4;d++){
                int nr = r+dr[d], nc = c+dc[d];
                if(nr>=0 && nr<N && nc>=0 && nc<M && grid[nr][nc]!='#' && dist[nr][nc]==-1){
                    dist[nr][nc] = dist[r][c] + 1;
                    q.push({nr,nc});
                }
            }
        }

        int ans = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(grid[i][j]=='S'){
                    ans = max(ans, dist[i][j]);
                }
            }
        }

        printf("%d\n", ans);
    }
    return 0;
}