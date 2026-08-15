import sys


def main():
    data = sys.stdin.read().split()
    idx = 0

    T = int(data[idx])
    idx += 1
    out = []

    for _ in range(T):
        N = int(data[idx])
        idx += 1

        prefix = [0] * (N + 1)
        for i in range(1, N + 1):
            c = data[idx]
            x = int(data[idx + 1])
            idx += 2
            delta = x if c == 'L' else -x
            prefix[i] = prefix[i - 1] + delta

        first_occurrence = {0: 0}
        best_len = 0
        best_s = 0
        best_e = 0

        for i in range(1, N + 1):
            p = prefix[i]
            if p in first_occurrence:
                length = i - first_occurrence[p]
                if length > best_len:
                    best_len = length
                    best_s = first_occurrence[p] + 1
                    best_e = i
            else:
                first_occurrence[p] = i

        if best_len == 0:
            out.append("0 0 0")
        else:
            out.append(f"{best_len} {best_s} {best_e}")

    sys.stdout.write("\n".join(out) + "\n")


if __name__ == "__main__":
    main()
