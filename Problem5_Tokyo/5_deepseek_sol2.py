import sys
from collections import defaultdict


def process_test_case(N, turns):
    prefix = 0
    first_occurrence = {0: 0}

    max_len = 0
    max_start = 1

    for i in range(1, N + 1):
        C, X = turns[i - 1]
        if C == 'L':
            prefix += X
        else:
            prefix -= X

        if prefix in first_occurrence:
            start = first_occurrence[prefix] + 1
            length = i - first_occurrence[prefix]

            if length > max_len or (length == max_len and start < max_start):
                max_len = length
                max_start = start
        else:
            first_occurrence[prefix] = i

    if max_len == 0:
        return "0 0 0"
    return f"{max_len} {max_start} {max_start + max_len - 1}"


def solve():
    input_data = sys.stdin.read().strip().split()
    if not input_data:
        return

    idx = 0
    T = int(input_data[idx])
    idx += 1

    results = []

    for _ in range(T):
        N = int(input_data[idx])
        idx += 1

        turns = []
        for _ in range(N):
            C = input_data[idx]
            X = int(input_data[idx + 1])
            idx += 2
            turns.append((C, X))

        results.append(process_test_case(N, turns))

    sys.stdout.write("\n".join(results))


if __name__ == "__main__":
    solve()
