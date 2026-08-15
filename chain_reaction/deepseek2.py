import sys

def solve():
    input_data = sys.stdin.readline
    testCases = int(input_data())
    
    for _ in range(testCases):
        poleCount = int(input_data())
        powerValues = list(map(int, input_data().split()))
        
        manualRepairs = 1
        
        for i in range(1, poleCount):
            if powerValues[i] >= powerValues[i - 1]:
                manualRepairs += 1
        
        print(manualRepairs)

if __name__ == "__main__":
    solve()