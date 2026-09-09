import sys
from collections import deque

input = sys.stdin.readline

T = int(input())

for _ in range(T):
    N, M = map(int, input().split())

    grid = []
    q = deque()

    dist = [-1] * (N * M)

    for r in range(N):
        row = input().strip()
        grid.append(row)

        for c in range(M):
            if row[c] == 'B':
                pos = r * M + c
                dist[pos] = 0
                q.append(pos)

    answer = 0

    # Multi-source BFS.
    # Since every B starts at distance 0, the first time
    # we reach a cell is its minimum distance from any base.
    while q:
        pos = q.popleft()

        r = pos // M
        c = pos % M

        if grid[r][c] == 'S':
            answer = max(answer, dist[pos])

        for dr, dc in ((-1, 0), (1, 0), (0, -1), (0, 1)):
            nr = r + dr
            nc = c + dc

            if nr < 0 or nr >= N or nc < 0 or nc >= M:
                continue

            if grid[nr][nc] == '#':
                continue

            nxt = nr * M + nc

            if dist[nxt] != -1:
                continue

            dist[nxt] = dist[pos] + 1
            q.append(nxt)

    print(answer)