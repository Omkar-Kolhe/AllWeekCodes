#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

bool check(int k, const vector<pair<long long, long long>>& v) {
    priority_queue<long long, vector<long long>, greater<long long>> pq;
    for (int i = 0; i < k; i++) {
        pq.push(0);
    }
    for (auto x : v) {
        long long t = pq.top();
        pq.pop();
        if (t + x.second > x.first) {
            return false;
        }
        pq.push(t + x.second);
    }
    return true;
}

void solve() {
    int n;
    cin >> n;
    vector<pair<long long, long long>> v(n);
    for (int i = 0; i < n; i++) {
        cin >> v[i].second >> v[i].first;
    }
    sort(v.begin(), v.end(), [](const pair<long long, long long>& a, const pair<long long, long long>& b) {
        if (a.first == b.first) return a.second > b.second;
        return a.first < b.first;
    });
    
    int l = 1, r = n, ans = n;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (check(mid, v)) {
            ans = mid;
            r = mid - 1;
        } else {
            l = mid + 1;
        }
    }
    cout << ans << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
