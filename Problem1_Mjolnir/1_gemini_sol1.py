# 1_gemini_sol1.py
import sys


def solve():
    input_data = sys.stdin.read().split()
    if not input_data:
        return

    T = int(input_data[0])
    idx = 1

    out = []
    for _ in range(T):
        N = int(input_data[idx])
        R = int(input_data[idx+1])
        idx += 2

        S_cap = int(input_data[idx])
        W_cap = int(input_data[idx+1])
        idx += 2

        capacity = S_cap + W_cap - R

        items = []
        tot_cost = 0
        for _ in range(N):
            s = int(input_data[idx])
            w = int(input_data[idx+1])
            c = int(input_data[idx+2])
            idx += 3

            impact = max(0, s - w)
            items.append((impact, c))
            tot_cost += c

        if capacity < 0:
            out.append("-1")
            continue

        dp = [0] * (capacity + 1)
        for impact, cost in items:
            for j in range(capacity, impact - 1, -1):
                if dp[j - impact] + cost > dp[j]:
                    dp[j] = dp[j - impact] + cost

        out.append(str(tot_cost - dp[capacity]))

    print('\n'.join(out))


if __name__ == '__main__':
    solve()
