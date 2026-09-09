import sys
import heapq

def main():
    data = sys.stdin.read().split()
    idx = 0
    t = int(data[idx]); idx += 1
    
    out = []
    
    for _ in range(t):
        n = int(data[idx]); idx += 1
        d = int(data[idx]); idx += 1
        f = int(data[idx]); idx += 1
        
        depots = []
        for i in range(n):
            x = int(data[idx]); idx += 1
            g = int(data[idx]); idx += 1
            depots.append((x, g))
        
        depots.sort()
        
        heap = []
        cur_fuel = f
        cur_pos = 0
        stops = 0
        ok = True
        
        for i in range(n + 1):
            target = d if i == n else depots[i][0]
            dist = target - cur_pos
            
            while cur_fuel < dist:
                if not heap:
                    ok = False
                    break
                cur_fuel += -heapq.heappop(heap)
                stops += 1
            
            if not ok:
                break
            
            cur_fuel -= dist
            cur_pos = target
            
            if i < n:
                heapq.heappush(heap, -depots[i][1])
        
        out.append(str(stops) if ok else "-1")
    
    print("\n".join(out))

main()