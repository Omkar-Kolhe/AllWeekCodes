import sys

def main():
    inp = sys.stdin.read().split()
    if not inp: return
    
    tc = int(inp[0])
    p = 1
    
    ans = []
    for _ in range(tc):
        x1, y1, x2, y2 = map(int, inp[p:p+4])
        p += 4
        
        dy = y2 - y1
        if dy < 0:
            ans.append("-1")
            continue
            
        cur_x = x1 + dy
        if x2 > cur_x:
            ans.append("-1")
        else:
            ans.append(str(dy + (cur_x - x2)))
            
    print('\n'.join(ans))

if __name__ == '__main__':
    main()