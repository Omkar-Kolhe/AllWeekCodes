import sys


def main():
    data = sys.stdin.read().split()
    idx = 0

    T = int(data[idx])
    idx += 1
    out_lines = []

    for _ in range(T):
        N = int(data[idx])
        R = int(data[idx + 1])
        idx += 2
        Scap = int(data[idx])
        Wcap = int(data[idx + 1])
        idx += 2

        impacts = [0] * N
        costs = [0] * N
        total_cost = 0

        for i in range(N):
            S = int(data[idx])
            W = int(data[idx + 1])
            C = int(data[idx + 2])
            idx += 3
            imp = S - W
            if imp < 0:
                imp = 0
            impacts[i] = imp
            costs[i] = C
            total_cost += C

        cap_total = Scap + Wcap

        if R > cap_total:
            out_lines.append("-1")
            continue

        allowed = cap_total - R

        # dp[w] = maximum cost of avengers kept (not convinced) with
        # combined impact <= w
        dp = [0] * (allowed + 1)

        for i in range(N):
            wt = impacts[i]
            val = costs[i]
            if wt > allowed:
                continue
            for w in range(allowed, wt - 1, -1):
                candidate = dp[w - wt] + val
                if candidate > dp[w]:
                    dp[w] = candidate

        best_kept = dp[allowed]
        out_lines.append(str(total_cost - best_kept))

    sys.stdout.write("\n".join(out_lines) + "\n")


if __name__ == "__main__":
    main()
