import sys

def solve():
    M, A = map(int, input().split())
    
    C = []
    for i in range(M + 1):
        C.append(list(map(int, input().split())))
    
    INF = float('inf')
    fullMask = (1 << M) - 1
    
    dp = [[INF] * (M + 1) for _ in range(fullMask + 1)]
    dpBag = [[INF] * (M + 1) for _ in range(fullMask + 1)]
    
    dp[1][1] = 0
    
    for mask in range(1, fullMask + 1):
        for last in range(1, M + 1):
            if not (mask & (1 << (last - 1))):
                continue
            
            if dp[mask][last] < INF:
                for nxt in range(1, M + 1):
                    if mask & (1 << (nxt - 1)):
                        continue
                    
                    newMask = mask | (1 << (nxt - 1))
                    
                    if last == A:
                        newCostBag = dp[mask][last] + C[last][nxt] // 2
                        dpBag[newMask][nxt] = min(dpBag[newMask][nxt], newCostBag)
                    
                    newCost = dp[mask][last] + C[last][nxt]
                    dp[newMask][nxt] = min(dp[newMask][nxt], newCost)
            
            if dpBag[mask][last] < INF:
                for nxt in range(1, M + 1):
                    if mask & (1 << (nxt - 1)):
                        continue
                    
                    newMask = mask | (1 << (nxt - 1))
                    newCost = dpBag[mask][last] + C[last][nxt]
                    dpBag[newMask][nxt] = min(dpBag[newMask][nxt], newCost)
    
    print(dpBag[fullMask][M])

T = int(input())
for _ in range(T):
    solve()