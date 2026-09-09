import sys
import math

def solve():
    # Read all tokens from standard input at once
    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    t = int(input_data[0])
    idx = 1
    
    out = []
    
    for _ in range(t):
        n = int(input_data[idx])
        idx += 1
        
        # 1-based indexing for convenience 
        h = [0] + [int(x) for x in input_data[idx : idx+n]]
        idx += n
        
        dp = [False] * (n + 1)
        dp[1] = True
        
        for j in range(2, n + 1):
            limit = math.isqrt(h[j])
            for d in range(1, limit + 1):
                if h[j] % d == 0:
                    # Check factor 'd'
                    if d >= 2 and j - d >= 1 and dp[j - d]:
                        dp[j] = True
                        break
                    
                    # Check paired factor
                    paired_d = h[j] // d
                    if paired_d >= 2 and j - paired_d >= 1 and dp[j - paired_d]:
                        dp[j] = True
                        break
                        
        if dp[n]:
            out.append("YES")
        else:
            out.append("NO")
            
    print('\n'.join(out))

if __name__ == '__main__':
    solve()