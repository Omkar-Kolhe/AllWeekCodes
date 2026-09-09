#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

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

        for (int i = 0; i < N; ++i) {
            cin >> depots[i].first >> depots[i].second;
        }

        sort(depots.begin(), depots.end());

        priority_queue<long long> maxHeap;

        long long fuel = F;
        int index = 0;
        int answer = 0;

        while (index < N && depots[index].first <= fuel) {
            maxHeap.push(depots[index].second);
            ++index;
        }

        while (fuel < D) {
            if (maxHeap.empty()) {
                cout << -1 << '\n';
                goto next_test;
            }

            fuel += maxHeap.top();
            maxHeap.pop();
            ++answer;

            while (index < N && depots[index].first <= fuel) {
                maxHeap.push(depots[index].second);
                ++index;
            }
        }

        cout << answer << '\n';

        next_test:;
    }

    return 0;
}