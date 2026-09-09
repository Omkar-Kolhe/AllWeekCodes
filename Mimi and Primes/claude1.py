import sys
from math import gcd

def largest_prime_factor(g):
    ans = -1
    p = 2
    while p * p <= g:
        if g % p == 0:
            ans = p
            while g % p == 0:
                g //= p
        p += 1
    if g > 1:
        ans = g
    return ans

def main():
    data = sys.stdin.read().split()
    idx = 0
    t = int(data[idx]); idx += 1
    out = []
    for _ in range(t):
        n = int(data[idx]); idx += 1
        g = 0
        for i in range(n):
            x = int(data[idx]); idx += 1
            g = gcd(g, x)
        out.append(str(largest_prime_factor(g)))
    print('\n'.join(out))

main()
