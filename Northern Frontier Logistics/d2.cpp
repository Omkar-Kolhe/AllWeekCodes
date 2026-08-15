#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int testCases;
    cin >> testCases;

    while (testCases--) {
        int numCrates, deadlineDays;
        cin >> numCrates >> deadlineDays;

        vector<int> crateWeights(numCrates);
        int lowerBound = 0;
        int upperBound = 0;

        for (int i = 0; i < numCrates; ++i) {
            cin >> crateWeights[i];
            lowerBound = max(lowerBound, crateWeights[i]);
            upperBound += crateWeights[i];
        }

        while (lowerBound < upperBound) {
            int candidateCapacity = lowerBound + (upperBound - lowerBound) / 2;

            int daysUsed = 1;
            int currentDayLoad = 0;

            for (int weight : crateWeights) {
                if (currentDayLoad + weight > candidateCapacity) {
                    ++daysUsed;
                    currentDayLoad = 0;
                }
                currentDayLoad += weight;
            }

            if (daysUsed <= deadlineDays) {
                upperBound = candidateCapacity;
            } else {
                lowerBound = candidateCapacity + 1;
            }
        }

        cout << lowerBound << '\n';
    }

    return 0;
}