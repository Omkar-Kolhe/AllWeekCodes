import sys

def solve():
    data = list(map(int, sys.stdin.read().split()))
    idx = 0
    T = data[idx]; idx += 1
    out = []

    for _ in range(T):
        N = data[idx]; idx += 1
        A = data[idx:idx+N]; idx += N

        mn = min(A)
        has_non_div = any(x % mn != 0 for x in A)
        cnt_min = A.count(mn)

        out.append("YES" if has_non_div or cnt_min == 1 else "NO")

    sys.stdout.write("\n".join(out))

if __name__ == "__main__":
    solve()