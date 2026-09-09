T = int(input())

for _ in range(T):
    N, M, K = map(int, input().split())
    
    grid = []
    for _ in range(N):
        grid.append(input().strip())
    
    S = input().strip()
    
    r, c = 0, 0
    
    for ch in S:
        nr, nc = r, c
        
        if ch == 'U':
            nr -= 1
        elif ch == 'D':
            nr += 1
        elif ch == 'L':
            nc -= 1
        elif ch == 'R':
            nc += 1
        
        if 0 <= nr < N and 0 <= nc < M and grid[nr][nc] == '.':
            r, c = nr, nc
    
    print(r + 1, c + 1)