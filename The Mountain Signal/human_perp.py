import sys

def solve(N, A):
    ans = [0] * N
    stack = []
    for i in range(N - 1, -1, -1):
        count = 0
        while stack and stack[-1] <= A[i]:
            stack.pop()
            count += 1
        if stack:
            count += 1
        ans[i] = count
        stack.append(A[i])
    return ans

def main():
    input = sys.stdin.read
    data = input().split()
    idx = 0
    T = int(data[idx])
    idx += 1
    out = []
    for _ in range(T):
        N = int(data[idx])
        idx += 1
        A = [int(data[idx + i]) for i in range(N)]
        idx += N
        result = solve(N, A)
        out.append(' '.join(map(str, result)))
    print('\n'.join(out))

if __name__ == "__main__":
    main()