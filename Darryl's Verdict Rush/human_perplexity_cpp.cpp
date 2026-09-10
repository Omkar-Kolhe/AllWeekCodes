#include <bits/stdc++.h>
using namespace std;

// Function to solve a single test case
void solve() {
    int n;
    cin >> n;

    // Store jobs as (processing_time, deadline) pairs
    vector<pair<long long, long long>> jobs(n);
    for (int i = 0; i < n; i++) {
        cin >> jobs[i].first >> jobs[i].second;
    }

    // Sort jobs by deadline (earliest deadline first)
    sort(jobs.begin(), jobs.end(), [](const auto& a, const auto& b) {
        return a.second < b.second;
    });

    // Min-heap to track when each container becomes free
    // The heap stores the finish time of the last job on each container
    priority_queue<long long, vector<long long>, greater<long long>> containers;

    // Process each job in deadline order
    for (const auto& job : jobs) {
        long long p = job.first;   // processing time
        long long d = job.second;  // deadline

        // Latest time this job can start and still meet deadline
        long long latest_start = d - p;

        // Check if any existing container is free early enough
        if (!containers.empty() && containers.top() <= latest_start) {
            // Reuse the container that finishes earliest
            containers.pop();
            containers.push(latest_start + p);  // Update its finish time
        } else {
            // Need a new container for this job
            containers.push(latest_start + p);
        }
    }

    // The number of containers used is our answer
    cout << containers.size() << "\n";
}

int main() {
    // Fast I/O
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
