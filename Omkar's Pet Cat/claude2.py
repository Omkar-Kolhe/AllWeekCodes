import sys
input = sys.stdin.readline

def gg(a, b):
    while b:
        a, b = b, a % b
    return a

t = int(input())
out = []
for _ in range(t):
    n = int(input())
    a = list(map(int, input().split()))
    g = a[0]
    for i in range(1, n):
        g = gg(g, a[i])
    c = 0
    for x in a:
        if x == g:
            c += 1
    out.append("NO" if c >= 2 else "YES")

print("\n".join(out))