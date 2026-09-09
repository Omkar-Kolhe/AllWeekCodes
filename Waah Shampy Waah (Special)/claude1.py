import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    t = int(data[idx]); idx += 1
    
    out = []
    for _ in range(t):
        n = int(data[idx]); idx += 1
        m = int(data[idx]); idx += 1
        
        max_size = (m + 1) // 2
        
        if n > max_size:
            out.append("-1")
        else:
            start = m - n + 1
            out.append(" ".join(str(i) for i in range(start, m + 1)))
    
    print("\n".join(out))

main()