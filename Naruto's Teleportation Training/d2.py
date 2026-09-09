import sys

def solve() -> None:
    data = sys.stdin.read().strip().split()
    if not data:
        return
    it = iter(data)
    t = int(next(it))
    out = []
    for _ in range(t):
        a = int(next(it))
        b = int(next(it))
        c = int(next(it))
        d = int(next(it))

        # Impossible conditions
        if b > d or (d - c) < (b - a):
            out.append("-1")
        else:
            ans = (a - c) + 2 * (d - b)
            out.append(str(ans))

    sys.stdout.write("\n".join(out) + "\n")

if __name__ == "__main__":
    solve()