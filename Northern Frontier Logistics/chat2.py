import sys

input = sys.stdin.readline

t = int(input())

for _ in range(t):
    n, d = map(int, input().split())
    w = list(map(int, input().split()))

    low = max(w)
    high = sum(w)

    while low <= high:
        capacity = (low + high) // 2

        days = 1
        current = 0

        for weight in w:
            if current + weight <= capacity:
                current += weight
            else:
                days += 1
                current = weight

        if days <= d:
            high = capacity - 1
        else:
            low = capacity + 1

    print(low)