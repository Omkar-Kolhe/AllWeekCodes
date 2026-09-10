#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

int main(){
    int t;
    scanf("%d", &t);
    while(t--){
        ll p, c, s, l;
        scanf("%lld %lld %lld %lld", &p, &c, &s, &l);

        ll made = l / p;
        ll got = 0;

        if(s <= l){
            ll k = (l - s) / c + 1;
            if(s / p >= 1) got = k;
            else got = max(0LL, k - 1);
        }

        printf("%lld\n", made - got);
    }
}