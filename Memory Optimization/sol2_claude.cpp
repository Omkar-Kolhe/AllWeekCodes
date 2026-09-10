#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;
    while (T--)
    {
        int N;
        cin >> N;
        vector<pair<long long, int>> arr(N);
        for (int i = 0; i < N; i++)
        {
            long long a;
            cin >> a;
            int k = 0;
            while (a % 2 == 0)
            {
                a /= 2;
                k++;
            }
            arr[i] = {a, k};
        }
        sort(arr.begin(), arr.end());
        long long ans = 0;
        int i = 0;
        while (i < N)
        {
            int j = i;
            int mx = arr[i].second;
            while (j < N && arr[j].first == arr[i].first)
            {
                mx = max(mx, arr[j].second);
                j++;
            }
            ans += mx;
            i = j;
        }
        cout << ans << "\n";
    }
    return 0;
}