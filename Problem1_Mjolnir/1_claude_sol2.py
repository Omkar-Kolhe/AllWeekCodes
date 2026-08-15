import sys


def solve():
    data = sys.stdin.buffer.read().split()
    pos = 0

    def read_int():
        nonlocal pos
        v = data[pos]
        pos += 1
        return int(v)

    T = read_int()
    results = []
    INF = float('inf')

    for _ in range(T):
        N = read_int()
        R = read_int()
        s_cap = read_int()
        w_cap = read_int()

        impact_list = []
        cost_list = []

        for _ in range(N):
            s = read_int()
            w = read_int()
            c = read_int()
            im = s - w
            if im < 0:
                im = 0
            impact_list.append(im)
            cost_list.append(c)

        cap_total = s_cap + w_cap

        if R > cap_total:
            results.append(-1)
            continue

        allowed = cap_total - R

        # dp[w] = minimum total convincing cost such that the impact of
        # avengers left un-convinced does not exceed w
        dp = [0] * (allowed + 1)

        for im, c in zip(impact_list, cost_list):
            for w in range(allowed, -1, -1):
                convince_opt = dp[w] + c
                keep_opt = dp[w - im] if w >= im else INF
                dp[w] = convince_opt if convince_opt < keep_opt else keep_opt

        results.append(dp[allowed])

    sys.stdout.write("\n".join(map(str, results)) + "\n")


solve()
