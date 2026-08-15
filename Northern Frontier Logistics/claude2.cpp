#include<bits/stdc++.h>
using namespace std;

int main(){
    int t;
    scanf("%d",&t);
    while(t--){
        int n,d;
        scanf("%d %d",&n,&d);
        int w[50005];
        int mx=0,tot=0;
        for(int i=0;i<n;i++){
            scanf("%d",&w[i]);
            if(w[i]>mx) mx=w[i];
            tot+=w[i];
        }
        int ans=tot;
        int l=mx,r=tot;
        while(l<=r){
            int m=(l+r)/2;
            int day=1,s=0;
            bool ok=true;
            for(int i=0;i<n;i++){
                if(s+w[i]>m){
                    day++;
                    s=w[i];
                    if(day>d){
                        ok=false;
                        break;
                    }
                }else{
                    s+=w[i];
                }
            }
            if(ok){
                ans=m;
                r=m-1;
            }else{
                l=m+1;
            }
        }
        printf("%d\n",ans);
    }
}