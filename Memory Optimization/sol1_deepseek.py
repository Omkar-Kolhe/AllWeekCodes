import sys


def solve():
    data = sys.stdin.buffer.read().split()
    t = int(data[0])
    idx = 1
    out = []
    for _ in range(t):
        n = int(data[idx])
        idx += 1
        max_exp = {}
        for _ in range(n):
            x = int(data[idx])
            idx += 1
            cnt = 0
            while x % 2 == 0:
                x //= 2
                cnt += 1
            if x not in max_exp or max_exp[x] < cnt:
                max_exp[x] = cnt
        ans = sum(max_exp.values())
        out.append(str(ans))
    sys.stdout.write("\n".join(out))


if __name__ == "__main__":
    solve()
