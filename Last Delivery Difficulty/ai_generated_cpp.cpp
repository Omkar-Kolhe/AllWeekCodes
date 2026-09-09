#include <bits/stdc++.h>
using namespace std;

struct Package {
    long long processingTime;
    long long deadline;
};

int solveTestCase(int numberOfPackages, vector<Package>& packages) {
    sort(packages.begin(), packages.end(), [](const Package& a, const Package& b) {
        return a.deadline < b.deadline;
    });

    priority_queue<long long, vector<long long>, less<long long>> maxHeap;
    long long accumulatedTime = 0;

    for (int index = 0; index < numberOfPackages; index++) {
        long long currentProcessingTime = packages[index].processingTime;
        long long currentDeadline = packages[index].deadline;

        maxHeap.push(currentProcessingTime);
        accumulatedTime += currentProcessingTime;

        if (accumulatedTime > currentDeadline) {
            long long largestProcessingTime = maxHeap.top();
            maxHeap.pop();
            accumulatedTime -= largestProcessingTime;
        }
    }

    return static_cast<int>(maxHeap.size());
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int numberOfTestCases;
    cin >> numberOfTestCases;

    vector<int> results;
    results.reserve(numberOfTestCases);

    for (int testCaseIndex = 0; testCaseIndex < numberOfTestCases; testCaseIndex++) {
        int numberOfPackages;
        cin >> numberOfPackages;

        vector<Package> packages(numberOfPackages);
        for (int i = 0; i < numberOfPackages; i++) {
            cin >> packages[i].processingTime >> packages[i].deadline;
        }

        int successfulDeliveries = solveTestCase(numberOfPackages, packages);
        results.push_back(successfulDeliveries);
    }

    for (int result : results) {
        cout << result << "\n";
    }

    return 0;
}
