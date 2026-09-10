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

        vector<pair<int, int>> blocks;
        blocks.reserve(N);

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

            blocks.push_back({x, exponent});
        }

        sort(blocks.begin(), blocks.end());

        long long answer = 0;

        for (int i = 0; i < N;)
        {
            int oddPart = blocks[i].first;
            int maxExponent = 0;

            int j = i;

            while (j < N && blocks[j].first == oddPart)
            {
                maxExponent = max(maxExponent, blocks[j].second);
                j++;
            }

            answer += maxExponent;
            i = j;
        }

        cout << answer << '\n';
    }

    return 0;
}