import sys

input = sys.stdin.readline

T = int(input())

for _ in range(T):
    N = int(input())
    arr = list(map(int, input().split()))

    max_exponent = {}

    for x in arr:
        exponent = 0

        while x % 2 == 0:
            x //= 2
            exponent += 1

        if x not in max_exponent:
            max_exponent[x] = exponent
        else:
            max_exponent[x] = max(max_exponent[x], exponent)

    answer = sum(max_exponent.values())

    print(answer)
