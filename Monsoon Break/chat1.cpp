#include<bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin>>t;

    while(t--){
        int n,k;
        string s;
        cin>>n>>s>>k;

        int l=0,w=0,m=0;

        for(int r=0;r<n;r++){
            if(s[r]=='W')w++;

            while(w>k){
                if(s[l]=='W')w--;
                l++;
            }

            m=max(m,r-l+1);
        }

        cout<<m<<"\n";
    }
}