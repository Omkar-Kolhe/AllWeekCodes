import sys

def main():
    data = sys.stdin.buffer.read().split()
    idx = 0
    T = int(data[idx]); idx += 1
    out = []

    for _ in range(T):
        N = int(data[idx]); idx += 1
        A = data[idx:idx+N]
        idx += N
        A = list(map(int, A))

        next_greater = [N] * N
        st = []

        for i in range(N):
            while st and A[st[-1]] < A[i]:
                next_greater[st.pop()] = i
            st.append(i)

        ans = [0] * N
        for i in range(N):
            if next_greater[i] != N:
                ans[i] = next_greater[i] - i
            else:
                ans[i] = N - 1 - i

        out.append(' '.join(map(str, ans)))

    sys.stdout.write('\n'.join(out) + '\n')

if __name__ == '__main__':
    main()