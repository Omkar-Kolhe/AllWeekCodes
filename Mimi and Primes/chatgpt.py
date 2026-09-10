def prime_factors(x):
    factors = set()

    while x % 2 == 0:
        factors.add(2)
        x //= 2

    p = 3
    while p * p <= x:
        if x % p == 0:
            factors.add(p)
            while x % p == 0:
                x //= p
        p += 2

    if x > 1:
        factors.add(x)

    return factors


def main():
    n = int(input())
    numbers = list(map(int, input().split()))

    common = prime_factors(numbers[0])

    for x in numbers[1:]:
        current = prime_factors(x)
        common &= current

        if not common:
            break

    if common:
        print(max(common))
    else:
        print("DNE")


if __name__ == "__main__":
    main()
