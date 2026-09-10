import sys

def solve():
    input_data = sys.stdin.read().split()
    pos = 0

    t = int(input_data[pos]); pos += 1
    results = []

    for _ in range(t):
        n = int(input_data[pos]); pos += 1
        a = input_data[pos:pos + n]
        pos += n
        a = [int(x) for x in a]

        next_greater = [n] * n
        stack = []

        for i in range(n):
            while stack and a[stack[-1]] < a[i]:
                j = stack.pop()
                next_greater[j] = i
            stack.append(i)

        ans = []
        for i in range(n):
            if next_greater[i] != n:
                ans.append(next_greater[i] - i)
            else:
                ans.append(n - 1 - i)

        results.append(" ".join(map(str, ans)))

    print("\n".join(results))

solve()