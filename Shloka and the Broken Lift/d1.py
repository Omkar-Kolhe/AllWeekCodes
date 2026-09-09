import sys
import math

input = sys.stdin.readline

T = int(input())

for _ in range(T):
    N = int(input())
    H = [0] + list(map(int, input().split()))
    
    reachable = [False] * (N + 1)
    reachable[1] = True
    
    for i in range(1, N + 1):
        if not reachable[i]:
            continue
        
        # Find all divisors of H[i] - these are possible jump lengths
        d = 1
        while d * d <= H[i]:
            if H[i] % d == 0:
                # d is a divisor
                if d >= 2:
                    j = i + d
                    if j <= N:
                        reachable[j] = True
                
                # H[i] // d is also a divisor
                other = H[i] // d
                if other >= 2 and other != d:
                    j = i + other
                    if j <= N:
                        reachable[j] = True
            
            d += 1
    
    print("YES" if reachable[N] else "NO")