#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--)
    {
        int N;
        cin >> N;

        unordered_map<int, int> maxExponent;

        for (int i = 0; i < N; i++)
        {
            int x;
            cin >> x;

            int exponent = 0;

            while (x % 2 == 0)
            {
                x /= 2;
                exponent++;
            }

            maxExponent[x] = max(maxExponent[x], exponent);
        }

        long long answer = 0;

        for (auto &[oddPart, exponent] : maxExponent)
        {
            answer += exponent;
        }

        cout << answer << '\n';
    }

    return 0;
}