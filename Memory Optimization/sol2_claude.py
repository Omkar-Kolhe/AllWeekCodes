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
        pairs = [None] * N
        for i in range(N):
            a = int(data[idx])
            idx += 1
            k = 0
            while a % 2 == 0:
                a >>= 1
                k += 1
            pairs[i] = (a, k)
        pairs.sort()
        ans = 0
        i = 0
        n = N
        while i < n:
            cur = pairs[i][0]
            mx = pairs[i][1]
            j = i + 1
            while j < n and pairs[j][0] == cur:
                if pairs[j][1] > mx:
                    mx = pairs[j][1]
                j += 1
            ans += mx
            i = j
        out.append(str(ans))
    sys.stdout.write("\n".join(out) + "\n")


if __name__ == "__main__":
    main()
