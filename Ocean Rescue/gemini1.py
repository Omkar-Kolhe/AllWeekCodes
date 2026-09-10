import sys
from collections import deque

def solve():
    # Fast bulk read
    input_data = sys.stdin.read().split()
    if not input_data:
        return
        
    t = int(input_data[0])
    idx = 1
    out = []
    
    for _ in range(t):
        n = int(input_data[idx])
        m = int(input_data[idx+1])
        idx += 2
        
        grid = input_data[idx : idx+n]
        idx += n
        
        dist = [[-1] * m for _ in range(n)]
        q = deque()
        
        # Enqueue all starting bases
        for r in range(n):
            for c in range(m):
                if grid[r][c] == 'B':
                    dist[r][c] = 0
                    q.append((r, c))
                    
        max_time = 0
        
        while q:
            r, c = q.popleft()
            
            if grid[r][c] == 'S':
                if dist[r][c] > max_time:
                    max_time = dist[r][c]
                    
            for dr, dc in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                nr, nc = r + dr, c + dc
                
                if 0 <= nr < n and 0 <= nc < m and grid[nr][nc] != '#' and dist[nr][nc] == -1:
                    dist[nr][nc] = dist[r][c] + 1
                    q.append((nr, nc))
                    
        out.append(str(max_time))
        
    print('\n'.join(out))

if __name__ == '__main__':
    solve()