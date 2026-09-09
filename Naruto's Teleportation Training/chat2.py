t=int(input())

for _ in range(t):
    a,b,c,d=map(int,input().split())

    if d<b:
        print(-1)
        continue

    diagonal=d-b
    x=a+diagonal

    if x<c:
        print(-1)
    else:
        print(diagonal+(x-c))