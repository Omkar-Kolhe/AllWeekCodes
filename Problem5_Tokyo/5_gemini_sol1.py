# 2_gemini_sol1.py
import sys


def solve():
    input_data = sys.stdin.read().split()
    if not input_data:
        return

    t = int(input_data[0])
    idx = 1

    out = []
    for _ in range(t):
        n = int(input_data[idx])
        idx += 1

        prefix = 0
        first_occ = {0: 0}

        max_len = 0
        best_s = 0
        best_e = 0

        for i in range(1, n + 1):
            direction = input_data[idx]
            angle = int(input_data[idx+1])
            idx += 2

            if direction == 'L':
                prefix += angle
            else:
                prefix -= angle

            if prefix in first_occ:
                start_idx = first_occ[prefix]
                length = i - start_idx
                if length > max_len:
                    max_len = length
                    best_s = start_idx + 1
                    best_e = i
            else:
                first_occ[prefix] = i

        if max_len == 0:
            out.append("0 0 0")
        else:
            out.append(f"{max_len} {best_s} {best_e}")

    sys.stdout.write('\n'.join(out) + '\n')


if __name__ == '__main__':
    solve()
