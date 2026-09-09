import sys

def solve():
    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    n = int(input_data[0])
    c = int(input_data[1])
    h = [int(x) for x in input_data[2:2+n]]

    def is_bad(l1, l2, l3):
        return (l3[1] - l1[1]) * (l1[0] - l2[0]) <= (l2[1] - l1[1]) * (l1[0] - l3[0])

    # Deque storing (m, b)
    dq = []
    
    # dp[0] = 0
    dq.append((-2 * h[0], h[0] * h[0]))
    
    dp_i = 0
    for i in range(1, n):
        x = h[i]
        
        # Pop sub-optimal lines from front
        while len(dq) >= 2:
            y1 = dq[0][0] * x + dq[0][1]
            y2 = dq[1][0] * x + dq[1][1]
            if y1 >= y2:
                dq.pop(0)
            else:
                break
        
        dp_i = dq[0][0] * x + dq[0][1] + x * x + c
        
        new_line = (-2 * x, dp_i + x * x)
        
        # Pop non-convex lines from back
        while len(dq) >= 2 and is_bad(dq[-2], dq[-1], new_line):
            dq.pop()
        
        dq.append(new_line)
        
    print(dp_i)

if __name__ == '__main__':
    solve()