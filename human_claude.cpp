#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;

    while (t--) {
        int n;
        cin >> n;

        vector<int> a(n);
        for (int i = 0; i < n; i++) cin >> a[i];

        vector<int> nextGreater(n, n);
        stack<int> st;

        for (int i = 0; i < n; i++) {
            while (!st.empty() && a[st.top()] < a[i]) {
                nextGreater[st.top()] = i;
                st.pop();
            }
            st.push(i);
        }

        for (int i = 0; i < n; i++) {
            int ans;
            if (nextGreater[i] != n) {
                ans = nextGreater[i] - i;
            } else {
                ans = n - 1 - i;
            }
            cout << ans;
            if (i != n - 1) cout << " ";
        }
        cout << "\n";
    }

    return 0;
}