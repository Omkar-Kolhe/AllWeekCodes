#include <iostream>
#include <vector>
using namespace std;

void solveTestCase() {
    int N;
    long long M;
    cin >> N >> M;
    
    long long lowerBound = M / 2 + 1;
    long long availableCount = M - lowerBound + 1;
    
    if (availableCount < N) {
        cout << -1 << endl;
        return;
    }
    
    vector<long long> result;
    for (long long i = 0; i < N; i++) {
        result.push_back(lowerBound + i);
    }
    
    for (int i = 0; i < result.size(); i++) {
        cout << result[i];
        if (i < result.size() - 1) {
            cout << " ";
        }
    }
    cout << endl;
}

int main() {
    int T;
    cin >> T;
    
    while (T > 0) {
        solveTestCase();
        T--;
    }
    
    return 0;
}