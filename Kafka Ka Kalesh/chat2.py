import sys

input = sys.stdin.readline

N = int(input())

for _ in range(N):
    P, C, S, L = map(int, input().split())

    # Messages produced by time L
    produced = L // P

    # Consumer attempts by time L
    if L >= S:
        attempts = (L - S) // C + 1
    else:
        attempts = 0

    # First attempt fails if it happens before
    # the first message is produced.
    successful_reads = attempts

    if S < P and attempts > 0:
        successful_reads -= 1

    lag = produced - successful_reads

    print(lag)