from collections import deque

T = int(input())

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

for _ in range(T):
    N, M = map(int, input().split())
    grid = [list(input().strip()) for _ in range(N)]
    
    dist = [[-1] * M for _ in range(N)]
    q = deque()
    
    for i in range(N):
        for j in range(M):
            if grid[i][j] == 'B':
                dist[i][j] = 0
                q.append((i, j))
    
    while q:
        r, c = q.popleft()
        
        for d in range(4):
            nr = r + dr[d]
            nc = c + dc[d]
            
            if 0 <= nr < N and 0 <= nc < M and grid[nr][nc] != '#' and dist[nr][nc] == -1:
                dist[nr][nc] = dist[r][c] + 1
                q.append((nr, nc))
    
    ans = 0
    for i in range(N):
        for j in range(M):
            if grid[i][j] == 'S':
                ans = max(ans, dist[i][j])
    
    print(ans)