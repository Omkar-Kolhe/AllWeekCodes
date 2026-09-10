import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    n = int(data[idx]); idx += 1
    out = []
    for _ in range(n):
        P = int(data[idx]); C = int(data[idx+1]); S = int(data[idx+2]); L = int(data[idx+3])
        idx += 4

        produced = L // P
        reads = 0
        if S <= L:
            K = (L - S) // C + 1
            if S // P >= 1:
                reads = K
            else:
                reads = max(0, K - 1)
        out.append(str(produced - reads))

    print("\n".join(out))

if __name__ == "__main__":
    main()