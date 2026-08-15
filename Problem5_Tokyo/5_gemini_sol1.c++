// 2_gemini_sol1.cpp
#include <iostream>
#include <vector>
#include <map>

using namespace std;

void solve()
{
    int n;
    if (!(cin >> n))
        return;

    map<long long, int> first_occ;
    first_occ[0] = 0;

    long long prefix = 0;
    int max_len = 0;
    int best_s = 0;
    int best_e = 0;

    for (int i = 1; i <= n; ++i)
    {
        char c;
        long long x;
        cin >> c >> x;

        if (c == 'L')
        {
            prefix += x;
        }
        else
        {
            prefix -= x;
        }

        if (first_occ.find(prefix) != first_occ.end())
        {
            int start_idx = first_occ[prefix];
            int current_len = i - start_idx;

            if (current_len > max_len)
            {
                max_len = current_len;
                best_s = start_idx + 1;
                best_e = i;
            }
        }
        else
        {
            first_occ[prefix] = i;
        }
    }

    if (max_len == 0)
    {
        cout << "0 0 0\n";
    }
    else
    {
        cout << max_len << " " << best_s << " " << best_e << "\n";
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    if (cin >> t)
    {
        while (t--)
        {
            solve();
        }
    }
    return 0;
}