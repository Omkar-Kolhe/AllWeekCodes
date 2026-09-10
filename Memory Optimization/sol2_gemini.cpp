#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

void solve()
{
    int n;
    cin >> n;
    vector<pair<int, int>> items(n);
    for (int i = 0; i < n; i++)
    {
        int x;
        cin >> x;
        int k = 0;
        while (x % 2 == 0)
        {
            x /= 2;
            k++;
        }
        items[i] = {x, k};
    }
    sort(items.begin(), items.end());

    long long ans = 0;
    int current_odd = -1;
    int current_max_k = 0;

    for (int i = 0; i < n; i++)
    {
        if (items[i].first != current_odd)
        {
            ans += current_max_k;
            current_odd = items[i].first;
            current_max_k = items[i].second;
        }
        else
        {
            current_max_k = max(current_max_k, items[i].second);
        }
    }
    ans += current_max_k;
    cout << ans << "\n";
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    if (cin >> t)
    {
        while (t--)
            solve();
    }
    return 0;
}