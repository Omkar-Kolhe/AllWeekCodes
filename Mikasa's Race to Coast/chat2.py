import sys
import heapq

def solve():
    input = sys.stdin.readline
    t = int(input())

    for _ in range(t):
        n, distance, initial_fuel = map(int, input().split())

        stations = []
        for _ in range(n):
            position, fuel = map(int, input().split())
            stations.append((position, fuel))

        stations.sort()

        max_fuel_heap = []
        current_fuel = initial_fuel
        station_index = 0
        stops = 0

        while (
            station_index < n
            and stations[station_index][0] <= current_fuel
        ):
            heapq.heappush(max_fuel_heap, -stations[station_index][1])
            station_index += 1

        possible = True

        while current_fuel < distance:
            if not max_fuel_heap:
                possible = False
                break

            current_fuel += -heapq.heappop(max_fuel_heap)
            stops += 1

            while (
                station_index < n
                and stations[station_index][0] <= current_fuel
            ):
                heapq.heappush(
                    max_fuel_heap,
                    -stations[station_index][1]
                )
                station_index += 1

        print(stops if possible else -1)


if __name__ == "__main__":
    solve()