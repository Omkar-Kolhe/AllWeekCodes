import sys

input = sys.stdin.readline

N, D, K = map(int, input().split())
H = list(map(int, input().split()))

adj = [[] for _ in range(N)]

for _ in range(N - 1):
    u, v = map(int, input().split())
    adj[u].append(v)
    adj[v].append(u)

parent = [-1] * N
depth = [0] * N
need = [0] * N

order = [0]
parent[0] = -2

for u in order:
    for v in adj[u]:
        if v == parent[u]:
            continue

        parent[v] = u
        depth[v] = depth[u] + 1
        order.append(v)

need[0] = H[0]

for u in order[1:]:
    current = H[u] + depth[u] * D
    need[u] = max(need[parent[u]], current)

need.sort()

print(need[K - 1])