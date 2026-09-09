#include<bits/stdc++.h>
using namespace std;
int main(){
int tc;scanf("%d",&tc);
while(tc--){
int n;scanf("%d",&n);
static char a[200005];
scanf("%s",a);
int k;scanf("%d",&k);
int l=0,c=0,ans=0;
for(int r=0;r<n;r++){
if(a[r]=='W')c++;
while(c>k){if(a[l]=='W')c--;l++;}
if(r-l+1>ans)ans=r-l+1;
}
printf("%d\n",ans);
}
}