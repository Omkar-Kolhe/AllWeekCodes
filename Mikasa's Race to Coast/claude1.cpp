#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

int main(){
    int t;
    scanf("%d", &t);
    while(t--){
        int n;
        ll d, f;
        scanf("%d %lld %lld", &n, &d, &f);
        
        vector<pair<ll,ll>> depots(n);
        for(int i = 0; i < n; i++){
            scanf("%lld %lld", &depots[i].first, &depots[i].second);
        }
        sort(depots.begin(), depots.end());
        
        priority_queue<ll> pq;
        ll curFuel = f;
        ll curPos = 0;
        int stops = 0;
        bool ok = true;
        
        for(int i = 0; i <= n; i++){
            ll targetPos = (i == n) ? d : depots[i].first;
            ll dist = targetPos - curPos;
            
            while(curFuel < dist){
                if(pq.empty()){
                    ok = false;
                    break;
                }
                curFuel += pq.top();
                pq.pop();
                stops++;
            }
            
            if(!ok) break;
            
            curFuel -= dist;
            curPos = targetPos;
            
            if(i < n){
                pq.push(depots[i].second);
            }
        }
        
        printf("%d\n", ok ? stops : -1);
    }
    return 0;
}