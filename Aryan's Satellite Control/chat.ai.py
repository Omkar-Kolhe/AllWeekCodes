import sys
from bisect import bisect_left, bisect_right

def add(bit, index, value):
    index += 1
    while index < len(bit):
        bit[index] += value
        index += index & -index

def prefix_sum(bit, index):
    result = 0
    index += 1
    while index:
        result += bit[index]
        index -= index & -index
    return result

def kth(bit, target):
    idx = 0
    step = 1 << (len(bit).bit_length() - 2)

    while step:
        nxt = idx + step
        if nxt < len(bit) and bit[nxt] < target:
            idx = nxt
            target -= bit[nxt]
        step >>= 1

    return idx

data = list(map(int, sys.stdin.buffer.read().split()))
it = iter(data)
t = next(it)
result = []

for _ in range(t):
    n = next(it)
    k = next(it)

    intervals = []
    ends = [0]

    for _ in range(n):
        left = next(it)
        right = next(it)
        intervals.append((left, right))
        ends.append(right)

    intervals.sort(key=lambda item: item[1])
    ends = sorted(set(ends))

    bit = [0] * (len(ends) + 1)
    add(bit, bisect_left(ends, 0), k)

    answer = 0

    for left, right in intervals:
        last = bisect_right(ends, left) - 1
        if last < 0:
            continue

        available = prefix_sum(bit, last)
        if available == 0:
            continue

        pos = kth(bit, available)
        add(bit, pos, -1)
        add(bit, bisect_left(ends, right), 1)
        answer += 1

    result.append(str(answer))

sys.stdout.write("\n".join(result))
