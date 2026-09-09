#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int N, K;
        string S;

        cin >> N;
        cin >> S;
        cin >> K;

        int left = 0;
        int work = 0;
        int ans = 0;

        for (int right = 0; right < N; right++) {
            if (S[right] == 'W')
                work++;

            while (work > K) {
                if (S[left] == 'W')
                    work--;
                left++;
            }

            ans = max(ans, right - left + 1);
        }

        cout << ans << '\n';
    }

    return 0;
}