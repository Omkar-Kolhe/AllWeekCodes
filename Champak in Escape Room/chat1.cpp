#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int N, M, K;
        cin >> N >> M >> K;

        vector<string> grid(N);
        for (auto &row : grid)
            cin >> row;

        string S;
        cin >> S;

        int r = 0, c = 0;

        for (char move : S) {
            int nr = r, nc = c;

            if (move == 'U') nr--;
            else if (move == 'D') nr++;
            else if (move == 'L') nc--;
            else nc++;

            if (nr >= 0 && nr < N &&
                nc >= 0 && nc < M &&
                grid[nr][nc] == '.') {
                r = nr;
                c = nc;
            }
        }

        cout << r + 1 << ' ' << c + 1 << '\n';
    }

    return 0;
}