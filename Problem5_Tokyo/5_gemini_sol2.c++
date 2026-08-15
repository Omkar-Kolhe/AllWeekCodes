// 2_gemini_sol2.cpp
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct PrefixSum
{
    long long val;
    int index;
    bool operator<(const PrefixSum &other) const
    {
        if (val != other.val)
            return val < other.val;
        return index < other.index;
    }
};

void process_test_case()
{
    int n;
    cin >> n;
    vector<PrefixSum> p(n + 1);
    p[0] = {0, 0};

    long long current_sum = 0;
    for (int i = 1; i <= n; ++i)
    {
        char dir;
        long long ang;
        cin >> dir >> ang;
        if (dir == 'L')
        {
            current_sum += ang;
        }
        else
        {
            current_sum -= ang;
        }
        p[i] = {current_sum, i};
    }

    sort(p.begin(), p.end());

    int ans_length = 0;
    int ans_start = 2e9;
    int ans_end = 0;

    for (int i = 0; i <= n;)
    {
        int j = i;
        while (j <= n && p[j].val == p[i].val)
        {
            j++;
        }

        if (j - i > 1)
        {
            int first_pos = p[i].index;
            int last_pos = p[j - 1].index;
            int len = last_pos - first_pos;

            if (len > ans_length)
            {
                ans_length = len;
                ans_start = first_pos + 1;
                ans_end = last_pos;
            }
            else if (len == ans_length)
            {
                if (first_pos + 1 < ans_start)
                {
                    ans_start = first_pos + 1;
                    ans_end = last_pos;
                }
            }
        }
        i = j;
    }

    if (ans_length == 0)
    {
        cout << "0 0 0\n";
    }
    else
    {
        cout << ans_length << " " << ans_start << " " << ans_end << "\n";
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
            process_test_case();
        }
    }
    return 0;
}