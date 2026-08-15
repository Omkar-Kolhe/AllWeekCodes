import sys


def find_best(n, turns):
    prefix = 0
    earliest = {0: 0}

    result = (0, 0, 0)

    for index, (direction, angle) in enumerate(turns, 1):
        if direction == "L":
            prefix += angle
        else:
            prefix -= angle

        old = earliest.get(prefix)

        if old is None:
            earliest[prefix] = index
            continue

        length = index - old
        start = old + 1

        best_len, best_start, _ = result

        if length > best_len or (
            length == best_len and
            (best_start == 0 or start < best_start)
        ):
            result = (length, start, index)

    return result


def main():
    data = sys.stdin.buffer
    t = int(data.readline())

    output = []

    for _ in range(t):
        n = int(data.readline())
        turns = []

        for _ in range(n):
            parts = data.readline().split()
            turns.append((parts[0], int(parts[1])))

        length, start, end = find_best(n, turns)
        output.append(f"{length} {start} {end}")

    sys.stdout.write("\n".join(output))


if __name__ == "__main__":
    main()
