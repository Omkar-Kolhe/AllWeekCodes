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

        max_k = {}
        for _ in range(N):
            x = int(input_data[idx])
            idx += 1

            # Fast bitwise trailing zero calculation
            k = (x & -x).bit_length() - 1
            odd = x >> k

            if k > max_k.get(odd, -1):
                max_k[odd] = k

        out.append(str(sum(max_k.values())))

    print('\n'.join(out))


if __name__ == '__main__':
    solve()
