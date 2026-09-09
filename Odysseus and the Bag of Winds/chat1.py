import sys

input = sys.stdin.readline
INF = 10**30

t = int(input())

for _ in range(t):
    m, a = map(int, input().split())
    a -= 1

    cost = [list(map(int, input().split())) for _ in range(m)]

    size = 1 << m

    dp = [
        [[INF, INF] for _ in range(m)]
        for _ in range(size)
    ]

    dp[1][0][0] = 0

    for mask in range(1, size):
        if not (mask & 1):
            continue

        reached_a = bool(mask & (1 << a))

        for last in range(m):
            if not (mask & (1 << last)):
                continue

            for used in range(2):
                cur = dp[mask][last][used]

                if cur == INF:
                    continue

                for nxt in range(m):
                    if mask & (1 << nxt):
                        continue

                    new_mask = mask | (1 << nxt)

                    value = cur + cost[last][nxt]
                    if value < dp[new_mask][nxt][used]:
                        dp[new_mask][nxt][used] = value

                    if not used and reached_a:
                        value = cur + cost[last][nxt] // 2
                        if value < dp[new_mask][nxt][1]:
                            dp[new_mask][nxt][1] = value

    full = size - 1
    print(min(dp[full][m - 1]))