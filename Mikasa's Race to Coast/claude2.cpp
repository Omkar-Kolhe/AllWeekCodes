#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int T;
    cin >> T;
    
    while (T--) {
        int N;
        ll D, F;
        cin >> N >> D >> F;
        
        vector<pair<ll, ll>> depots(N);
        for (int i = 0; i < N; i++) {
            cin >> depots[i].first >> depots[i].second;
        }
        
        sort(depots.begin(), depots.end());
        
        priority_queue<ll> maxHeap;
        ll currentFuel = F;
        ll currentPosition = 0;
        int stopCount = 0;
        bool possible = true;
        
        for (int i = 0; i <= N; i++) {
            ll targetPosition = (i == N) ? D : depots[i].first;
            ll distanceNeeded = targetPosition - currentPosition;
            
            while (currentFuel < distanceNeeded) {
                if (maxHeap.empty()) {
                    possible = false;
                    break;
                }
                currentFuel += maxHeap.top();
                maxHeap.pop();
                stopCount++;
            }
            
            if (!possible) {
                break;
            }
            
            currentFuel -= distanceNeeded;
            currentPosition = targetPosition;
            
            if (i < N) {
                maxHeap.push(depots[i].second);
            }
        }
        
        cout << (possible ? stopCount : -1) << "\n";
    }
    
    return 0;
}