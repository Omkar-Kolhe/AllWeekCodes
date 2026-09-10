#include <iostream>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    while (n--) {
        long long P, C, S, L;
        cin >> P >> C >> S >> L;

        // Producer sends a message every P seconds.
        long long messages = L / P;

        // Count how many times the consumer tries to read.
        long long reads = 0;

        if (L >= S) {
            reads = (L - S) / C + 1;
        }

        /*
         * If the consumer starts before the first message is produced,
         * its first attempt finds an empty buffer.
         *
         * After that, the consumer cannot catch up with the producer
         * because C >= P.
         */
        if (S < P && reads > 0) {
            reads--;
        }

        cout << messages - reads << '\n';
    }

    return 0;
}