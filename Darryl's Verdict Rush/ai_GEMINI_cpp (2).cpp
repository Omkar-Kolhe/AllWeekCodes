#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int N;
        cin >> N;

        vector<pair<long long, long long>> jobs(N);
        for (auto &job : jobs) {
            cin >> job.first >> job.second;
        }

        sort(jobs.begin(), jobs.end(), [](const auto &a, const auto &b) {
            return a.second < b.second;
        });

        multiset<long long> loads;
        int containers = 0;

        for (auto [p, d] : jobs) {
            auto it = loads.upper_bound(d - p);

            if (it == loads.begin()) {
                loads.insert(p);
                ++containers;
            } else {
                --it;
                long long current = *it;
                loads.erase(it);
                loads.insert(current + p);
            }
        }

        cout << containers << '\n';
    }

    return 0;
}
