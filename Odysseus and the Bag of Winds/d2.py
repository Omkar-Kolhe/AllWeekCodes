import sys
from typing import List

def solve_test_case() -> int:
    line = sys.stdin.readline().split()
    M, A = int(line[0]), int(line[1])
    
    cost: List[List[int]] = []
    for i in range(M + 1):
        row = list(map(int, sys.stdin.readline().split()))
        cost.append(row)
    
    INF = 10 ** 15
    full_mask = (1 << M) - 1
    
    dp_no_bag = [[INF] * (M + 1) for _ in range(full_mask + 1)]
    dp_with_bag = [[INF] * (M + 1) for _ in range(full_mask + 1)]
    
    dp_no_bag[1][1] = 0
    
    for mask in range(1, full_mask + 1):
        for current in range(1, M + 1):
            if not ((mask >> (current - 1)) & 1):
                continue
            
            if dp_no_bag[mask][current] < INF:
                for next_node in range(1, M + 1):
                    if (mask >> (next_node - 1)) & 1:
                        continue
                    
                    new_mask = mask | (1 << (next_node - 1))
                    
                    if current == A:
                        cost_with_bag = dp_no_bag[mask][current] + cost[current][next_node] // 2
                        dp_with_bag[new_mask][next_node] = min(
                            dp_with_bag[new_mask][next_node], 
                            cost_with_bag
                        )
                    
                    cost_without_bag = dp_no_bag[mask][current] + cost[current][next_node]
                    dp_no_bag[new_mask][next_node] = min(
                        dp_no_bag[new_mask][next_node],
                        cost_without_bag
                    )
            
            if dp_with_bag[mask][current] < INF:
                for next_node in range(1, M + 1):
                    if (mask >> (next_node - 1)) & 1:
                        continue
                    
                    new_mask = mask | (1 << (next_node - 1))
                    new_cost = dp_with_bag[mask][current] + cost[current][next_node]
                    dp_with_bag[new_mask][next_node] = min(
                        dp_with_bag[new_mask][next_node],
                        new_cost
                    )
    
    return dp_with_bag[full_mask][M]

def main():
    T = int(sys.stdin.readline())
    
    results = []
    for _ in range(T):
        result = solve_test_case()
        results.append(str(result))
    
    print('\n'.join(results))

if __name__ == "__main__":
    main()