import sys

def solve():
    data = sys.stdin.read().split()
    it = iter(data)
    t = int(next(it))
    out = []
    for _ in range(t):
        n = int(next(it))
        s = next(it)
        k = int(next(it))

        left = 0
        w_count = 0
        best = 0

        for right in range(n):
            if s[right] == 'W':
                w_count += 1

            while w_count > k:
                if s[left] == 'W':
                    w_count -= 1
                left += 1

            best = max(best, right - left + 1)

        out.append(str(best))

    sys.stdout.write("\n".join(out) + "\n")

if __name__ == "__main__":
    solve()