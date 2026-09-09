import sys

def main():
    data = sys.stdin.buffer.read().split()
    idx = 0
    t = int(data[idx]); idx += 1
    out = []

    for _ in range(t):
        n = int(data[idx]); idx += 1
        h = [0] + [int(x) for x in data[idx:idx+n]]
        idx += n

        ok = [False] * (n + 1)
        ok[1] = True

        for j in range(2, n + 1):
            val = h[j]
            d = 1
            reached = False
            while d * d <= val:
                if val % d == 0:
                    d1, d2 = d, val // d
                    if d1 >= 2 and d1 <= j - 1 and ok[j - d1]:
                        reached = True
                        break
                    if d2 >= 2 and d2 <= j - 1 and ok[j - d2]:
                        reached = True
                        break
                d += 1
            ok[j] = reached

        out.append("YES" if ok[n] else "NO")

    print("\n".join(out))

main()