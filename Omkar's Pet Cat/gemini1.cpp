#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

void solve() {
    int n;
    cin >> n;
    vector<int> a(n);
    int min_val = 2e9 + 7;
    for (int i = 0; i < n; ++i) {
        cin >> a[i];
        min_val = min(min_val, a[i]);
    }
    
    int count = 0;
    for (int i = 0; i < n; ++i) {
        if (a[i] == min_val) {
            count++;
        }
    }
    
    if (count == 1) {
        cout << "YES\n";
        return;
    }
    
    for (int i = 0; i < n; ++i) {
        if (a[i] % min_val != 0) {
            cout << "YES\n";
            return;
        }
    }
    cout << "NO\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    if (cin >> t) {
        while (t--) {
            solve();
        }
    }
    return 0;
}