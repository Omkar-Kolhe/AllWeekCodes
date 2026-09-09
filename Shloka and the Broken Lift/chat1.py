import sys

input = sys.stdin.readline

MAXH = 100000

# divisors[x] contains all possible jump distances >= 2
# which divide x.
divisors = [[] for _ in range(MAXH + 1)]

for d in range(2, MAXH + 1):
    for x in range(d, MAXH + 1, d):
        divisors[x].append(d)


T = int(input())

for _ in range(T):
    N = int(input())
    H = list(map(int, input().split()))

    dp = [False] * N
    dp[0] = True

    for j in range(1, N):
        # If d divides H[j], we can come from j-d.
        for d in divisors[H[j]]:
            if d > j:
                break

            if dp[j - d]:
                dp[j] = True
                break

    print("YES" if dp[N - 1] else "NO")