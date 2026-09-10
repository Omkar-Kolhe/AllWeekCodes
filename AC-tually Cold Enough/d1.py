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

# Build tree structure with parent-child relationships
parent = [-1] * N
children = [[] for _ in range(N)]

q = deque([0])
parent[0] = 0

while q:
    u = q.popleft()
    for v in adj[u]:
        if parent[v] == -1:
            parent[v] = u
            children[u].append(v)
            q.append(v)

if K > N:
    print(-1)
    sys.exit()

def can_cool(T):
    cooled = 0
    stack = [(0, T)]  # (node, temperature)
    
    while stack:
        u, temp = stack.pop()
        
        if temp >= H[u]:
            cooled += 1
            if cooled >= K:
                return True
            
            # Only propagate to children if this room is cooled
            for v in children[u]:
                stack.append((v, temp - D))
    
    return cooled >= K

# Check if possible at all
maxH = max(H)
high = maxH + D * N + 5

if not can_cool(high):
    print(-1)
    sys.exit()

# Binary search for minimum T
low = 0
ans = -1

while low <= high:
    mid = (low + high) // 2
    
    if can_cool(mid):
        ans = mid
        high = mid - 1
    else:
        low = mid + 1

print(ans)