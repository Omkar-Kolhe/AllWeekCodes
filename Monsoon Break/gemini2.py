import sys

def solve():
    input = sys.stdin.read
    data = input().split()
    
    if not data:
        return
        
    T = int(data[0])
    idx = 1
    
    out = []
    for _ in range(T):
        N = int(data[idx])
        S = data[idx+1]
        K = int(data[idx+2])
        idx += 3
        
        max_len = 0
        w_count = 0
        left = 0
        
        for right in range(N):
            if S[right] == 'W':
                w_count += 1
            
            while w_count > K:
                if S[left] == 'W':
                    w_count -= 1
                left += 1
                
            max_len = max(max_len, right - left + 1)
            
        out.append(str(max_len))
        
    print('\n'.join(out))

if __name__ == '__main__':
    solve()