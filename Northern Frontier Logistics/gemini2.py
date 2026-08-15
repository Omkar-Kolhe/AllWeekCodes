def solve():
    t = int(input())
    for _ in range(t):
        n, d = map(int, input().split())
        weights = list(map(int, input().split()))
        
        lo, hi = max(weights), sum(weights)
        ans = hi
        while lo <= hi:
            mid = (lo + hi) // 2
            days, s = 1, 0
            for w in weights:
                if s + w > mid:
                    days += 1
                    s = 0
                s += w
            if days <= d:
                ans = mid
                hi = mid - 1
            else:
                lo = mid + 1
        print(ans)

solve()