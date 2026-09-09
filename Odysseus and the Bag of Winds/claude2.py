import sys

def solve():
    input_data = sys.stdin.read().split()
    pos = 0
    T = int(input_data[pos]); pos += 1
    
    INF = float('inf')
    results = []
    
    for _ in range(T):
        M = int(input_data[pos]); pos += 1
        A = int(input_data[pos]); pos += 1
        A_idx = A - 1
        
        C = [[0] * M for _ in range(M)]
        for i in range(M):
            for j in range(M):
                C[i][j] = int(input_data[pos])
                pos += 1
        
        FULL_MASK = 1 << M
        
        dp = [[[INF, INF] for _ in range(M)] for _ in range(FULL_MASK)]
        dp[1][0][0] = 0
        
        for mask in range(1, FULL_MASK):
            if not (mask & 1):
                continue
            
            a_visited = bool(mask & (1 << A_idx))
            
            for last_node in range(M):
                if not (mask & (1 << last_node)):
                    continue
                
                for bag_state in range(2):
                    current_cost = dp[mask][last_node][bag_state]
                    if current_cost == INF:
                        continue
                    
                    for next_node in range(M):
                        if mask & (1 << next_node):
                            continue
                        
                        new_mask = mask | (1 << next_node)
                        
                        normal_cost = current_cost + C[last_node][next_node]
                        if normal_cost < dp[new_mask][next_node][bag_state]:
                            dp[new_mask][next_node][bag_state] = normal_cost
                        
                        if bag_state == 0 and a_visited:
                            halved_cost = current_cost + C[last_node][next_node] // 2
                            if halved_cost < dp[new_mask][next_node][1]:
                                dp[new_mask][next_node][1] = halved_cost
        
        final_mask = FULL_MASK - 1
        answer = min(dp[final_mask][M-1][0], dp[final_mask][M-1][1])
        results.append(str(answer))
    
    print('\n'.join(results))

if __name__ == "__main__":
    solve()