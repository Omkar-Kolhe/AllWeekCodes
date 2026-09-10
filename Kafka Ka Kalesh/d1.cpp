#include <bits/stdc++.h>
using namespace std;

/*
 * Problem: Kafka Ka Kalesh
 * 
 * Producer writes 1 message every P seconds, starting at t = P.
 * Consumer tries to read 1 message every C seconds, starting at t = S.
 * Consumer can only read if there is at least one message in the buffer.
 * If a write and read happen at the same time, write happens first.
 * Given C >= P, find number of unread messages at time L.
 *
 * Approach:
 *  - Total messages produced by time L: floor(L / P)
 *  - Consumer attempt times: S, S+C, S+2C, ... <= L
 *    Number of attempts = 0 if S > L, else floor((L - S) / C) + 1
 *  - Any attempt before the first message (t < P) fails.
 *    Since C >= P, at most the very first attempt can fail.
 *  - So:
 *      if S < P and S <= L: first attempt fails, others succeed
 *      else: all attempts succeed
 *  - Lag = produced - successful_attempts
 */

void solve() {
    long long P, C, S, L;
    cin >> P >> C >> S >> L;

    // Total messages produced by time L
    long long produced = L / P;

    // Count consumer attempts in [0, L]
    long long attempts = 0;
    if (S <= L) {
        attempts = (L - S) / C + 1;
    }

    // Determine how many of these attempts actually succeed
    long long successful = attempts;

    // If the consumer starts before the first message and has at least one attempt,
    // then the first attempt fails.
    if (S < P && S <= L) {
        successful = max(0LL, attempts - 1);
    }

    // Unread messages = produced - successfully consumed
    long long lag = produced - successful;
    cout << lag << "\n";
}

int main() {
    // Fast I/O
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;
    while (N--) {
        solve();
    }
    return 0;
}