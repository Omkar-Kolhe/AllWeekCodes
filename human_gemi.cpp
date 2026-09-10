#include <iostream>
#include <vector>

using namespace std;

void processTestCase() {
    int numTowers;
    cin >> numTowers;
    
    vector<int> signalStrengths(numTowers);
    for (int i = 0; i < numTowers; ++i) {
        cin >> signalStrengths[i];
    }
    
    vector<int> visibleCounts(numTowers);
    vector<int> strongerTowerIndices;
    
    for (int currentTower = numTowers - 1; currentTower >= 0; --currentTower) {
        while (!strongerTowerIndices.empty() && signalStrengths[strongerTowerIndices.back()] <= signalStrengths[currentTower]) {
            strongerTowerIndices.pop_back();
        }
        
        if (strongerTowerIndices.empty()) {
            visibleCounts[currentTower] = (numTowers - 1) - currentTower;
        } else {
            visibleCounts[currentTower] = strongerTowerIndices.back() - currentTower;
        }
        
        strongerTowerIndices.push_back(currentTower);
    }
    
    for (int i = 0; i < numTowers; ++i) {
        cout << visibleCounts[i] << (i == numTowers - 1 ? "" : " ");
    }
    cout << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int totalTestCases;
    cin >> totalTestCases;
    
    while (totalTestCases--) {
        processTestCase();
    }
    
    return 0;
}