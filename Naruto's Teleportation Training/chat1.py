t=int(input())

while t:
    a,b,c,d=map(int,input().split())

    if d<b:
        print(-1)
    else:
        k=d-b
        z=a+k

        if z<c:
            print(-1)
        else:
            print(k+z-c)

    t-=1