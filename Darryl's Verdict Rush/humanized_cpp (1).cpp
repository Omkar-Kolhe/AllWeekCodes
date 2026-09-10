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
        for (auto &[p, d] : jobs) {
            cin >> p >> d;
        }

        sort(jobs.begin(), jobs.end(), [](const auto &a, const auto &b) {
            return a.second < b.second;
        });

        multiset<long long> finishTimes;
        long long answer = 0;

        for (auto [p, d] : jobs) {
            auto it = finishTimes.upper_bound(d - p);

            if (it != finishTimes.begin()) {
                --it;
                long long finish = *it;
                finishTimes.erase(it);
                finishTimes.insert(finish + p);
            } else {
                finishTimes.insert(p);
                ++answer;
            }
        }

        cout << answer << '\n';
    }

    return 0;
}
