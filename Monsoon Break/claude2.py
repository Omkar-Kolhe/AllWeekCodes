import sys

def main():
    input_data = sys.stdin.read().split('\n')
    idx = 0
    t = int(input_data[idx]); idx += 1
    results = []

    for _ in range(t):
        n = int(input_data[idx]); idx += 1
        s = input_data[idx].strip(); idx += 1
        k = int(input_data[idx]); idx += 1

        left = 0
        w_count = 0
        best = 0
        for right in range(n):
            if s[right] == 'W':
                w_count += 1
            while w_count > k:
                if s[left] == 'W':
                    w_count -= 1
                left += 1
            best = max(best, right - left + 1)

        results.append(str(best))

    print('\n'.join(results))

if __name__ == "__main__":
    main()