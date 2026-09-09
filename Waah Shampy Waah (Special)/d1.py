def solve():
    N, M = map(int, input().split())
    
    start = M // 2 + 1
    count = M - start + 1
    
    if count < N:
        print(-1)
        return
    
    result = list(range(start, start + N))
    print(*result)

T = int(input())
for _ in range(T):
    solve()