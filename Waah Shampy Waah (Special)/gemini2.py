import sys

def solve() -> None:
    input_tokens = sys.stdin.read().split()
    if not input_tokens:
        return

    t = int(input_tokens[0])
    results = []
    pointer = 1

    for _ in range(t):
        n = int(input_tokens[pointer])
        m = int(input_tokens[pointer + 1])
        pointer += 2

        max_capacity = (m + 1) // 2
        if n > max_capacity:
            results.append("-1")
        else:
            start_element = m - n + 1
            squad = " ".join(str(val) for val in range(start_element, start_element + n))
            results.append(squad)

    sys.stdout.write("\n".join(results) + "\n")

if __name__ == "__main__":
    solve()