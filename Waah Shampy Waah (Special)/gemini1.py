import sys

def main():
    input_data = sys.stdin.read().split()
    if not input_data:
        return

    t = int(input_data[0])
    idx = 1
    out = []

    for _ in range(t):
        n = int(input_data[idx])
        m = int(input_data[idx + 1])
        idx += 2

        if n > (m + 1) // 2:
            out.append("-1")
        else:
            start = m - n + 1
            res = [str(x) for x in range(start, m + 1)]
            out.append(" ".join(res))

    sys.stdout.write("\n".join(out) + "\n")

if __name__ == "__main__":
    main()