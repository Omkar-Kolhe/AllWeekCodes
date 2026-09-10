import sys

def solve():
    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    N = int(input_data[0])
    D = int(input_data[1])
    K = int(input_data[2])
    
    if K > N:
        print("-1")
        return
        
    H = [int(x) for x in input_data[3:3+N]]
    
    idx = 3 + N
    adj = [[] for _ in range(N)]
    for _ in range(N - 1):
        u = int(input_data[idx])
        v = int(input_data[idx+1])
        idx += 2
        adj[u].append(v)
        adj[v].append(u)
        
    req = [0] * N
    req[0] = H[0]
    depth = [0] * N
    
    queue = [0]
    visited = [False] * N
    visited[0] = True
    
    for u in queue:
        for v in adj[u]:
            if not visited[v]:
                visited[v] = True
                depth[v] = depth[u] + 1
                req[v] = max(req[u], H[v] + depth[v] * D)
                queue.append(v)
                
    req.sort()
    print(req[K - 1])

if __name__ == '__main__':
    solve()