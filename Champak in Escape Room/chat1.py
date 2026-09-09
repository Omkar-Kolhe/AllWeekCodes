import sys

input = sys.stdin.readline

T = int(input())

for _ in range(T):
    N, M, K = map(int, input().split())

    grid = [input().strip() for _ in range(N)]
    S = input().strip()

    r = c = 0

    for move in S:
        nr, nc = r, c

        if move == 'U':
            nr -= 1
        elif move == 'D':
            nr += 1
        elif move == 'L':
            nc -= 1
        else:  # R
            nc += 1

        if 0 <= nr < N and 0 <= nc < M and grid[nr][nc] == '.':
            r, c = nr, nc

    print(r + 1, c + 1)