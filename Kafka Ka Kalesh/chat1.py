n = int(input())

for _ in range(n):
    P, C, S, L = map(int, input().split())

    # Number of messages produced by time L
    messages = L // P

    # Number of times the consumer tries to read
    reads = 0

    if L >= S:
        reads = (L - S) // C + 1

    # The first read fails if it happens before
    # the first message is produced.
    if S < P and reads > 0:
        reads -= 1

    # Messages that are still unread
    lag = messages - reads

    print(lag)