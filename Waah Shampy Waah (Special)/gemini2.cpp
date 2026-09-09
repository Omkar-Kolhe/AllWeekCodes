#include <iostream>
#include <vector>

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    int t;
    if (!(std::cin >> t)) return 0;

    while (t--) {
        long long n, m;
        std::cin >> n >> m;

        long long max_antichain_size = (m + 1) / 2;
        if (n > max_antichain_size) {
            std::cout << -1 << "\n";
        } else {
            long long start_val = m - n + 1;
            for (long long i = 0; i < n; ++i) {
                std::cout << (start_val + i) << (i + 1 == n ? "" : " ");
            }
            std::cout << "\n";
        }
    }

    return 0;
}