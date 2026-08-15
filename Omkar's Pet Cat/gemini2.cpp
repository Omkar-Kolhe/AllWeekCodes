#include <bits/stdc++.h>
using namespace std;

int main() {
    cin.tie(0)->sync_with_stdio(0);
    int t;
    if (!(cin >> t)) return 0;
    while (t--) {
        int n; 
        cin >> n;
        vector<int> a(n);
        int mn = 2e9;
        for (int& x : a) {
            cin >> x;
            mn = min(mn, x);
        }
        
        int c = count(a.begin(), a.end(), mn);
        if (c == 1) {
            cout << "YES\n";
            continue;
        }
        
        bool ok = false;
        for (int x : a) {
            if (x % mn != 0) {
                ok = true;
                break;
            }
        }
        cout << (ok ? "YES\n" : "NO\n");
    }
    return 0;
}