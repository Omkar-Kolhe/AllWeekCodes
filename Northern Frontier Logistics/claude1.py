import sys

def can_ship(W, cap, D):
    days, cur = 1, 0
    for w in W:
        if cur + w > cap:
            days += 1
            cur = 0
        cur += w
    return days <= D

def min_capacity(W, D):
    lo, hi = max(W), sum(W)
    while lo < hi:
        mid = (lo + hi) // 2
        if can_ship(W, mid, D):
            hi = mid
        else:
            lo = mid + 1
    return lo

def main():
    data = sys.stdin.read().split()
    idx = 0
    T = int(data[idx]); idx += 1
    results = []
    for _ in range(T):
        N, D = int(data[idx]), int(data[idx+1])
        idx += 2
        W = list(map(int, data[idx:idx+N]))
        idx += N
        results.append(str(min_capacity(W, D)))
    sys.stdout.write("\n".join(results) + "\n")

if __name__ == "__main__":
    main()