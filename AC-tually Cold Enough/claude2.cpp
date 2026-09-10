#include <bits/stdc++.h>
using namespace std;

int main(){
    int N; long long D; int K;
    scanf("%d %lld %d", &N, &D, &K);

    vector<long long> H(N);
    for(int i=0;i<N;i++) scanf("%lld", &H[i]);

    vector<vector<int>> adj(N);
    for(int i=0;i<N-1;i++){
        int u,v;
        scanf("%d %d", &u, &v);
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    if(K > N){
        printf("-1\n");
        return 0;
    }

    vector<long long> V(N, -1);
    vector<int> depth(N, 0);
    vector<int> parent(N, -1);

    vector<int> order;
    order.reserve(N);
    vector<int> stack_;
    stack_.push_back(0);
    vector<char> visited(N, 0);
    visited[0] = 1;

    while(!stack_.empty()){
        int u = stack_.back();
        stack_.pop_back();
        order.push_back(u);
        for(int w : adj[u]){
            if(!visited[w]){
                visited[w] = 1;
                parent[w] = u;
                depth[w] = depth[u] + 1;
                stack_.push_back(w);
            }
        }
    }

    V[0] = H[0];
    for(int idx = 1; idx < (int)order.size(); idx++){
        int u = order[idx];
        long long val = H[u] + (long long)depth[u] * D;
        V[u] = max(V[parent[u]], val);
    }

    sort(V.begin(), V.end());
    printf("%lld\n", V[K-1]);

    return 0;
}