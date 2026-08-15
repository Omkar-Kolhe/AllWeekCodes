import sys


def solve():
    data = sys.stdin.read().strip().split()
    if not data:
        return

    ptr = 0
    T = int(data[ptr])
    ptr += 1

    out_lines = []

    for _ in range(T):
        N = int(data[ptr])
        R = int(data[ptr + 1])
        ptr += 2

        Scap = int(data[ptr])
        Wcap = int(data[ptr + 1])
        ptr += 2

        impacts = []
        costs = []
        total_impact = 0

        for _ in range(N):
            S = int(data[ptr])
            W = int(data[ptr + 1])
            C = int(data[ptr + 2])
            ptr += 3
            imp = S - W if S > W else 0
            impacts.append(imp)
            costs.append(C)
            total_impact += imp

        target = R + total_impact - (Scap + Wcap)

        if target <= 0:
            out_lines.append("0")
            continue

        if target > total_impact:
            out_lines.append("-1")
            continue

        # Use dictionary for sparse DP
        dp = {0: 0}  # cost -> max impact removed

        for imp, cost in zip(impacts, costs):
            if imp == 0:
                continue
            new_items = []
            for c, removed in dp.items():
                new_cost = c + cost
                new_removed = removed + imp
                new_items.append((new_cost, new_removed))

            for c, r in new_items:
                if c in dp:
                    dp[c] = max(dp[c], r)
                else:
                    dp[c] = r

        ans = min((c for c, r in dp.items() if r >= target), default=-1)
        out_lines.append(str(ans))

    sys.stdout.write("\n".join(out_lines))


if __name__ == "__main__":
    solve()
