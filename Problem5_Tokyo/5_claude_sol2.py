import sys


def solve():
    data = sys.stdin.buffer.read().split()
    pos = 0

    def read_token():
        nonlocal pos
        v = data[pos]
        pos += 1
        return v

    T = int(read_token())
    results = []

    for _ in range(T):
        N = int(read_token())

        prefix = [0] * (N + 1)
        for i in range(1, N + 1):
            direction = read_token()
            angle = int(read_token())
            step = angle if direction == b'L' else -angle
            prefix[i] = prefix[i - 1] + step

        # order indices by (prefix value, index) so equal-value groups are
        # contiguous and internally sorted by index
        order = sorted(range(N + 1), key=lambda k: (prefix[k], k))

        best_len = 0
        best_s = 0
        best_e = 0

        i = 0
        total = N + 1
        while i < total:
            j = i
            val = prefix[order[i]]
            while j < total and prefix[order[j]] == val:
                j += 1
            min_idx = order[i]
            max_idx = order[j - 1]
            length = max_idx - min_idx
            if length > 0:
                if length > best_len or (length == best_len and (min_idx + 1) < best_s):
                    best_len = length
                    best_s = min_idx + 1
                    best_e = max_idx
            i = j

        if best_len == 0:
            results.append("0 0 0")
        else:
            results.append(f"{best_len} {best_s} {best_e}")

    sys.stdout.write("\n".join(results) + "\n")


solve()
