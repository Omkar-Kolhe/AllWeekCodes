import sys


def possible(weights, days_limit, capacity):
    days = 1
    current = 0

    for weight in weights:
        if current + weight <= capacity:
            current += weight
        else:
            days += 1
            current = weight

    return days <= days_limit


def solve():
    input = sys.stdin.buffer.readline

    t = int(input())

    for _ in range(t):
        n, d = map(int, input().split())
        weights = list(map(int, input().split()))

        left = max(weights)
        right = sum(weights)

        while left < right:
            mid = (left + right) // 2

            if possible(weights, d, mid):
                right = mid
            else:
                left = mid + 1

        print(left)


if __name__ == "__main__":
    solve()