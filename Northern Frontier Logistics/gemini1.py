import sys

def solve():
    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    it = iter(input_data)
    num_test_cases = int(next(it))
    results = []
    
    for _ in range(num_test_cases):
        n = int(next(it))
        d = int(next(it))
        weights = [int(next(it)) for _ in range(n)]
        
        low = max(weights)
        high = sum(weights)
        ans = high
        
        while low <= high:
            mid = (low + high) // 2
            days = 1
            curr = 0
            for w in weights:
                if curr + w > mid:
                    days += 1
                    curr = 0
                curr += w
            
            if days <= d:
                ans = mid
                high = mid - 1
            else:
                low = mid + 1
                
        results.append(str(ans))
        
    sys.stdout.write("\n".join(results) + "\n")

if __name__ == '__main__':
    solve()