import sys
def solve():
    data = list(map(int, sys.stdin.read().split()))
    idx, T = 0, data[idx]; idx += 1
    out = []
    for _ in range(T):
        N, D = data[idx], data[idx+1]; idx += 2
        W = data[idx:idx+N]; idx += N
        lo, hi = max(W), sum(W)
        while lo < hi:
            mid = (lo + hi) // 2
            cnt, cur = 1, 0
            for w in W:
                if cur + w > mid:
                    cnt += 1
                    cur = 0
                cur += w
            if cnt <= D:
                hi = mid
            else:
                lo = mid + 1
        out.append(str(lo))
    sys.stdout.write("\n".join(out))
if __name__ == "__main__":
    solve()