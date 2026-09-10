import sys

def solve():
    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    t = int(input_data[0])
    idx = 1
    out = []
    
    for _ in range(t):
        n = int(input_data[idx])
        idx += 1
        a = [int(x) for x in input_data[idx : idx+n]]
        idx += n
        
        ans = [0] * n
        stack = []
        
        for i in range(n - 1, -1, -1):
            while stack and a[stack[-1]] <= a[i]:
                stack.pop()
            
            if not stack:
                ans[i] = n - 1 - i
            else:
                ans[i] = stack[-1] - i
                
            stack.append(i)
            
        out.append(" ".join(map(str, ans)))
        
    print("\n".join(out))

if __name__ == '__main__':
    solve()