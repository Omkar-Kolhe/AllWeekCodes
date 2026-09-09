import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    T = int(data[idx]); idx += 1
    out = []

    for _ in range(T):
        N = int(data[idx]); idx += 1
        H = data[idx:idx+N]
        idx += N
        H = [0] + [int(x) for x in H]

        reachable = [False] * (N + 1)
        reachable[1] = True

        for j in range(2, N + 1):
            h = H[j]
            d = 1
            found = False
            while d * d <= h:
                if h % d == 0:
                    d1 = d
                    d2 = h // d
                    if d1 >= 2 and j - d1 >= 1 and reachable[j - d1]:
                        found = True
                        break
                    if d2 >= 2 and d2 != d1 and j - d2 >= 1 and reachable[j - d2]:
                        found = True
                        break
                d += 1
            reachable[j] = found

        out.append("YES" if reachable[N] else "NO")

    print("\n".join(out))

main()