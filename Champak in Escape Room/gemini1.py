import sys

def solve():
    # Read everything at once and split by whitespace
    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    t = int(input_data[0])
    idx = 1
    
    out = []
    
    for _ in range(t):
        n = int(input_data[idx])
        m = int(input_data[idx+1])
        k = int(input_data[idx+2])
        idx += 3
        
        # Slicing the grid rows
        grid = input_data[idx : idx+n]
        idx += n
        
        s = input_data[idx]
        idx += 1
        
        r, c = 0, 0
        
        for move in s:
            nr, nc = r, c
            if move == 'U':
                nr -= 1
            elif move == 'D':
                nr += 1
            elif move == 'L':
                nc -= 1
            elif move == 'R':
                nc += 1
                
            # Boundary check and obstacle check
            if 0 <= nr < n and 0 <= nc < m and grid[nr][nc] != '#':
                r, c = nr, nc
                
        out.append(f"{r + 1} {c + 1}")
        
    print('\n'.join(out))

if __name__ == '__main__':
    solve()