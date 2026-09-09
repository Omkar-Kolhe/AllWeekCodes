#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int N;
        cin >> N;

        vector<pair<long long, long long>> packages(N);

        for (int i = 0; i < N; ++i) {
            long long P, D;
            cin >> P >> D;
            packages[i] = {P, D};
        }

        sort(packages.begin(), packages.end(),
             [](const pair<long long, long long>& a,
                const pair<long long, long long>& b) {
                 return a.second < b.second;
             });

        priority_queue<long long> maxHeap;
        long long totalTime = 0;

        for (const auto& package : packages) {
            long long processingTime = package.first;
            long long deadline = package.second;

            totalTime += processingTime;
            maxHeap.push(processingTime);

            if (totalTime > deadline) {
                totalTime -= maxHeap.top();
                maxHeap.pop();
            }
        }

        cout << maxHeap.size() << '\n';
    }

    return 0;
}
