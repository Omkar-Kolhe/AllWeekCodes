import sys

input = sys.stdin.readline

T = int(input())

for _ in range(T):
    N = int(input())
    arr = list(map(int, input().split()))

    blocks = []

    for x in arr:
        exponent = 0

        while x % 2 == 0:
            x //= 2
            exponent += 1

        blocks.append((x, exponent))

    blocks.sort()

    answer = 0
    i = 0

    while i < N:
        odd_part = blocks[i][0]
        max_exponent = 0

        while i < N and blocks[i][0] == odd_part:
            max_exponent = max(max_exponent, blocks[i][1])
            i += 1

        answer += max_exponent

    print(answer)
