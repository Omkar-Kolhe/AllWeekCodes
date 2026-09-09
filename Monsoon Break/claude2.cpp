#include <bits/stdc++.h>
using namespace std;

int main() {
    int t;
    scanf("%d", &t);
    while (t--) {
        int n;
        scanf("%d", &n);
        static char s[200005];
        scanf("%s", s);
        int k;
        scanf("%d", &k);

        int left = 0, wCount = 0, best = 0;
        for (int right = 0; right < n; right++) {
            if (s[right] == 'W') wCount++;
            while (wCount > k) {
                if (s[left] == 'W') wCount--;
                left++;
            }
            best = max(best, right - left + 1);
        }
        printf("%d\n", best);
    }
    return 0;
}