import sys

"""
Problem: Kafka Ka Kalesh

Producer:
  - Writes 1 message every P seconds.
  - First message at t = P, then 2P, 3P, ...

Consumer:
  - Tries to read 1 message every C seconds.
  - First attempt at t = S, then S+C, S+2C, ...
  - Can only read if buffer >= 1; otherwise skips that attempt.
  - If write and read happen at same time, write happens first.

Given C >= P, find number of unread messages at time L.

Approach:
  - produced = floor(L / P)
  - attempts = number of k >= 0 such that S + k*C <= L
             = 0 if S > L, else floor((L - S) / C) + 1
  - Because C >= P, at most the first attempt can fail.
    It fails only if S < P and S <= L.
  - successful = attempts, or attempts - 1 in that case.
  - lag = produced - successful
"""

def solve():
    data = sys.stdin.read().strip().split()
    if not data:
        return

    it = iter(data)
    N = int(next(it))
    out = []

    for _ in range(N):
        P = int(next(it))
        C = int(next(it))
        S = int(next(it))
        L = int(next(it))

        # Total messages produced by time L
        produced = L // P

        # Number of consumer attempts in [0, L]
        attempts = 0
        if S <= L:
            attempts = (L - S) // C + 1

        # Determine how many attempts actually succeed
        successful = attempts

        # If consumer starts before first message and has at least one attempt,
        # the first attempt fails.
        if S < P and S <= L:
            successful = max(0, attempts - 1)

        lag = produced - successful
        out.append(str(lag))

    sys.stdout.write("\n".join(out) + "\n")

if __name__ == "__main__":
    solve()