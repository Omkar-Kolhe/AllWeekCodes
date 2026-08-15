import sys

def solve():
    input = sys.stdin.buffer.readline

    t = int(input())
    answers = []

    for _ in range(t):
        n = int(input())
        a = list(map(int, input().split()))

        mn = min(a)

        count_min = a.count(mn)
        has_non_multiple = any(x % mn != 0 for x in a)

        if count_min == 1 or has_non_multiple:
            answers.append("YES")
        else:
            answers.append("NO")

    sys.stdout.write("\n".join(answers))


if __name__ == "__main__":
    solve()