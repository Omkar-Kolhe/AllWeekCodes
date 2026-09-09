import sys

def solve():
    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    T = int(input_data[0])
    idx = 1
    out = []
    
    for _ in range(T):
        N = int(input_data[idx])
        M = int(input_data[idx+1])
        K = int(input_data[idx+2])
        idx += 3
        
        grid = input_data[idx : idx+N]
        idx += N
        
        S = input_data[idx]
        idx += 1
        
        r, c = 0, 0
        for move in S:
            nr, nc = r, c
            if move == 'U':
                nr -= 1
            elif move == 'D':
                nr += 1
            elif move == 'L':
                nc -= 1
            elif move == 'R':
                nc += 1
                
            if 0 <= nr < N and 0 <= nc < M and grid[nr][nc] == '.':
                r, c = nr, nc
                
        out.append(f"{r + 1} {c + 1}")
        
    print('\n'.join(out))

if __name__ == '__main__':
    solve()