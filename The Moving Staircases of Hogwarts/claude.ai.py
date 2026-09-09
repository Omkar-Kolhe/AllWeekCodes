import sys

data = list(map(int, sys.stdin.buffer.read().split()))
it = iter(data)
t = next(it)
out = []

for _ in range(t):
  n = next(it)
  energy = [next(it) for _ in range(n)]
  start = next(it)
  dest = next(it)

  if start == dest:
    out.append("0")
    continue

  dp = [0] * (dest - start + 1)
  dp[1] = energy[start + 1]

  for pos in range(2, len(dp)):
    floor = start + pos
    dp[pos] = energy[floor] + min(dp[pos - 1], dp[pos - 2])

  out.append(str(dp[-1]))

sys.stdout.write("\n".join(out))
