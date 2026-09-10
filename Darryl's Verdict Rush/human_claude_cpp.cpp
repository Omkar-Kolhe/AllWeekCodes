#include <bits/stdc++.h>
using namespace std;

int main(){
    int t;
    scanf("%d", &t);
    while(t--){
        int n;
        scanf("%d", &n);
        vector<pair<long long,long long>> v(n);
        for(int i = 0; i < n; i++){
            long long p, d;
            scanf("%lld %lld", &p, &d);
            v[i] = {d, p};
        }
        sort(v.begin(), v.end());

        priority_queue<long long, vector<long long>, greater<long long>> pq;
        for(auto &job : v){
            long long d = job.first, p = job.second;
            if(!pq.empty() && pq.top() + p <= d){
                long long x = pq.top();
                pq.pop();
                pq.push(x + p);
            } else {
                pq.push(p);
            }
        }

        printf("%d\n", (int)pq.size());
    }
    return 0;
}
