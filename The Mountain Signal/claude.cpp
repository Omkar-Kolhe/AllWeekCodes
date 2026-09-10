#include <bits/stdc++.h>
using namespace std;

int main(){
    int T;
    scanf("%d", &T);
    while(T--){
        int N;
        scanf("%d", &N);
        vector<int> A(N);
        for(int i = 0; i < N; i++) scanf("%d", &A[i]);

        vector<int> nextGreater(N, N);
        vector<int> st;
        st.reserve(N);

        for(int i = 0; i < N; i++){
            while(!st.empty() && A[st.back()] < A[i]){
                nextGreater[st.back()] = i;
                st.pop_back();
            }
            st.push_back(i);
        }

        string out;
        out.reserve(N * 7);
        for(int i = 0; i < N; i++){
            int ans;
            if(nextGreater[i] != N) ans = nextGreater[i] - i;
            else ans = N - 1 - i;
            out += to_string(ans);
            out += (i + 1 < N) ? ' ' : '\n';
        }
        fwrite(out.data(), 1, out.size(), stdout);
    }
    return 0;
}