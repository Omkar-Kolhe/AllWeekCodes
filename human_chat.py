t = int(input())

while t > 0:
    n = int(input())
    a = list(map(int, input().split()))

    ans = [0] * n
    st = []

    for i in range(n - 1, -1, -1):
        while st and a[st[-1]] <= a[i]:
            st.pop()

        if not st:
            ans[i] = n - i - 1
        else:
            ans[i] = st[-1] - i

        st.append(i)

    print(*ans)

    t -= 1