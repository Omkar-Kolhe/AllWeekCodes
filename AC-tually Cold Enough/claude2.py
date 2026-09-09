import sys

def main():
    data = sys.stdin.buffer.read().split()
    idx = 0
    N = int(data[idx]); D = int(data[idx+1]); K = int(data[idx+2])
    idx += 3

    H = [int(data[idx+i]) for i in range(N)]
    idx += N

    adj = [[] for _ in range(N)]
    for _ in range(N - 1):
        u = int(data[idx]); v = int(data[idx+1])
        idx += 2
        adj[u].append(v)
        adj[v].append(u)

    if K > N:
        print(-1)
        return

    parent = [-1] * N
    depth = [0] * N
    visited = [False] * N
    order = []
    stack = [0]
    visited[0] = True

    while stack:
        u = stack.pop()
        order.append(u)
        for w in adj[u]:
            if not visited[w]:
                visited[w] = True
                parent[w] = u
                depth[w] = depth[u] + 1
                stack.append(w)

    V = [0] * N
    V[0] = H[0]
    for u in order[1:]:
        val = H[u] + depth[u] * D
        V[u] = max(V[parent[u]], val)

    V.sort()
    print(V[K - 1])

main()