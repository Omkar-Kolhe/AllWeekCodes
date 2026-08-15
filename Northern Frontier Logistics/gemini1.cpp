#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>

using namespace std;

bool canShip(const vector<int>& weights, int d, long long capacity) {
    int days = 1;
    long long current_weight = 0;
    for (int w : weights) {
        if (current_weight + w > capacity) {
            days++;
            current_weight = 0;
        }
        current_weight += w;
    }
    return days <= d;
}

void solve() {
    int n, d;
    if (!(cin >> n >> d)) return;
    vector<int> weights(n);
    long long low = 0, high = 0;
    for (int i = 0; i < n; ++i) {
        cin >> weights[i];
        low = max(low, (long long)weights[i]);
        high += weights[i];
    }
    long long ans = high;
    while (low <= high) {
        long long mid = low + (high - low) / 2;
        if (canShip(weights, d, mid)) {
            ans = mid;
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    cout << ans << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    if (cin >> t) {
        while (t--) solve();
    }
    return 0;
}