#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int n;
        cin >> n;

        vector<pair<long long, long long>> jobs(n);

        for (int i = 0; i < n; i++) {
            cin >> jobs[i].first >> jobs[i].second;
        }

        sort(jobs.begin(), jobs.end(),
             [](const auto& a, const auto& b) {
                 return a.second < b.second;
             });

        priority_queue<long long> longest;
        long long currentTime = 0;

        for (auto [time, deadline] : jobs) {
            currentTime += time;
            longest.push(time);

            if (currentTime > deadline) {
                currentTime -= longest.top();
                longest.pop();
            }
        }

        cout << longest.size() << '\n';
    }

    return 0;
}
