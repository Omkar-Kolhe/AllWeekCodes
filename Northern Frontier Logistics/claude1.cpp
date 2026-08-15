#include <bits/stdc++.h>
using namespace std;

bool canShip(vector<int>& W, int cap, int D){
    int days = 1, cur = 0;
    for(int w : W){
        if(cur + w > cap){
            days++;
            cur = 0;
        }
        cur += w;
    }
    return days <= D;
}

int main(){
    int T;
    cin >> T;
    while(T--){
        int N, D;
        cin >> N >> D;
        vector<int> W(N);
        int lo = 0, hi = 0;
        for(auto &w : W){
            cin >> w;
            lo = max(lo, w);
            hi += w;
        }
        while(lo < hi){
            int mid = lo + (hi - lo) / 2;
            if(canShip(W, mid, D)) hi = mid;
            else lo = mid + 1;
        }
        cout << lo << "\n";
    }
}