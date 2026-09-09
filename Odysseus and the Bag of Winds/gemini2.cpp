#include <iostream>
#include <vector>
#include <algorithm>

static const int INFINITY_COST = 1e9 + 7;

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    int test_cases;
    if (!(std::cin >> test_cases)) {
        return 0;
    }

    while (test_cases--) {
        int m, a;
        std::cin >> m >> a;

        const int aeolus_idx = a - 1;
        const int ithaca_idx = m - 1;

        std::vector<std::vector<int>> c(m, std::vector<int>(m));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                std::cin >> c[i][j];
            }
        }

        const int num_intermediate_masks = 1 << (m - 1);
        std::vector<std::vector<std::vector<int>>> memo(
            num_intermediate_masks,
            std::vector<std::vector<int>>(m, std::vector<int>(2, INFINITY_COST))
        );

        memo[1][0][0] = 0;
        int minimum_total_days = INFINITY_COST;
        const int completed_intermediate_mask = num_intermediate_masks - 1;

        for (int mask = 1; mask < num_intermediate_masks; mask += 2) {
            const bool wind_bag_available = (mask & (1 << aeolus_idx)) != 0;

            for (int u = 0; u < m - 1; ++u) {
                for (int bag_used = 0; bag_used < 2; ++bag_used) {
                    const int current_cost = memo[mask][u][bag_used];
                    if (current_cost >= INFINITY_COST) continue;

                    if (mask == completed_intermediate_mask) {
                        minimum_total_days = std::min(minimum_total_days, current_cost + c[u][ithaca_idx]);
                        if (wind_bag_available && bag_used == 0) {
                            minimum_total_days = std::min(minimum_total_days, current_cost + c[u][ithaca_idx] / 2);
                        }
                    } else {
                        for (int v = 1; v < m - 1; ++v) {
                            if (!(mask & (1 << v))) {
                                const int next_mask = mask | (1 << v);

                                if (current_cost + c[u][v] < memo[next_mask][v][bag_used]) {
                                    memo[next_mask][v][bag_used] = current_cost + c[u][v];
                                }

                                if (wind_bag_available && bag_used == 0) {
                                    const int reduced_cost = current_cost + c[u][v] / 2;
                                    if (reduced_cost < memo[next_mask][v][1]) {
                                        memo[next_mask][v][1] = reduced_cost;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        std::cout << minimum_total_days << "\n";
    }

    return 0;
}