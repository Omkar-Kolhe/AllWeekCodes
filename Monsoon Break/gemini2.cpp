#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

void solve() {
    int N;
    cin >> N;
    string S;
    cin >> S;
    int K;
    cin >> K;

    int max_len = 0;
    int w_count = 0;
    int left = 0;

    for (int right = 0; right < N; ++right) {
        if (S[right] == 'W') {
            w_count++;
        }
        while (w_count > K) {
            if (S[left] == 'W') {
                w_count--;
            }
            left++;
        }
        max_len = max(max_len, right - left + 1);
    }

    cout << max_len << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int T;
    if (cin >> T) {
        while (T--) {
            solve();
        }
    }
    return 0;
}