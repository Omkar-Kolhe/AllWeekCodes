import sys


def solve():
    input_data = sys.stdin.read().strip().split()
    if not input_data:
        return

    idx = 0
    T = int(input_data[idx])
    idx += 1

    results = []

    for _ in range(T):
        N = int(input_data[idx])
        R = int(input_data[idx + 1])
        idx += 2

        Scap = int(input_data[idx])
        Wcap = int(input_data[idx + 1])
        idx += 2

        avengers = []
        total_impact = 0
        total_cost = 0

        for _ in range(N):
            S = int(input_data[idx])
            W = int(input_data[idx + 1])
            C = int(input_data[idx + 2])
            idx += 3
            impact = max(0, S - W)
            avengers.append((impact, C))
            total_impact += impact
            total_cost += C

        required_remove = R + total_impact - (Scap + Wcap)

        if required_remove <= 0:
            results.append("0")
            continue

        if required_remove > total_impact:
            results.append("-1")
            continue

        # DP: dp[j] = maximum impact removed with cost j
        dp = [-1] * (total_cost + 1)
        dp[0] = 0

        for impact, cost in avengers:
            if impact == 0:
                continue
            for j in range(total_cost, cost - 1, -1):
                if dp[j - cost] != -1:
                    dp[j] = max(dp[j], dp[j - cost] + impact)

        ans = -1
        for j in range(total_cost + 1):
            if dp[j] >= required_remove:
                ans = j
                break

        results.append(str(ans))

    sys.stdout.write("\n".join(results))


if __name__ == "__main__":
    solve()
