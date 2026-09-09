T=int(input())

for _ in range(T):
    N=int(input())
    S=input().strip()
    K=int(input())

    left=0
    work=0
    ans=0

    for right in range(N):
        if S[right]=='W':
            work+=1

        while work>K:
            if S[left]=='W':
                work-=1
            left+=1

        ans=max(ans,right-left+1)

    print(ans)