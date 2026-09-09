import sys

input = sys.stdin.readline

T = int(input())

for _ in range(T):
    N = int(input())
    H = [0] + list(map(int, input().split()))

    dp = [False] * (N + 1)
    dp[1] = True

    for j in range(3, N + 1):
        x = H[j]
        d = 1

        while d * d <= x:
            if x % d == 0:
                d1 = d
                d2 = x // d

                if d1 >= 2 and d1 < j and dp[j - d1]:
                    dp[j] = True
                    break

                if d2 != d1 and d2 >= 2 and d2 < j and dp[j - d2]:
                    dp[j] = True
                    break

            d += 1

    print("YES" if dp[N] else "NO")