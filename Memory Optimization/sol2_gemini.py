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
        idx += 1

        items = []
        for _ in range(N):
            x = int(input_data[idx])
            idx += 1

            k = (x & -x).bit_length() - 1
            odd = x >> k
            items.append((odd, k))

        items.sort(key=lambda item: item[0])

        ans = 0
        curr_odd = -1
        curr_max = 0

        for odd, k in items:
            if odd != curr_odd:
                ans += curr_max
                curr_odd = odd
                curr_max = k
            else:
                if k > curr_max:
                    curr_max = k

        ans += curr_max
        out.append(str(ans))

    print('\n'.join(out))


if __name__ == '__main__':
    solve()
