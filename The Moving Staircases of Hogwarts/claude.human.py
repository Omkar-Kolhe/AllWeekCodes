import sys

input = sys.stdin.readline

for _ in range(int(input())):
  n = int(input())
  e = list(map(int, input().split()))
  s, d = map(int, input().split())

  if s == d:
    print(0)
    continue

  prev2 = 0
  prev1 = e[s + 1]

  for i in range(s + 2, d + 1):
    cur = e[i] + min(prev1, prev2)
    prev2, prev1 = prev1, cur

  print(prev1)
