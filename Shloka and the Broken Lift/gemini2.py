import sys

def solve():
    max_h = 100000
    divisors = [[] for _ in range(max_h + 1)]
    for i in range(1, max_h + 1):
        for j in range(i, max_h + 1, i):
            divisors[j].append(i)

    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    t = int(input_data[0])
    idx = 1
    
    out = []
    for _ in range(t):
        n = int(input_data[idx])
        idx += 1
        h = [0] + [int(x) for x in input_data[idx:idx+n]]
        idx += n
        
        dp = [False] * (n + 1)
        dp[1] = True
        
        for j in range(2, n + 1):
            for d in divisors[h[j]]:
                if d >= 2 and d < j:
                    if dp[j - d]:
                        dp[j] = True
                        break
                elif d >= j:
                    break
        
        if dp[n]:
            out.append("YES")
        else:
            out.append("NO")
            
    print("\n".join(out))

if __name__ == '__main__':
    solve()