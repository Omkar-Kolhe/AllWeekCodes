#include <bits/stdc++.h>
using namespace std;

int main(){
    int t;
    scanf("%d", &t);
    while(t--){
        int n, m, k;
        scanf("%d %d %d", &n, &m, &k);
        vector<string> grid(n+1);
        for(int i=1;i<=n;i++){
            char buf[1005];
            scanf("%s", buf);
            grid[i] = "#" + string(buf);
        }
        char sbuf[100005];
        scanf("%s", sbuf);

        int r = 1, c = 1;
        for(int i=0; i<k; i++){
            int nr = r, nc = c;
            if(sbuf[i]=='U') nr--;
            else if(sbuf[i]=='D') nr++;
            else if(sbuf[i]=='L') nc--;
            else nc++;

            if(nr>=1 && nr<=n && nc>=1 && nc<=m && grid[nr][nc]=='.'){
                r = nr; c = nc;
            }
        }
        printf("%d %d\n", r, c);
    }
}