import sys

def solve():
    data = sys.stdin.read().strip().split()
    if not data:
        return
    it = iter(data)
    N = int(next(it))
    out = []
    for _ in range(N):
        P = int(next(it))
        C = int(next(it))
        S = int(next(it))
        L = int(next(it))

        produced = L // P

        attempts = 0
        if S <= L:
            attempts = (L - S) // C + 1

        successful = attempts
        if S < P and S <= L:
            successful = max(0, attempts - 1)

        lag = produced - successful
        out.append(str(lag))

    sys.stdout.write("\n".join(out) + "\n")

if __name__ == "__main__":
    solve()