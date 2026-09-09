import sys

def main():
    raw = sys.stdin.read().split()
    if not raw: return
    
    tc = int(raw[0])
    ptr = 1
    
    res = []
    for _ in range(tc):
        n = int(raw[ptr])
        str_val = raw[ptr+1]
        k_limit = int(raw[ptr+2])
        ptr += 3
        
        cur_w, l_idx, best = 0, 0, 0
        for r_idx in range(n):
            if str_val[r_idx] == 'W':
                cur_w += 1
            while cur_w > k_limit:
                if str_val[l_idx] == 'W':
                    cur_w -= 1
                l_idx += 1
            if r_idx - l_idx + 1 > best:
                best = r_idx - l_idx + 1
        res.append(str(best))
        
    print('\n'.join(res))

if __name__ == '__main__':
    main()