import sys

def solve():
    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    t = int(input_data[0])
    idx = 1
    results = []
    
    for _ in range(t):
        n = int(input_data[idx])
        idx += 1
        
        a = [int(x) for x in input_data[idx : idx + n]]
        idx += n
        
        min_val = min(a)
        count = a.count(min_val)
        
        if count == 1:
            results.append("YES")
        else:
            possible = False
            for x in a:
                if x % min_val != 0:
                    possible = True
                    break
            
            if possible:
                results.append("YES")
            else:
                results.append("NO")
                
    sys.stdout.write("\n".join(results) + "\n")

if __name__ == '__main__':
    solve()