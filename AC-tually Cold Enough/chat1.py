import sys
from collections import deque

input = sys.stdin.readline

N, D, K = map(int, input().split())
H = list(map(int, input().split()))

graph = [[] for _ in range(N)]

for _ in range(N - 1):
    u, v = map(int, input().split())
    graph[u].append(v)
    graph[v].append(u)

if K > N:
    print(-1)
    sys.exit()

# need[i] = minimum T required to cool node i
# including the requirement of every ancestor.
need = [0] * N
parent = [-1] * N
depth = [0] * N

q = deque([0])
parent[0] = 0
need[0] = H[0]

while q:
    u = q.popleft()

    for v in graph[u]:
        if v == parent[u]:
            continue

        parent[v] = u
        depth[v] = depth[u] + 1

        current = H[v] + depth[v] * D

        need[v] = max(need[u], current)

        q.append(v)

need.sort()

print(need[K - 1])