import sys

def main():
    input_data = sys.stdin.read().split()
    if not input_data:
        return

    it = iter(input_data)
    num_tests = int(next(it))
    INF = 10**9

    for _ in range(num_tests):
        m = int(next(it))
        a = int(next(it))
        aeolus = a - 1
        ithaca = m - 1

        cost = []
        for _ in range(m):
            cost.append([int(next(it)) for _ in range(m)])

        total_masks = 1 << (m - 1)
        # dp[mask][u][bag]
        dp = [[[INF, INF] for _ in range(m)] for _ in range(total_masks)]
        dp[1][0][0] = 0

        target_mask = total_masks - 1
        best_time = INF

        for mask in range(1, total_masks, 2):
            has_bag = bool(mask & (1 << aeolus))

            for u in range(m - 1):
                for bag in (0, 1):
                    cur_cost = dp[mask][u][bag]
                    if cur_cost >= INF:
                        continue

                    if mask == target_mask:
                        best_time = min(best_time, cur_cost + cost[u][ithaca])
                        if has_bag and bag == 0:
                            best_time = min(best_time, cur_cost + cost[u][ithaca] // 2)
                    else:
                        for v in range(1, m - 1):
                            if not (mask & (1 << v)):
                                next_mask = mask | (1 << v)

                                if cur_cost + cost[u][v] < dp[next_mask][v][bag]:
                                    dp[next_mask][v][bag] = cur_cost + cost[u][v]

                                if has_bag and bag == 0:
                                    reduced = cur_cost + cost[u][v] // 2
                                    if reduced < dp[next_mask][v][1]:
                                        dp[next_mask][v][1] = reduced

        print(best_time)

if __name__ == "__main__":
    main()