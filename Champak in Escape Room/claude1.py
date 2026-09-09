import sys

def main():
    data = sys.stdin.buffer.read().split()
    idx = 0
    t = int(data[idx]); idx += 1
    out = []

    for _ in range(t):
        n, m, k = int(data[idx]), int(data[idx+1]), int(data[idx+2])
        idx += 3
        grid = []
        for i in range(n):
            grid.append(data[idx].decode())
            idx += 1
        s = data[idx].decode()
        idx += 1

        r, c = 1, 1
        for i in range(k):
            ch = s[i]
            nr, nc = r, c
            if ch == 'U': nr -= 1
            elif ch == 'D': nr += 1
            elif ch == 'L': nc -= 1
            else: nc += 1

            if 1 <= nr <= n and 1 <= nc <= m and grid[nr-1][nc-1] == '.':
                r, c = nr, nc

        out.append(f"{r} {c}")

    print("\n".join(out))

main()