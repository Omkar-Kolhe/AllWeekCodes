#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int N;
        long long D, F;
        cin >> N >> D >> F;

        vector<pair<long long, long long>> depots(N);
        for (auto &[x, fuel] : depots)
            cin >> x >> fuel;

        sort(depots.begin(), depots.end());

        priority_queue<long long> available;
        long long currentFuel = F;
        int stops = 0;
        int i = 0;

        while (i < N && currentFuel >= depots[i].first) {
            available.push(depots[i].second);
            ++i;
        }

        while (currentFuel < D) {
            if (available.empty()) {
                break;
            }

            currentFuel += available.top();
            available.pop();
            ++stops;

            while (i < N && currentFuel >= depots[i].first) {
                available.push(depots[i].second);
                ++i;
            }
        }

        if (currentFuel >= D)
            cout << stops << '\n';
        else
            cout << -1 << '\n';
    }

    return 0;
}