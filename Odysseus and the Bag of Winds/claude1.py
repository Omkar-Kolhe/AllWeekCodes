import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    t = int(data[idx]); idx += 1
    
    out = []
    INF = float('inf')
    
    for _ in range(t):
        m = int(data[idx]); idx += 1
        a = int(data[idx]); idx += 1
        a_idx = a - 1
        
        c = []
        for i in range(m):
            row = data[idx:idx+m]
            idx += m
            c.append([int(x) for x in row])
        
        full = 1 << m
        dp = [[[INF, INF] for _ in range(m)] for _ in range(full)]
        dp[1][0][0] = 0
        
        for mask in range(1, full):
            if not (mask & 1):
                continue
            a_reached = (mask & (1 << a_idx)) != 0
            
            for last in range(m):
                if not (mask & (1 << last)):
                    continue
                for bag in range(2):
                    cur = dp[mask][last][bag]
                    if cur == INF:
                        continue
                    for j in range(m):
                        if mask & (1 << j):
                            continue
                        nmask = mask | (1 << j)
                        
                        nc = cur + c[last][j]
                        if nc < dp[nmask][j][bag]:
                            dp[nmask][j][bag] = nc
                        
                        if bag == 0 and a_reached:
                            hc = cur + c[last][j] // 2
                            if hc < dp[nmask][j][1]:
                                dp[nmask][j][1] = hc
        
        fullmask = full - 1
        ans = min(dp[fullmask][m-1][0], dp[fullmask][m-1][1])
        out.append(str(ans))
    
    print("\n".join(out))

main()