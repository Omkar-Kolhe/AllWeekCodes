import sys
from collections import deque

input = sys.stdin.readline

T = int(input())

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
                dist[i][j] = 0
                q.append((i, j))

    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    while q:
        r, c = q.popleft()

        for dr, dc in directions:
            nr = r + dr
            nc = c + dc

            if not (0 <= nr < N and 0 <= nc < M):
                continue

            if grid[nr][nc] == '#':
                continue

            if dist[nr][nc] != -1:
                continue

            dist[nr][nc] = dist[r][c] + 1
            q.append((nr, nc))

    answer = 0

    for i in range(N):
        for j in range(M):
            if grid[i][j] == 'S':
                answer = max(answer, dist[i][j])

    print(answer)