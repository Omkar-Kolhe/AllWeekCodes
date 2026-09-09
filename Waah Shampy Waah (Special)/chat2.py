import sys

def solve():
    input = sys.stdin.readline
    t = int(input())

    for _ in range(t):
        n, m = map(int, input().split())

        start = m // 2 + 1
        count = m - start + 1

        if count < n:
            print(-1)
        else:
            result = [str(x) for x in range(start, start + n)]
            print(" ".join(result))

if __name__ == "__main__":
    solve()