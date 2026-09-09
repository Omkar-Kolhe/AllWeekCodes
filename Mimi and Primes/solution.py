import sys
import math

def is_prime(n):
    if n < 2:
        return False
    if n == 2:
        return True
    if n % 2 == 0:
        return False
    d = 3
    while d * d <= n:
        if n % d == 0:
            return False
        d += 2
    return True

def solve():
    input = sys.stdin.readline
    n = int(input())
    a = list(map(int, input().split()))

    g = a[0]

    for x in a[1:]:
        g = math.gcd(g, x)

    if g < 2:
        print("DNE")
        return

    ans = 1

    while g % 2 == 0:
        ans = 2
        g //= 2

    d = 3

    while d * d <= g:
        if g % d == 0:
            if is_prime(d):
                ans = max(ans, d)

            while g % d == 0:
                g //= d

        d += 2

    if g > 1 and is_prime(g):
        ans = max(ans, g)

    if ans == 1:
        print("DNE")
    else:
        print(ans)

if __name__ == "__main__":
    solve()