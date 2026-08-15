import sys

def main():
    data = sys.stdin.read().strip().split()
    if not data:
        return

    pointer = 0
    test_cases = int(data[pointer])
    pointer += 1

    output_lines = []

    for _ in range(test_cases):
        num_crates = int(data[pointer])
        deadline_days = int(data[pointer + 1])
        pointer += 2

        weights = list(map(int, data[pointer:pointer + num_crates]))
        pointer += num_crates

        low = max(weights)
        high = sum(weights)

        while low < high:
            capacity = (low + high) // 2

            days_used = 1
            current_load = 0

            for w in weights:
                if current_load + w > capacity:
                    days_used += 1
                    current_load = 0
                current_load += w

            if days_used <= deadline_days:
                high = capacity
            else:
                low = capacity + 1

        output_lines.append(str(low))

    sys.stdout.write("\n".join(output_lines))

if __name__ == "__main__":
    main()