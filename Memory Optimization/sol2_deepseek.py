import sys


def solve():
    data = sys.stdin.buffer.read().split()
    t = int(data[0])
    idx = 1
    out = []
    for _ in range(t):
        n = int(data[idx])
        idx += 1
        pairs = []
        for _ in range(n):
            x = int(data[idx])
            idx += 1
            cnt = 0
            while x % 2 == 0:
                x //= 2
                cnt += 1
            pairs.append((x, cnt))
        pairs.sort()
        ans = 0
        i = 0
        while i < n:
            odd = pairs[i][0]
            mx = 0
            while i < n and pairs[i][0] == odd:
                if pairs[i][1] > mx:
                    mx = pairs[i][1]
                i += 1
            ans += mx
        out.append(str(ans))
    sys.stdout.write("\n".join(out))


if __name__ == "__main__":
    solve()
