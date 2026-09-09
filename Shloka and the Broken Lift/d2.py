import math

T = int(input())

for _ in range(T):
    N = int(input())
    H = [0] + list(map(int, input().split()))
    
    reachable = [False] * (N + 1)
    reachable[1] = True
    
    for j in range(3, N + 1):
        h = H[j]
        d = 1
        while d * d <= h:
            if h % d == 0:
                dist1 = d
                dist2 = h // d
                
                if dist1 >= 2 and j - dist1 >= 1 and reachable[j - dist1]:
                    reachable[j] = True
                    break
                if dist2 >= 2 and dist2 != dist1 and j - dist2 >= 1 and reachable[j - dist2]:
                    reachable[j] = True
                    break
            d += 1
    
    print("YES" if reachable[N] else "NO")