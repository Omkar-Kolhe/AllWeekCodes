#include <iostream>
#include <vector>

using namespace std;

void solve() {
    int n;
    cin >> n;
    vector<int> a(n);
    for (int i = 0; i < n; ++i) {
        cin >> a[i];
    }
    vector<int> ans(n);
    vector<int> st;
    for (int i = n - 1; i >= 0; --i) {
        while (!st.empty() && a[st.back()] <= a[i]) {
            st.pop_back();
        }
        if (st.empty()) {
            ans[i] = n - 1 - i;
        } else {
            ans[i] = st.back() - i;
        }
        st.push_back(i);
    }
    for (int i = 0; i < n; ++i) {
        cout << ans[i] << (i == n - 1 ? "" : " ");
    }
    cout << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}