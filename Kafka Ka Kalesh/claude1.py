import sys

def main():
    data = sys.stdin.buffer.read().split()
    n = int(data[0])
    pos = 1
    res = []

    for _ in range(n):
        p = int(data[pos]); c = int(data[pos+1]); s = int(data[pos+2]); l = int(data[pos+3])
        pos += 4

        made = l // p
        got = 0
        if s <= l:
            k = (l - s) // c + 1
            got = k if s // p >= 1 else max(0, k - 1)

        res.append(made - got)

    print('\n'.join(map(str, res)))

main()