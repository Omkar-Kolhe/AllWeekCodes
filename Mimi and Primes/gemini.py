import sys
import math

def solve():
    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    t = int(input_data[0])
    idx = 1
    out = []
    
    for _ in range(t):
        n = int(input_data[idx])
        idx += 1
        
        g = 0
        for i in range(n):
            a = int(input_data[idx + i])
            g = math.gcd(g, a)
        idx += n
        
        if g == 1:
            out.append("-1")
            continue
            
        max_prime = -1
        i = 2
        temp = g
        while i * i <= temp:
            if temp % i == 0:
                max_prime = i
                while temp % i == 0:
                    temp //= i
            i += 1
        if temp > 1:
            max_prime = max(max_prime, temp)
            
        out.append(str(max_prime))
        
    print("\n".join(out))

if __name__ == '__main__':
    solve()
