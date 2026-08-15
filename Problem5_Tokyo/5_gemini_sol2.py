# 2_gemini_sol2.py
import sys


def process():
    data = sys.stdin.read().split()
    if not data:
        return

    T = int(data[0])
    ptr = 1

    results = []
    for _ in range(T):
        N = int(data[ptr])
        ptr += 1

        prefix_arr = [(0, 0)]
        curr_sum = 0

        for i in range(1, N + 1):
            d = data[ptr]
            a = int(data[ptr+1])
            ptr += 2

            if d == 'L':
                curr_sum += a
            else:
                curr_sum -= a

            prefix_arr.append((curr_sum, i))

        # Group identical sums by sorting them
        prefix_arr.sort(key=lambda x: (x[0], x[1]))

        ans_len = 0
        ans_start = float('inf')
        ans_end = 0

        i = 0
        while i <= N:
            j = i
            while j <= N and prefix_arr[j][0] == prefix_arr[i][0]:
                j += 1

            if j - i > 1:
                first_i = prefix_arr[i][1]
                last_i = prefix_arr[j - 1][1]
                cur_len = last_i - first_i

                if cur_len > ans_len:
                    ans_len = cur_len
                    ans_start = first_i + 1
                    ans_end = last_i
                elif cur_len == ans_len:
                    if first_i + 1 < ans_start:
                        ans_start = first_i + 1
                        ans_end = last_i
            i = j

        if ans_len == 0:
            results.append("0 0 0")
        else:
            results.append(f"{ans_len} {ans_start} {ans_end}")

    sys.stdout.write('\n'.join(results) + '\n')


if __name__ == '__main__':
    process()
