import sys
from collections import deque

data = list(map(int, sys.stdin.buffer.read().split()))
pos = 0
t = data[pos]
pos += 1
answers = []

for _ in range(t):
  n, q = data[pos], data[pos + 1]
  pos += 2

  risk = data[pos:pos + n]
  pos += n

  graph = [[] for _ in range(n)]
  for _ in range(n - 1):
    parent, child = data[pos], data[pos + 1]
    pos += 2
    graph[parent].append(child)

  levels = n.bit_length()
  parent_table = [[0] * n for _ in range(levels)]
  max_table = [[0] * n for _ in range(levels)]
  depth = [0] * n

  queue = deque([0])
  max_table[0][0] = risk[0]

  while queue:
    node = queue.popleft()
    for child in graph[node]:
      parent_table[0][child] = node
      depth[child] = depth[node] + 1
      max_table[0][child] = max(risk[child], risk[node])
      queue.append(child)

  for level in range(1, levels):
    prev = parent_table[level - 1]
    cur = parent_table[level]
    prev_max = max_table[level - 1]
    cur_max = max_table[level]

    for node in range(n):
      middle = prev[node]
      cur[node] = prev[middle]
      cur_max[node] = max(prev_max[node], prev_max[middle])

  for _ in range(q):
    a, b = data[pos], data[pos + 1]
    pos += 2

    u, v = a, b
    path_max = max(risk[u], risk[v])

    if depth[u] < depth[v]:
      u, v = v, u

    jump = depth[u] - depth[v]

    for level in range(levels):
      if jump & (1 << level):
        path_max = max(path_max, max_table[level][u])
        u = parent_table[level][u]

    if u != v:
      for level in range(levels - 1, -1, -1):
        if parent_table[level][u] != parent_table[level][v]:
          path_max = max(path_max, max_table[level][u], max_table[level][v])
          u = parent_table[level][u]
          v = parent_table[level][v]

      path_max = max(path_max, risk[u], risk[v], risk[parent_table[0][u]])
      u = parent_table[0][u]

    distance = depth[a] + depth[b] - 2 * depth[u]
    answers.append(f"{u} {distance} {path_max}")

sys.stdout.write("\n".join(answers))
