#include<bits/stdc++.h>
using namespace std;

int gg(int a,int b){
    while(b){
        int t=a%b;
        a=b;
        b=t;
    }
    return a;
}

int main(){
    int t;
    scanf("%d",&t);
    while(t--){
        int n;
        scanf("%d",&n);
        int a[200005];
        for(int i=0;i<n;i++) scanf("%d",&a[i]);
        int g=a[0];
        for(int i=1;i<n;i++) g=gg(g,a[i]);
        int c=0;
        for(int i=0;i<n;i++) if(a[i]==g) c++;
        if(c>=2) printf("NO\n");
        else printf("YES\n");
    }
}