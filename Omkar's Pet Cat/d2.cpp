#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int testCases;
    cin >> testCases;

    while (testCases--) {
        int numBlocks;
        cin >> numBlocks;

        vector<long long> values(numBlocks);
        long long minimumValue = LLONG_MAX;

        for (int i = 0; i < numBlocks; ++i) {
            cin >> values[i];
            minimumValue = min(minimumValue, values[i]);
        }

        bool existsNotDivisibleByMin = false;
        int countOfMinimum = 0;

        for (long long v : values) {
            if (v % minimumValue != 0)
                existsNotDivisibleByMin = true;
            if (v == minimumValue)
                countOfMinimum++;
        }

        bool canWin = existsNotDivisibleByMin || (countOfMinimum == 1);

        cout << (canWin ? "YES" : "NO") << '\n';
    }

    return 0;
}