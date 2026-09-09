from collections import deque
import sys

input = sys.stdin.readline


def bad(l1, l2, l3):
    """
    Check whether l2 becomes unnecessary.

    Lines are represented as:
    (slope, intercept)
    """

    m1, b1 = l1
    m2, b2 = l2
    m3, b3 = l3

    return (b2 - b1) * (m2 - m3) >= \
           (b3 - b2) * (m1 - m2)


def value(line, x):
    m, b = line
    return m * x + b


def main():
    N, C = map(int, input().split())
    h = list(map(int, input().split()))

    dp = [0] * N

    hull = deque()

    # Starting milestone
    hull.append((
        -2 * h[0],
        h[0] * h[0]
    ))

    for j in range(1, N):

        x = h[j]

        # Query minimum line
        while len(hull) >= 2:
            if value(hull[0], x) >= value(hull[1], x):
                hull.popleft()
            else:
                break

        dp[j] = x * x + C + value(hull[0], x)

        # New line
        new_line = (
            -2 * h[j],
            dp[j] + h[j] * h[j]
        )

        # Remove useless lines
        while len(hull) >= 2 and bad(
            hull[-2],
            hull[-1],
            new_line
        ):
            hull.pop()

        hull.append(new_line)

    print(dp[N - 1])


if __name__ == "__main__":
    main()