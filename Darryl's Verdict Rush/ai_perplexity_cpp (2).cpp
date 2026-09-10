#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    vector<pair<long long, long long>> jobs(n);
    for (int i = 0; i < n; i++) {
        cin >> jobs[i].first >> jobs[i].second;
    }
    sort(jobs.begin(), jobs.end(), [](const auto& a, const auto& b) {
        return a.second < b.second;
    });
    priority_queue<long long, vector<long long>, greater<long long>> containers;
    for (const auto& job : jobs) {
        long long p = job.first;
        long long d = job.second;
        long long latest_start = d - p;
        if (!containers.empty() && containers.top() <= latest_start) {
            containers.pop();
            containers.push(latest_start + p);
        } else {
            containers.push(latest_start + p);
        }
    }
    cout << containers.size() << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
