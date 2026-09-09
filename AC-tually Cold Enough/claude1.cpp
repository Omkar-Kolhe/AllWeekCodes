#include <bits/stdc++.h>
using namespace std;

int main(){
    int n; long long d; int k;
    scanf("%d %lld %d", &n, &d, &k);

    vector<long long> h(n);
    for(int i=0;i<n;i++) scanf("%lld", &h[i]);

    vector<vector<int>> adj(n);
    for(int i=0;i<n-1;i++){
        int u,v; scanf("%d %d",&u,&v);
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    if(k > n){ printf("-1\n"); return 0; }

    vector<long long> M(n, -1);
    vector<int> depth(n, 0);
    vector<int> par(n, -1);

    M[0] = h[0];
    vector<int> order;
    order.reserve(n);
    queue<int> q;
    q.push(0);
    vector<char> vis(n,0);
    vis[0]=1;

    while(!q.empty()){
        int u = q.front(); q.pop();
        order.push_back(u);
        for(int v : adj[u]){
            if(!vis[v]){
                vis[v]=1;
                depth[v] = depth[u]+1;
                long long val = h[v] + d*(long long)depth[v];
                M[v] = max(M[u], val);
                par[v]=u;
                q.push(v);
            }
        }
    }

    sort(M.begin(), M.end());
    printf("%lld\n", M[k-1]);
}