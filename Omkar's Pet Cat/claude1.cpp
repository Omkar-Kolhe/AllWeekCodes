#include <bits/stdc++.h>
using namespace std;

int main(){
    int T;
    scanf("%d", &T);
    while(T--){
        int N;
        scanf("%d", &N);
        vector<int> A(N);
        for(auto &x : A) scanf("%d", &x);
        int g = A[0];
        for(int i = 1; i < N; i++) g = __gcd(g, A[i]);
        int cnt = 0;
        for(int x : A) if(x == g) cnt++;
        puts(cnt >= 2 ? "NO" : "YES");
    }
    return 0;
}