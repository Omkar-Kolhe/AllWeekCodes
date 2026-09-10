import sys


def main():
    data = sys.stdin.buffer.read().split()
    idx = 0
    T = int(data[idx])
    idx += 1
    out = []
    for _ in range(T):
        N = int(data[idx])
        idx += 1
        max_exp = {}
        for _ in range(N):
            a = int(data[idx])
            idx += 1
            k = 0
            while a % 2 == 0:
                a >>= 1
                k += 1
            prev = max_exp.get(a)
            if prev is None or prev < k:
                max_exp[a] = k
        out.append(str(sum(max_exp.values())))
    sys.stdout.write("\n".join(out) + "\n")


if __name__ == "__main__":
    main()
