import sys
from collections import deque

def main():
    data = sys.stdin.buffer.read().split()
    idx = 0
    t = int(data[idx]); idx += 1
    out = []

    for _ in range(t):
        n, m = int(data[idx]), int(data[idx+1])
        idx += 2
        g = []
        for i in range(n):
            g.append(data[idx].decode())
            idx += 1

        dist = [[-1]*m for _ in range(n)]
        q = deque()

        for i in range(n):
            for j in range(m):
                if g[i][j] == 'B':
                    dist[i][j] = 0
                    q.append((i, j))

        ans = 0
        while q:
            x, y = q.popleft()
            for dx, dy in ((-1,0),(1,0),(0,-1),(0,1)):
                nx, ny = x+dx, y+dy
                if 0 <= nx < n and 0 <= ny < m and dist[nx][ny] == -1 and g[nx][ny] != '#':
                    dist[nx][ny] = dist[x][y] + 1
                    if g[nx][ny] == 'S':
                        ans = max(ans, dist[nx][ny])
                    q.append((nx, ny))

        out.append(str(ans))

    print("\n".join(out))

main()