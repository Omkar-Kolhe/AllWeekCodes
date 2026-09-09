import sys
from collections import deque

data = list(map(int, sys.stdin.buffer.read().split()))
it = iter(data)
t = next(it)
out = []

for _ in range(t):
    n = next(it)
    q = next(it)
    risk = [next(it) for _ in range(n)]

    children = [[] for _ in range(n)]
    for _ in range(n - 1):
        p = next(it)
        v = next(it)
        children[p].append(v)

    log = n.bit_length()
    up = [[0] * n for _ in range(log)]
    mx = [[0] * n for _ in range(log)]
    depth = [0] * n

    mx[0][0] = risk[0]
    queue = deque([0])

    while queue:
        u = queue.popleft()
        for v in children[u]:
            depth[v] = depth[u] + 1
            up[0][v] = u
            mx[0][v] = max(risk[v], risk[u])
            queue.append(v)

    for k in range(1, log):
        for v in range(n):
            mid = up[k - 1][v]
            up[k][v] = up[k - 1][mid]
            mx[k][v] = max(mx[k - 1][v], mx[k - 1][mid])

    for _ in range(q):
        a = next(it)
        b = next(it)
        x, y = a, b
        best = max(risk[a], risk[b])

        if depth[x] < depth[y]:
            x, y = y, x

        diff = depth[x] - depth[y]
        bit = 0
        while diff:
            if diff & 1:
                best = max(best, mx[bit][x])
                x = up[bit][x]
            diff >>= 1
            bit += 1

        if x != y:
            for k in range(log - 1, -1, -1):
                if up[k][x] != up[k][y]:
                    best = max(best, mx[k][x], mx[k][y])
                    x = up[k][x]
                    y = up[k][y]

            best = max(best, risk[x], risk[y], risk[up[0][x]])
            x = up[0][x]

        dist = depth[a] + depth[b] - 2 * depth[x]
        out.append(f"{x} {dist} {best}")

sys.stdout.write("\n".join(out))
