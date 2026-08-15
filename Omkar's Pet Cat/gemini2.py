def solve():
    import sys
    input = sys.stdin.read
    data = input().split()
    if not data: return
    t = int(data[0])
    idx = 1
    
    out = []
    for _ in range(t):
        n = int(data[idx])
        a = [int(x) for x in data[idx+1 : idx+1+n]]
        idx += n + 1
        
        mn = min(a)
        if a.count(mn) == 1:
            out.append("YES")
        else:
            if any(x % mn != 0 for x in a):
                out.append("YES")
            else:
                out.append("NO")
                
    print('\n'.join(out))

if __name__ == '__main__':
    solve()