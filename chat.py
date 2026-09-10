t = int(input())

for _ in range(t):
    n = int(input())
    a = list(map(int, input().split()))

    ans = [0] * n
    stack = []

    for i in range(n - 1, -1, -1):

        while stack and a[stack[-1]] <= a[i]:
            stack.pop()

        if not stack:
            ans[i] = n - 1 - i
        else:
            ans[i] = stack[-1] - i

        stack.append(i)

    print(*ans)