#include <iostream>
#include <unordered_map>

using namespace std;

void solve()
{
    int n;
    cin >> n;
    unordered_map<int, int> max_k;
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
        if (k > max_k[x])
        {
            max_k[x] = k;
        }
    }
    long long ans = 0;
    for (auto const &[odd_part, k] : max_k)
    {
        ans += k;
    }
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