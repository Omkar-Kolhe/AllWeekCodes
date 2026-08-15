import sys

def main():
    data = sys.stdin.read().strip().split()
    if not data:
        return

    pointer = 0
    test_cases = int(data[pointer])
    pointer += 1

    output = []

    for _ in range(test_cases):
        num_blocks = int(data[pointer])
        pointer += 1

        values = list(map(int, data[pointer:pointer + num_blocks]))
        pointer += num_blocks

        minimum = min(values)

        has_non_multiple = False
        min_count = 0

        for v in values:
            if v % minimum != 0:
                has_non_multiple = True
            if v == minimum:
                min_count += 1

        possible = has_non_multiple or (min_count == 1)

        output.append("YES" if possible else "NO")

    sys.stdout.write("\n".join(output))

if __name__ == "__main__":
    main()