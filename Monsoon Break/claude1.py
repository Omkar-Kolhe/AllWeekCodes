import sys
d=sys.stdin.read().split('\n')
i=0
t=int(d[i]);i+=1
o=[]
for _ in range(t):
    n=int(d[i]);i+=1
    s=d[i].strip();i+=1
    k=int(d[i]);i+=1
    l=0;c=0;b=0
    for r in range(n):
        if s[r]=='W':c+=1
        while c>k:
            if s[l]=='W':c-=1
            l+=1
        if r-l+1>b:b=r-l+1
    o.append(str(b))
print('\n'.join(o))