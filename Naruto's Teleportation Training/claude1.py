import sys
d=sys.stdin.read().split()
i=0
t=int(d[i]);i+=1
o=[]
for _ in range(t):
    a=int(d[i]);i+=1
    b=int(d[i]);i+=1
    c=int(d[i]);i+=1
    e=int(d[i]);i+=1
    if e<b:
        o.append("-1")
        continue
    k=e-b
    z=a+k
    if z<c:
        o.append("-1")
    else:
        o.append(str(k+z-c))
print('\n'.join(o))