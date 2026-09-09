import sys

def main():
    data = sys.stdin.buffer.read().split()
    idx = 0
    T = int(data[idx]); idx += 1
    out = []

    for _ in range(T):
        N = int(data[idx]); M = int(data[idx+1]); K = int(data[idx+2])
        idx += 3

        grid = []
        for i in range(N):
            grid.append(data[idx].decode())
            idx += 1

        S = data[idx].decode()
        idx += 1

        r, c = 0, 0  # 0-indexed internally

        dr = {'U': -1, 'D': 1, 'L': 0, 'R': 0}
        dc = {'U': 0, 'D': 0, 'L': -1, 'R': 1}

        for ch in S:
            nr = r + dr[ch]
            nc = c + dc[ch]
            if 0 <= nr < N and 0 <= nc < M and grid[nr][nc] == '.':
                r, c = nr, nc

        out.append(f"{r+1} {c+1}")

    print("\n".join(out))

main()