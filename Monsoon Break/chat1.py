t=int(input())

while t:
    n=int(input())
    s=input()
    k=int(input())

    l=w=m=0

    for r in range(n):
        if s[r]=='W':
            w+=1

        while w>k:
            if s[l]=='W':
                w-=1
            l+=1

        m=max(m,r-l+1)

    print(m)
    t-=1