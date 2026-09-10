import sys
from collections import deque

input = sys.stdin.readline


def remove_middle(a, b, c):
    """Check whether line b is unnecessary."""
    am, ab = a
    bm, bb = b
    cm, cb = c

    return (bb - ab) * (bm - cm) >= (cb - bb) * (am - bm)


def line_value(line, x):
    m, b = line
    return m * x + b


def solve():
    n, transition_cost = map(int, input().split())
    heights = list(map(int, input().split()))

    dp = [0] * n
    hull = deque()

    first_height = heights[0]
    hull.append((-2 * first_height, first_height * first_height))

    for i in range(1, n):
        x = heights[i]

        # The heights are strictly increasing, so the best line
        # moves only forward in the deque.
        while len(hull) >= 2 and line_value(hull[0], x) >= line_value(hull[1], x):
            hull.popleft()

        dp[i] = x * x + transition_cost + line_value(hull[0], x)

        new_line = (-
                    2 * x, dp[i] + x * x)

        while len(hull) >= 2 and remove_middle(
            hull[-2], hull[-1], new_line
        ):
            hull.pop()

        hull.append(new_line)

    print(dp[-1])


if __name__ == "__main__":
    solve()
