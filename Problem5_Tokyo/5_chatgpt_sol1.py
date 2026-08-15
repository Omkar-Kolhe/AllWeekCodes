import sys

input = sys.stdin.readline

t = int(input())

for _ in range(t):
    n = int(input())

    first = {0: 0}
    prefix = 0

    best_length = 0
    best_start = 0
    best_end = 0

    for i in range(1, n + 1):
        direction, value = input().split()
        value = int(value)

        if direction == 'L':
            prefix += value
        else:
            prefix -= value

        if prefix in first:
            previous = first[prefix]
            length = i - previous
            start = previous + 1

            if (length > best_length or
                    (length == best_length and
                     (best_start == 0 or start < best_start))):
                best_length = length
                best_start = start
                best_end = i
        else:
            first[prefix] = i

    print(best_length, best_start, best_end)
