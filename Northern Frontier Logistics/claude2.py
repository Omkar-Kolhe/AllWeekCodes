import sys
input = sys.stdin.readline

t = int(input())
out = []
for _ in range(t):
    n, d = map(int, input().split())
    w = list(map(int, input().split()))
    lo = max(w)
    hi = sum(w)
    ans = hi
    while lo <= hi:
        mid = (lo + hi) // 2
        day = 1
        s = 0
        ok = True
        for x in w:
            if s + x > mid:
                day += 1
                s = x
                if day > d:
                    ok = False
                    break
            else:
                s += x
        if ok:
            ans = mid
            hi = mid - 1
        else:
            lo = mid + 1
    out.append(str(ans))

print("\n".join(out))