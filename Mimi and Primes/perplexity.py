import math
from functools import reduce

def gcd(a, b):
    while b:
        a, b = b, a % b
    return a

def largest_prime_factor(n):
    if n <= 1:
        return -1
    
    largest = -1
    
    if n % 2 == 0:
        largest = 2
        while n % 2 == 0:
            n //= 2
    
    i = 3
    while i * i <= n:
        if n % i == 0:
            largest = i
            while n % i == 0:
                n //= i
        i += 2
    
    if n > 1:
        largest = n
    
    return largest

t = int(input())
for _ in range(t):
    n = int(input())
    a = list(map(int, input().split()))
    
    g = reduce(gcd, a)
    print(largest_prime_factor(g))
