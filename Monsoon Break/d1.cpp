#include<bits/stdc++.h>
using namespace std;
void _slv(){
int _n;cin>>_n;string _S;cin>>_S;int _K;cin>>_K;
int _l=0,_wc=0,_mx=0;
for(int _r=0;_r<_n;++_r){
if(_S[_r]=='W')_wc++;
while(_wc>_K){
if(_S[_l]=='W')_wc--;
_l++;
}
_mx=max(_mx,_r-_l+1);
}
cout<<_mx<<"\n";
}
int main(){
ios::sync_with_stdio(0);cin.tie(0);
int _T;cin>>_T;
while(_T--){_slv();}
return 0;
}