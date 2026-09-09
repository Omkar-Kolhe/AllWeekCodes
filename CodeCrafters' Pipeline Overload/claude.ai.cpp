#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int N;
        long long X;
        cin >> N >> X;

        vector<int> a(N);
        long long total = 0;

        for (int i = 0; i < N; i++) {
            cin >> a[i];
            total += a[i];
        }

        if (total == X) {
            cout << "NO\n";
            continue;
        }

        sort(a.begin(), a.end());

        long long current = 0;

        for (int i = 0; i < N - 1; i++) {
            if (current + a[i] == X)
                swap(a[i], a[i + 1]);
            current += a[i];
        }

        cout << "YES\n";
        for (int value : a)
            cout << value << ' ';
        cout << '\n';
    }
}
