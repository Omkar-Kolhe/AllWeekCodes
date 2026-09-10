import sys
from collections import deque

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
order = []

stack = [0]
parent[0] = -2

while stack:
    u = stack.pop()
    order.append(u)
    
    for v in adj[u]:
        if parent[v] == -1:
            parent[v] = u
            stack.append(v)

for i in range(1, N):
    u = order[i]
    depth[u] = depth[parent[u]] + 1

req = []
for i in range(N):
    req.append(H[i] + depth[i] * D)

req.sort()

print(req[K - 1])