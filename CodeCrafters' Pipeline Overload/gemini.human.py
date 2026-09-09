import sys

input = sys.stdin.readline

for _ in range(int(input())):
    n, x = map(int, input().split())
    a = list(map(int, input().split()))

    if sum(a) == x:
        print("NO")
        continue

    a.sort()
    cur = 0

    for i in range(n - 1):
        if cur + a[i] == x:
            a[i], a[i + 1] = a[i + 1], a[i]
        cur += a[i]

    print("YES")
    print(*a)
