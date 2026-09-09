import sys
import heapq

input = sys.stdin.readline

t = int(input())

for _ in range(t):
    n, d, fuel = map(int, input().split())
    depots = [tuple(map(int, input().split())) for _ in range(n)]
    depots.sort()

    heap = []
    i = 0
    stops = 0

    while i < n and depots[i][0] <= fuel:
        heapq.heappush(heap, -depots[i][1])
        i += 1

    while fuel < d:
        if not heap:
            print(-1)
            break

        fuel += -heapq.heappop(heap)
        stops += 1

        while i < n and depots[i][0] <= fuel:
            heapq.heappush(heap, -depots[i][1])
            i += 1
    else:
        print(stops)