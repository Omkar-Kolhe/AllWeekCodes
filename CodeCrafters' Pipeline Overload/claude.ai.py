import sys

data = list(map(int, sys.stdin.buffer.read().split()))
it = iter(data)
t = next(it)
out = []

for _ in range(t):
  n = next(it)
  x = next(it)
  a = [next(it) for _ in range(n)]

  if sum(a) == x:
    out.append("NO")
    continue

  a.sort()
  prefix = 0

  for i in range(n):
    if prefix + a[i] == x:
      if i + 1 < n:
        a[i], a[i + 1] = a[i + 1], a[i]
    prefix += a[i]

  out.append("YES")
  out.append(" ".join(map(str, a)))

sys.stdout.write("\n".join(out))
