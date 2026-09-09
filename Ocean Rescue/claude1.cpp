#include <bits/stdc++.h>
using namespace std;

int main(){
    int t;
    scanf("%d", &t);
    while(t--){
        int n, m;
        scanf("%d %d", &n, &m);
        vector<string> g(n);
        for(int i=0;i<n;i++){
            char buf[1005];
            scanf("%s", buf);
            g[i] = buf;
        }

        vector<vector<int>> dist(n, vector<int>(m, -1));
        queue<pair<int,int>> q;

        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                if(g[i][j]=='B'){ dist[i][j]=0; q.push({i,j}); }

        int dx[4]={-1,1,0,0}, dy[4]={0,0,-1,1};
        int ans = 0;

        while(!q.empty()){
            auto [x,y] = q.front(); q.pop();
            for(int dir=0; dir<4; dir++){
                int nx=x+dx[dir], ny=y+dy[dir];
                if(nx>=0 && nx<n && ny>=0 && ny<m && dist[nx][ny]==-1 && g[nx][ny]!='#'){
                    dist[nx][ny] = dist[x][y]+1;
                    if(g[nx][ny]=='S') ans = max(ans, dist[nx][ny]);
                    q.push({nx,ny});
                }
            }
        }
        printf("%d\n", ans);
    }
}