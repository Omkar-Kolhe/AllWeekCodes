import sys

def solve_test_case(N, M):
    lower_bound = M // 2 + 1
    available_count = M - lower_bound + 1
    
    if available_count < N:
        return [-1]
    
    result = []
    for i in range(N):
        result.append(lower_bound + i)
    
    return result

def main():
    input_data = sys.stdin.read().split()
    idx = 0
    
    T = int(input_data[idx])
    idx += 1
    
    output = []
    for _ in range(T):
        N = int(input_data[idx])
        M = int(input_data[idx + 1])
        idx += 2
        
        result = solve_test_case(N, M)
        
        if result == [-1]:
            output.append("-1")
        else:
            output.append(" ".join(map(str, result)))
    
    print("\n".join(output))

if __name__ == "__main__":
    main()