import sys

def main():
    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    num_test_cases = int(input_data[0])
    results = []
    
    idx = 1
    for _ in range(num_test_cases):
        P = int(input_data[idx])
        C = int(input_data[idx + 1])
        S = int(input_data[idx + 2])
        L = int(input_data[idx + 3])
        idx += 4
        
        produced = L // P
        consumed = 0
        
        if S <= L:
            attempts = 1 + (L - S) // C
            failed = 1 if S < P else 0
            consumed = attempts - failed
            
        results.append(str(produced - consumed))
        
    sys.stdout.write("\n".join(results) + "\n")

if __name__ == '__main__':
    main()