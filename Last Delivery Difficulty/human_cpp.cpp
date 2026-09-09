#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

struct Package {
    long long duration;
    long long deadline;

    bool operator<(const Package& other) const {
        return deadline < other.deadline;
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int testCases;
    if (!(cin >> testCases)) {
        return 0;
    }

    while (testCases--) {
        int n;
        cin >> n;

        vector<Package> packages(n);
        for (int i = 0; i < n; ++i) {
            cin >> packages[i].duration >> packages[i].deadline;
        }

        sort(packages.begin(), packages.end());

        long long currentTime = 0;
        priority_queue<long long> deliveredDurations;

        for (const auto& pkg : packages) {
            currentTime += pkg.duration;
            deliveredDurations.push(pkg.duration);

            if (currentTime > pkg.deadline) {
                currentTime -= deliveredDurations.top();
                deliveredDurations.pop();
            }
        }

        cout << deliveredDurations.size() << "\n";
    }

    return 0;
}
