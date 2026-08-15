import sys


def solve():
    data = sys.stdin.read().strip().split()
    if not data:
        return

    idx = 0
    T = int(data[idx])
    idx += 1

    results = []

    for _ in range(T):
        N = int(data[idx])
        idx += 1

        prefix = [0] * (N + 1)

        for i in range(1, N + 1):
            C = data[idx]
            X = int(data[idx + 1])
            idx += 2

            if C == 'L':
                prefix[i] = prefix[i - 1] + X
            else:
                prefix[i] = prefix[i - 1] - X

        first_pos = {0: 0}
        best_len = 0
        best_start = 1

        for i in range(1, N + 1):
            curr = prefix[i]

            if curr in first_pos:
                start = first_pos[curr] + 1
                length = i - first_pos[curr]

                if length > best_len or (length == best_len and start < best_start):
                    best_len = length
                    best_start = start
            else:
                first_pos[curr] = i

        if best_len == 0:
            results.append("0 0 0")
        else:
            results.append(
                f"{best_len} {best_start} {best_start + best_len - 1}")

    sys.stdout.write("\n".join(results))


if __name__ == "__main__":
    solve()
