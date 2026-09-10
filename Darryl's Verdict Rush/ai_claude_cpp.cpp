#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

struct Job {
    ll p, d;
};

bool compareByDeadline(const Job &a, const Job &b) {
    return a.d < b.d;
}

int solveTestCase() {
    int n;
    cin >> n;
    vector<Job> jobs(n);
    for (int i = 0; i < n; i++) {
        cin >> jobs[i].p >> jobs[i].d;
    }
    sort(jobs.begin(), jobs.end(), compareByDeadline);
    priority_queue<ll, vector<ll>, greater<ll>> minHeap;
    for (int i = 0; i < n; i++) {
        ll p = jobs[i].p;
        ll d = jobs[i].d;
        if (!minHeap.empty() && minHeap.top() + p <= d) {
            ll cur = minHeap.top();
            minHeap.pop();
            minHeap.push(cur + p);
        } else {
            minHeap.push(p);
        }
    }
    return (int)minHeap.size();
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    cin >> t;
    while (t--) {
        cout << solveTestCase() << "\n";
    }
    return 0;
}
