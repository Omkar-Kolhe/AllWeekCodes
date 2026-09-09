#include <bits/stdc++.h>
using namespace std;

int main(){
    int t;
    scanf("%d", &t);
    while(t--){
        int n;
        scanf("%d", &n);
        vector<pair<long long,long long>> pkg(n);
        for(int i = 0; i < n; i++){
            long long p, d;
            scanf("%lld %lld", &p, &d);
            pkg[i] = {d, p};
        }
        sort(pkg.begin(), pkg.end());

        priority_queue<long long> pq;
        long long curTime = 0;

        for(int i = 0; i < n; i++){
            long long d = pkg[i].first;
            long long p = pkg[i].second;

            pq.push(p);
            curTime += p;

            if(curTime > d){
                long long biggest = pq.top();
                pq.pop();
                curTime -= biggest;
            }
        }

        printf("%d\n", (int)pq.size());
    }
    return 0;
}
