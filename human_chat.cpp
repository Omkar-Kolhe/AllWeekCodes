#include <bits/stdc++.h>
using namespace std;

int main() {
    int t;
    cin >> t;

    while (t--) {
        int n;
        cin >> n;

        vector<int> a(n);
        vector<int> ans(n);

        for (int i = 0; i < n; i++) {
            cin >> a[i];
        }

        stack<int> st;

        for (int i = n - 1; i >= 0; i--) {
            while (!st.empty() && a[st.top()] <= a[i]) {
                st.pop();
            }

            if (st.empty()) {
                ans[i] = n - i - 1;
            } else {
                ans[i] = st.top() - i;
            }

            st.push(i);
        }

        for (int i = 0; i < n; i++) {
            cout << ans[i] << " ";
        }

        cout << endl;
    }

    return 0;
}