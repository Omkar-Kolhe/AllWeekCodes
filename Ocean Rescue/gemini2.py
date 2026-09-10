import sys
from collections import deque

def solve():
    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    T = int(input_data[0])
    idx = 1
    out = []
    
    for _ in range(T):
        N = int(input_data[idx])
        M = int(input_data[idx+1])
        idx += 2
        
        grid = input_data[idx : idx+N]
        idx += N
        
        dist = [[-1] * M for _ in range(N)]
        q = deque()
        
        for i in range(N):
            for j in range(M):
                if grid[i][j] == 'B':
                    q.append((i, j))
                    dist[i][j] = 0
                    
        max_time = 0
        dr = [-1, 1, 0, 0]
        dc = [0, 0, -1, 1]
        
        while q:
            r, c = q.popleft()
            
            if grid[r][c] == 'S':
                if dist[r][c] > max_time:
                    max_time = dist[r][c]
                    
            for i in range(4):
                nr = r + dr[i]
                nc = c + dc[i]
                
                if 0 <= nr < N and 0 <= nc < M and grid[nr][nc] != '#' and dist[nr][nc] == -1:
                    dist[nr][nc] = dist[r][c] + 1
                    q.append((nr, nc))
                    
        out.append(str(max_time))
        
    print('\n'.join(out))

if __name__ == '__main__':
    solve()