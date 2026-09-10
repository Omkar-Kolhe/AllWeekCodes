import sys

def run():
    tokens = sys.stdin.read().split()
    if not tokens:
        return
    
    t = int(tokens[0])
    out = []
    
    it = iter(tokens[1:])
    for _ in range(t):
        p = int(next(it))
        c = int(next(it))
        s = int(next(it))
        l = int(next(it))
        
        produced = l // p
        consumed = 0
        
        if s <= l:
            attempts = (l - s) // c + 1
            if s < p:
                attempts -= 1  # consumer was too early, 0 messages ready
            consumed = attempts
            
        out.append(str(produced - consumed))
        
    print('\n'.join(out))

if __name__ == '__main__':
    run()