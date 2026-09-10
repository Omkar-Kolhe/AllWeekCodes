#include <bits/stdc++.h>
using namespace std;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;
    while (N--) {
        long long P, C, S, L;
        cin >> P >> C >> S >> L;

        long long produced = L / P;

        long long reads = 0;
        if (S <= L) {
            long long K = (L - S) / C + 1;
            if (S / P >= 1) {
                reads = K;
            } else {
                reads = max(0LL, K - 1);
            }
        }

        cout << (produced - reads) << "\n";
    }
    return 0;
}