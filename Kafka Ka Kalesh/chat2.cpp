#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    while (N--) {
        long long P, C, S, L;
        cin >> P >> C >> S >> L;

        // Number of messages produced by time L
        long long produced = L / P;

        // Number of consumer attempts by time L
        long long attempts = 0;

        if (L >= S) {
            attempts = (L - S) / C + 1;
        }

        // If consumer starts before the first message is produced,
        // its first attempt fails.
        long long successfulReads = attempts;

        if (S < P && attempts > 0) {
            successfulReads--;
        }

        long long lag = produced - successfulReads;

        cout << lag << '\n';
    }

    return 0;
}