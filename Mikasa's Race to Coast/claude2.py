import sys
import heapq

def solve():
    input_data = sys.stdin.buffer.read().split()
    pos = 0
    T = int(input_data[pos]); pos += 1
    
    results = []
    
    for _ in range(T):
        N = int(input_data[pos]); pos += 1
        D = int(input_data[pos]); pos += 1
        F = int(input_data[pos]); pos += 1
        
        depots = []
        for i in range(N):
            X = int(input_data[pos]); pos += 1
            G = int(input_data[pos]); pos += 1
            depots.append((X, G))
        
        depots.sort(key=lambda p: p[0])
        
        max_heap = []
        current_fuel = F
        current_position = 0
        stop_count = 0
        possible = True
        
        for i in range(N + 1):
            target_position = D if i == N else depots[i][0]
            distance_needed = target_position - current_position
            
            while current_fuel < distance_needed:
                if not max_heap:
                    possible = False
                    break
                current_fuel += -heapq.heappop(max_heap)
                stop_count += 1
            
            if not possible:
                break
            
            current_fuel -= distance_needed
            current_position = target_position
            
            if i < N:
                heapq.heappush(max_heap, -depots[i][1])
        
        results.append(str(stop_count) if possible else "-1")
    
    sys.stdout.write("\n".join(results) + "\n")

if __name__ == "__main__":
    solve()