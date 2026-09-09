#include<bits/stdc++.h>
using namespace std;
int main(){
ios::sync_with_stdio(false);cin.tie(0);
int tc;cin>>tc;
while(tc--){
long long a,b,c,d;cin>>a>>b>>c>>d;
if(d<b){cout<<-1<<"\n";continue;}
long long dg=d-b;
long long xx=a+dg;
if(xx<c)cout<<-1<<"\n";
else cout<<dg+xx-c<<"\n";
}
}