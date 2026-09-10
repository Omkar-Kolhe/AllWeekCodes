#include <bits/stdc++.h>
using namespace std;

vector<int> solve(int N, vector<int>& A) {
    vector<int> ans(N);
    stack<int> st;
    for (int i = N - 1; i >= 0; i--) {
        int count = 0;
        while (!st.empty() && st.top() <= A[i]) {
            st.pop();
            count++;
        }
        if (!st.empty()) {
            count++;
        }
        ans[i] = count;
        st.push(A[i]);
    }
    return ans;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int T;
    cin >> T;
    while (T--) {
        int N;
        cin >> N;
        vector<int> A(N);
        for (int i = 0; i < N; i++) {
            cin >> A[i];
        }
        vector<int> result = solve(N, A);
        for (int i = 0; i < N; i++) {
            cout << result[i] << (i == N - 1 ? "" : " ");
        }
        cout << "\n";
    }
    return 0;
}