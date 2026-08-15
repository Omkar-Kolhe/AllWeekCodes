import sys

input = sys.stdin.readline

t = int(input())

for _ in range(t):
    n = int(input())
    a = list(map(int, input().split()))

    mn = min(a)

    count = 0
    possible = False

    for x in a:
        if x == mn:
            count += 1

        if x % mn != 0:
            possible = True

    if count == 1 or possible:
        print("YES")
    else:
        print("NO")