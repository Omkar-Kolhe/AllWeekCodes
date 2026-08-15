import sys

input = sys.stdin.readline

t = int(input())

for _ in range(t):
    n, r = map(int, input().split())
    scap, wcap = map(int, input().split())

    people = []
    total = r

    for _ in range(n):
        s, w, c = map(int, input().split())
        impact = max(0, s - w)
        total += impact
        people.append((impact, c))

    allowed = scap + wcap

    if total <= allowed:
        print(0)
        continue

    required = total - allowed
    INF = 10**18

    dp = [INF] * (required + 1)
    dp[0] = 0

    for impact, cost in people:
        for current in range(required, -1, -1):
            if dp[current] == INF:
                continue

            new_amount = min(required, current + impact)
            candidate = dp[current] + cost

            if candidate < dp[new_amount]:
                dp[new_amount] = candidate

    print(-1 if dp[required] == INF else dp[required])
