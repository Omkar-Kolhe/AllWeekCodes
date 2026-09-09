import sys

input = sys.stdin.readline

t = int(input())

for _ in range(t):
    n, m = map(int, input().split())

    start = m // 2 + 1

    if m - start + 1 < n:
        print(-1)
    else:
        print(*range(start, start + n))