import sys

def solve():
    input = sys.stdin.readline
    INF = 10**30

    test_cases = int(input())

    for _ in range(test_cases):
        m, a = map(int, input().split())
        a -= 1

        travel = [
            list(map(int, input().split()))
            for _ in range(m)
        ]

        total_masks = 1 << m

        dp = [
            [[INF, INF] for _ in range(m)]
            for _ in range(total_masks)
        ]

        dp[1][0][0] = 0

        for mask in range(total_masks):
            if not (mask & 1):
                continue

            has_bag = bool(mask & (1 << a))

            for last in range(m):
                if not (mask & (1 << last)):
                    continue

                for bag_used in range(2):
                    current = dp[mask][last][bag_used]

                    if current >= INF:
                        continue

                    for nxt in range(m):
                        if mask & (1 << nxt):
                            continue

                        next_mask = mask | (1 << nxt)

                        regular_cost = current + travel[last][nxt]

                        if regular_cost < dp[next_mask][nxt][bag_used]:
                            dp[next_mask][nxt][bag_used] = regular_cost

                        if has_bag and bag_used == 0:
                            bag_cost = current + travel[last][nxt] // 2

                            if bag_cost < dp[next_mask][nxt][1]:
                                dp[next_mask][nxt][1] = bag_cost

        final_mask = total_masks - 1

        answer = min(
            dp[final_mask][m - 1][0],
            dp[final_mask][m - 1][1]
        )

        print(answer)


if __name__ == "__main__":
    solve()