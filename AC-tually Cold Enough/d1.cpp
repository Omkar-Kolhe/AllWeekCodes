#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int N, D, K;
    cin >> N >> D >> K;
    
    vector<long long> H(N);
    for (int i = 0; i < N; i++) {
        cin >> H[i];
    }
    
    vector<vector<int>> adj(N);
    for (int i = 0; i < N - 1; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    
    // Build parent-child relationships via BFS/DFS from root 0
    vector<int> parent(N, -1);
    vector<vector<int>> children(N);
    vector<int> order;
    order.reserve(N);
    
    queue<int> q;
    q.push(0);
    parent[0] = 0;
    
    while (!q.empty()) {
        int u = q.front();
        q.pop();
        order.push_back(u);
        
        for (int v : adj[u]) {
            if (parent[v] == -1) {
                parent[v] = u;
                children[u].push_back(v);
                q.push(v);
            }
        }
    }
    
    // Check if we can even cool K rooms (tree size check)
    if (K > N) {
        cout << -1 << "\n";
        return 0;
    }
    
    // For each node, find the minimum T needed to cool at least that node
    // Actually, for a given T, we can determine which nodes get cooled
    // Binary search on T
    
    auto canCool = [&](long long T) -> bool {
        int cooled = 0;
        
        // We'll do a DFS and only propagate if current node is cooled
        // Use stack for iterative DFS
        vector<pair<int, long long>> stack;
        stack.push_back({0, T});
        
        while (!stack.empty()) {
            auto [u, temp] = stack.back();
            stack.pop_back();
            
            // Check if this room is cooled
            if (temp >= H[u]) {
                cooled++;
                // Only propagate to children if this room is cooled
                for (int v : children[u]) {
                    stack.push_back({v, temp - D});
                }
            }
            // If not cooled, we stop propagation (no children added)
        }
        
        return cooled >= K;
    };
    
    // If even with T = 0 we can cool K rooms, answer is 0
    // But H[i] can be 0, so minimum T could be 0
    // We need to find minimum T such that canCool(T) is true
    
    // Upper bound: maximum H[i] + D * depth
    // Actually a safe upper bound is max(H) + D * N
    long long maxH = *max_element(H.begin(), H.end());
    long long low = 0, high = maxH + D * N + 5;
    long long ans = -1;
    
    // But wait - if even with huge T we can't cool K, then impossible
    // Let's check with a very large T
    if (!canCool(high)) {
        cout << -1 << "\n";
        return 0;
    }
    
    while (low <= high) {
        long long mid = low + (high - low) / 2;
        
        if (canCool(mid)) {
            ans = mid;
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    
    cout << ans << "\n";
    
    return 0;
}