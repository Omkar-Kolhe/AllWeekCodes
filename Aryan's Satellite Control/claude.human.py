import sys
from bisect import bisect_left, bisect_right

class Fenwick:
  def __init__(self, n):
    self.n = n
    self.bit = [0] * (n + 1)

  def add(self, i, value):
    i += 1
    while i <= self.n:
      self.bit[i] += value
      i += i & -i

  def sum(self, i):
    total = 0
    i += 1
    while i:
      total += self.bit[i]
      i -= i & -i
    return total

  def kth(self, k):
    idx = 0
    step = 1 << (self.n.bit_length() - 1)
    while step:
      nxt = idx + step
      if nxt <= self.n and self.bit[nxt] < k:
        idx = nxt
        k -= self.bit[nxt]
      step >>= 1
    return idx

input = sys.stdin.readline

for _ in range(int(input())):
  n, k = map(int, input().split())
  jobs = [tuple(map(int, input().split())) for _ in range(n)]

  jobs.sort(key=lambda x: x[1])
  ends = sorted(set([0] + [r for _, r in jobs]))

  fw = Fenwick(len(ends))
  fw.add(bisect_left(ends, 0), k)

  ans = 0

  for left, right in jobs:
    idx = bisect_right(ends, left) - 1
    if idx < 0 or fw.sum(idx) == 0:
      continue

    count = fw.sum(idx)
    chosen = fw.kth(count)
    fw.add(chosen, -1)
    fw.add(bisect_left(ends, right), 1)
    ans += 1

  print(ans)
