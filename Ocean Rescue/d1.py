import sys
from collections import deque

input = sys.stdin.readline

T = int(input())

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

for _ in range(T):
    N, M = map(int, input().split())
    
    grid = []
    dist = [[-1] * M for _ in range(N)]
    q = deque()
    
    for i in range(N):
        row = input().strip()
        grid.append(row)
        for j in range(M):
            if row[j] == 'B':
                q.append((i, j))
                dist[i][j] = 0
    
    ans = 0
    
    while q:
        r, c = q.popleft()
        
        if grid[r][c] == 'S':
            ans = max(ans, dist[r][c])
        
        for d in range(4):
            nr = r + dr[d]
            nc = c + dc[d]
            
            if 0 <= nr < N and 0 <= nc < M:
                if grid[nr][nc] != '#' and dist[nr][nc] == -1:
                    dist[nr][nc] = dist[r][c] + 1
                    q.append((nr, nc))
    
    print(ans)