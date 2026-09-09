#include <bits/stdc++.h>
using namespace std;

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    
    int t_cases;
    if(!(cin >> t_cases)) return 0;
    
    while(t_cases--){
        int sz, k_val;
        string days;
        cin >> sz >> days >> k_val;
        
        int mx = 0, w_cnt = 0, i = 0;
        for(int j = 0; j < sz; ++j){
            if(days[j] == 'W') w_cnt++;
            while(w_cnt > k_val){
                if(days[i] == 'W') w_cnt--;
                i++;
            }
            int window = j - i + 1;
            if(window > mx) mx = window;
        }
        cout << mx << "\n";
    }
    return 0;
}