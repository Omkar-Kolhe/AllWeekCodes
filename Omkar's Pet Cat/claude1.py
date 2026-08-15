import sys
from math import gcd

def solve(A):
    g = 0
    for x in A:
        g = gcd(g, x)
    cnt = A.count(g)
    return "NO" if cnt >= 2 else "YES"

def main():
    data = sys.stdin.read().split()
    idx = 0
    T = int(data[idx]); idx += 1
    out = []
    for _ in range(T):
        N = int(data[idx]); idx += 1
        A = list(map(int, data[idx:idx+N])); idx += N
        out.append(solve(A))
    sys.stdout.write("\n".join(out) + "\n")

main()