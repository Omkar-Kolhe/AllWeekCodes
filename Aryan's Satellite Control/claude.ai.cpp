#include <bits/stdc++.h>
using namespace std;

struct FenwickTree {
    int size;
    vector<int> tree;

    explicit FenwickTree(int n) : size(n), tree(n + 1, 0) {}

    void update(int index, int delta) {
        for (i++ndex; index <= size; index += index & -index)
            tree[index] += delta;
    }

    int query(int index) const {
        int result = 0;
        for (i++ndex; index > 0; index -= index & -index)
            result += tree[index];
        return result;
    }

    int findByOrder(int order) const {
        int index = 0;
        int step = 1;
        while ((step << 1) <= size) step <<= 1;

        for (; step; step >>= 1) {
            int next = index + step;
            if (next <= size && tree[next] < order) {
                index = next;
                order -= tree[next];
            }
        }

        return index;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int N, K;
        cin >> N >> K;

        vector<pair<long long, long long>> intervals(N);
        vector<long long> ends(N);

        for (int i = 0; i < N; i++) {
            cin >> intervals[i].first >> intervals[i].second;
            ends[i] = intervals[i].second;
        }

        sort(intervals.begin(), intervals.end(),
             [](const auto& a, const auto& b) {
                 if (a.second != b.second) return a.second < b.second;
                 return a.first < b.first;
             });

        ends.push_back(0);
        sort(ends.begin(), ends.end());
        ends.erase(unique(ends.begin(), ends.end()), ends.end());

        FenwickTree fw((int)ends.size());
        int zeroIndex = lower_bound(ends.begin(), ends.end(), 0) - ends.begin();

        if (zeroIndex < (int)ends.size())
            fw.update(zeroIndex, K);

        int answer = 0;

        for (const auto& [left, right] : intervals) {
            int last = upper_bound(ends.begin(), ends.end(), left) - ends.begin() - 1;
            if (last < 0) continue;

            int count = fw.query(last);
            if (count == 0) continue;

            int chosen = fw.findByOrder(count);
            fw.update(chosen, -1);

            int finishIndex = lower_bound(ends.begin(), ends.end(), right) - ends.begin();
            fw.update(finishIndex, 1);

            ++answer;
        }

        cout << answer << '\n';
    }
}
