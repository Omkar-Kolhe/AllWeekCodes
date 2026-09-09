import sys

def solve() -> None:
    tokens = sys.stdin.read().split()
    if not tokens:
        return

    iterator = iter(tokens)
    t = int(next(iterator))
    infinity = float("inf")
    output = []

    for _ in range(t):
        m = int(next(iterator))
        a = int(next(iterator))
        aeolus = a - 1
        ithaca = m - 1

        matrix = [[int(next(iterator)) for _ in range(m)] for _ in range(m)]

        intermediate_bound = 1 << (m - 1)
        dp = [[[infinity, infinity] for _ in range(m)] for _ in range(intermediate_bound)]
        dp[1][0][0] = 0

        full_intermediate = intermediate_bound - 1
        optimal_cost = infinity

        for mask in range(1, intermediate_bound, 2):
            aeolus_visited = (mask & (1 << aeolus)) != 0

            for u in range(m - 1):
                for bag_state in (0, 1):
                    base_val = dp[mask][u][bag_state]
                    if base_val == infinity:
                        continue

                    if mask == full_intermediate:
                        direct = base_val + matrix[u][ithaca]
                        if direct < optimal_cost:
                            optimal_cost = direct
                        if aeolus_visited and bag_state == 0:
                            halved = base_val + matrix[u][ithaca] // 2
                            if halved < optimal_cost:
                                optimal_cost = halved
                    else:
                        for v in range(1, m - 1):
                            if not (mask & (1 << v)):
                                nxt = mask | (1 << v)
                                norm = base_val + matrix[u][v]
                                if norm < dp[nxt][v][bag_state]:
                                    dp[nxt][v][bag_state] = norm

                                if aeolus_visited and bag_state == 0:
                                    disc = base_val + matrix[u][v] // 2
                                    if disc < dp[nxt][v][1]:
                                        dp[nxt][v][1] = disc

        output.append(str(optimal_cost))

    sys.stdout.write("\n".join(output) + "\n")

if __name__ == "__main__":
    solve()