import sys
from collections import deque

def main():
    data = list(map(int, sys.stdin.buffer.read().split()))
    it = iter(data)

    n = next(it)
    cost = next(it)
    height = [next(it) for _ in range(n)]

    dp = [0] * n
    lines = deque([(-2 * height[0], height[0] * height[0])])

    def value(line, x):
        return line[0] * x + line[1]

    def bad(first, middle, last):
        return ((middle[1] - first[1]) * (middle[0] - last[0])
                >= (last[1] - middle[1]) * (first[0] - middle[0]))

    for i in range(1, n):
        x = height[i]

        while len(lines) > 1 and value(lines[0], x) >= value(lines[1], x):
            lines.popleft()

        dp[i] = x * x + cost + value(lines[0], x)

        candidate = (-2 * x, dp[i] + x * x)

        while len(lines) > 1 and bad(lines[-2], lines[-1], candidate):
            lines.pop()

        lines.append(candidate)

    sys.stdout.write(str(dp[-1]))

if __name__ == "__main__":
    main()
