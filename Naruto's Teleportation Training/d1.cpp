#include <bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int t;
    if(!(cin>>t))return 0;
    while(t--){
        long long a,b,c,d;
        cin>>a>>b>>c>>d;
        if(b>d || (d-c)<(b-a)){
            cout<<-1<<"\n";
        }else{
            long long ans=(a-c)+2*(d-b);
            cout<<ans<<"\n";
        }
    }
    return 0;
}