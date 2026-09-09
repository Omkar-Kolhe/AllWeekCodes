#include <bits/stdc++.h>
using namespace std;

void solve() {
    int N;
    cin >> N;
    vector<pair<long long, long long>> jobs(N);
    for (int i = 0; i < N; ++i) {
        cin >> jobs[i].first >> jobs[i].second; // P, D
    }

    sort(jobs.begin(), jobs.end(), [](const auto& a, const auto& b) {
        return a.second < b.second; // sort by deadline
    });

    priority_queue<long long> maxHeap; // store processing times
    long long currentTime = 0;

    for (auto &job : jobs) {
        long long P = job.first;
        long long D = job.second;

        currentTime += P;
        maxHeap.push(P);

        if (currentTime > D) {
            // Remove the job with the largest processing time
            long long largest = maxHeap.top();
            maxHeap.pop();
            currentTime -= largest;
        }
    }

    cout << (int)maxHeap.size() << "\n";
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;
    while (T--) {
        solve();
    }
    return 0;
}
