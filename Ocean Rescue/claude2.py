import sys
from collections import deque

def main():
    data = sys.stdin.buffer.read().split()
    idx = 0
    T = int(data[idx]); idx += 1
    out = []

    for _ in range(T):
        N = int(data[idx]); M = int(data[idx+1])
        idx += 2

        grid = []
        for i in range(N):
            grid.append(data[idx].decode())
            idx += 1

        dist = [[-1] * M for _ in range(N)]
        q = deque()

        for i in range(N):
            for j in range(M):
                if grid[i][j] == 'B':
                    dist[i][j] = 0
                    q.append((i, j))

        while q:
            r, c = q.popleft()
            d = dist[r][c]
            if r > 0 and grid[r-1][c] != '#' and dist[r-1][c] == -1:
                dist[r-1][c] = d + 1
                q.append((r-1, c))
            if r < N-1 and grid[r+1][c] != '#' and dist[r+1][c] == -1:
                dist[r+1][c] = d + 1
                q.append((r+1, c))
            if c > 0 and grid[r][c-1] != '#' and dist[r][c-1] == -1:
                dist[r][c-1] = d + 1
                q.append((r, c-1))
            if c < M-1 and grid[r][c+1] != '#' and dist[r][c+1] == -1:
                dist[r][c+1] = d + 1
                q.append((r, c+1))

        ans = 0
        for i in range(N):
            for j in range(M):
                if grid[i][j] == 'S':
                    ans = max(ans, dist[i][j])

        out.append(str(ans))

    print("\n".join(out))

main()