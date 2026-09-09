import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    t = int(data[idx]); idx += 1
    results = []

    for _ in range(t):
        a = int(data[idx]); idx += 1
        b = int(data[idx]); idx += 1
        c = int(data[idx]); idx += 1
        d = int(data[idx]); idx += 1

        if d < b:
            results.append("-1")
            continue

        diagonal_moves = d - b
        x_after_diagonal = a + diagonal_moves

        if x_after_diagonal < c:
            results.append("-1")
        else:
            left_moves = x_after_diagonal - c
            results.append(str(diagonal_moves + left_moves))

    print('\n'.join(results))

if __name__ == "__main__":
    main()