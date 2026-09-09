#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

void solve() {
    int n;
    cin >> n;
    vector<pair<long long, long long>> arr(n);
    for (int i = 0; i < n; i++) {
        cin >> arr[i].second >> arr[i].first;
    }
    sort(arr.begin(), arr.end());
    long long cur = 0;
    priority_queue<long long> pq;
    for (int i = 0; i < n; i++) {
        cur += arr[i].second;
        pq.push(arr[i].second);
        if (cur > arr[i].first) {
            cur -= pq.top();
            pq.pop();
        }
    }
    cout << pq.size() << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    if (cin >> t) {
        while (t--) {
            solve();
        }
    }
    return 0;
}
