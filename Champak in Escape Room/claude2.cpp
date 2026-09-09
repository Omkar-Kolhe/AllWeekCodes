#include <bits/stdc++.h>
using namespace std;

int main(){
    int T;
    scanf("%d", &T);
    while(T--){
        int N, M, K;
        scanf("%d %d %d", &N, &M, &K);
        vector<string> grid(N+1);
        for(int i=1;i<=N;i++){
            char buf[1005];
            scanf("%s", buf);
            grid[i] = "#" + string(buf);
        }
        char S[100005];
        scanf("%s", S);

        int r = 1, c = 1;
        for(int i=0;i<K;i++){
            int nr = r, nc = c;
            if(S[i]=='U') nr--;
            else if(S[i]=='D') nr++;
            else if(S[i]=='L') nc--;
            else if(S[i]=='R') nc++;

            if(nr>=1 && nr<=N && nc>=1 && nc<=M && grid[nr][nc]=='.'){
                r = nr; c = nc;
            }
        }

        printf("%d %d\n", r, c);
    }
    return 0;
}