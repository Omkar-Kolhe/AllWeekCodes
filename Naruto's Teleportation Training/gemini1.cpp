#include <bits/stdc++.h>
using namespace std;

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    
    int t_cases;
    if(!(cin >> t_cases)) return 0;
    
    while(t_cases--){
        long long start_x, start_y, end_x, end_y;
        cin >> start_x >> start_y >> end_x >> end_y;
        
        long long up = end_y - start_y;
        if(up < 0){
            cout << "-1\n";
            continue;
        }
        
        long long reach_x = start_x + up;
        if(end_x > reach_x){
            cout << "-1\n";
        } else {
            long long left = reach_x - end_x;
            cout << up + left << "\n";
        }
    }
    return 0;
}