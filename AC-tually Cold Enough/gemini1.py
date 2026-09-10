import sys
from collections import deque

def solve():
    # Fast I/O reading all tokens
    input_data = sys.stdin.read().split()
    if not input_data:
        return
        
    n = int(input_data[0])
    d = int(input_data[1])
    k = int(input_data[2])
    
    if k > n:
        print("-1")
        return
        
    h = [int(x) for x in input_data[3 : 3+n]]
    
    adj = [[] for _ in range(n)]
    idx = 3 + n
    
    for _ in range(n - 1):
        u = int(input_data[idx])
        v = int(input_data[idx+1])
        adj[u].append(v)
        adj[v].append(u)
        idx += 2
        
    actual_req = [0] * n
    depth = [0] * n
    vis = [False] * n
    
    q = deque([0])
    vis[0] = True
    actual_req[0] = h[0]
    
    while q:
        u = q.popleft()
        
        for v in adj[u]:
            if not vis[v]:
                vis[v] = True
                depth[v] = depth[u] + 1
                
                cur_req = h[v] + depth[v] * d
                actual_req[v] = max(actual_req[u], cur_req)
                
                q.append(v)
                
    actual_req.sort()
    
    # 0-indexed, so we want the K-1 element
    print(actual_req[k - 1])

if __name__ == '__main__':
    solve()