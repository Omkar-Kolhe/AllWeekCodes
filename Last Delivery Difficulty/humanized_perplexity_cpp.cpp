#include <bits/stdc++.h>
using namespace std;

// Solve a single test case: maximize number of packages delivered on time
void solve_one_case() {
    int n;
    cin >> n;

    // Each package: (processing_time, deadline)
    vector<pair<long long, long long>> packages(n);
    for (int i = 0; i < n; ++i) {
        cin >> packages[i].first >> packages[i].second;
    }

    // Greedy idea: process packages in order of increasing deadlines
    sort(packages.begin(), packages.end(),
         [](const pair<long long, long long>& a,
            const pair<long long, long long>& b) {
             return a.second < b.second;
         });

    // Max-heap to keep track of processing times of chosen packages
    priority_queue<long long> chosen_times;
    long long total_time = 0;

    for (const auto& pkg : packages) {
        long long p = pkg.first;
        long long d = pkg.second;

        // Tentatively take this package
        total_time += p;
        chosen_times.push(p);

        // If we miss the deadline, drop the heaviest package so far
        if (total_time > d) {
            long long heaviest = chosen_times.top();
            chosen_times.pop();
            total_time -= heaviest;
        }
    }

    // Number of packages we managed to keep
    cout << (int)chosen_times.size() << "\n";
}

int main() {
    // Faster I/O
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;
    while (t--) {
        solve_one_case();
    }

    return 0;
}
