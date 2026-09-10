import sys
from math import gcd

def largest_prime_factor(value):
    result = -1

    if value % 2 == 0:
        result = 2
        while value % 2 == 0:
            value //= 2

    factor = 3
    while factor * factor <= value:
        if value % factor == 0:
            result = factor
            while value % factor == 0:
                value //= factor
        factor += 2

    if value > 1:
        result = value

    return result

def main():
    data = list(map(int, sys.stdin.buffer.read().split()))
    pos = 0
    tests = data[pos]
    pos += 1
    answers = []

    for _ in range(tests):
        n = data[pos]
        pos += 1

        common = 0
        for _ in range(n):
            common = gcd(common, data[pos])
            pos += 1

        answers.append(str(largest_prime_factor(common)))

    sys.stdout.write("\n".join(answers))

if __name__ == "__main__":
    main()
