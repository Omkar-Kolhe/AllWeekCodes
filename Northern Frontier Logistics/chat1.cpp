#include <bits/stdc++.h>
using namespace std;

bool canTransport(const vector<long long>& w, int d, long long capacity) {
    int days = 1;
    long long current = 0;

    for (long long x : w) {
        if (current + x <= capacity) {
            current += x;
        } else {
            days++;
            current = x;
        }
    }

    return days <= d;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int n, d;
        cin >> n >> d;

        vector<long long> w(n);
        long long left = 0;
        long long right = 0;

        for (int i = 0; i < n; i++) {
            cin >> w[i];
            left = max(left, w[i]);
            right += w[i];
        }

        while (left < right) {
            long long mid = left + (right - left) / 2;

            if (canTransport(w, d, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        cout << left << '\n';
    }

    return 0;
}