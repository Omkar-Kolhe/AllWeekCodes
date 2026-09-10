import sys
from collections import deque

def main():
    data = sys.stdin.buffer.read().split()
    idx = 0
    n = int(data[idx]); d = int(data[idx+1]); k = int(data[idx+2])
    idx += 3

    h = [int(x) for x in data[idx:idx+n]]
    idx += n

    adj = [[] for _ in range(n)]
    for _ in range(n-1):
        u = int(data[idx]); v = int(data[idx+1])
        idx += 2
        adj[u].append(v)
        adj[v].append(u)

    if k > n:
        print(-1)
        return

    M = [0]*n
    depth = [0]*n
    vis = [False]*n

    M[0] = h[0]
    vis[0] = True
    q = deque([0])

    while q:
        u = q.popleft()
        du = depth[u]
        mu = M[u]
        for v in adj[u]:
            if not vis[v]:
                vis[v] = True
                depth[v] = du + 1
                val = h[v] + d*(du+1)
                M[v] = mu if mu > val else val
                q.append(v)

    M.sort()
    print(M[k-1])

main()