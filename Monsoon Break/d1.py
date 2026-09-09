import sys as _s

def _run():
    _d=_s.stdin.read().split();_i=iter(_d);_t=int(next(_i));_o=[]
    for __ in range(_t):
        _n=int(next(_i));_str=next(_i);_k=int(next(_i))
        _l=0;_wc=0;_mx=0
        for _r in range(_n):
            if _str[_r]=='W':_wc+=1
            while _wc>_k:
                if _str[_l]=='W':_wc-=1
                _l+=1
            _mx=max(_mx,_r-_l+1)
        _o.append(str(_mx))
    _s.stdout.write("\n".join(_o)+"\n")

if __name__=="__main__":
    _run()