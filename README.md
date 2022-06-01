Rolling hash method (check all binary codes of size k problem) 
[link](https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/discuss/2092553/Explaining-the-Rolling-Hash-Method-or-Guide)

#### bit manipulation notes:
- X is power of 2 when (x & (x-1)) == 0
- X mod power of 2 (x mod 2^n) = (x & (2^n - 1))
- X rightmost set bit, if x is not 0, y = x & ~ (x-1)
- Right propogate rightmost set bit in x, eg. x=0b0100 => x=0b0111, y = x ||  ((x & ~(x-1)) -1) <br>
    Modulo in general returns the remainder of a value after division. So x mod 4, for example, returns 0, 1, 2 or 3 depending on x. These possible values can be represented using two bits in binary (00, 01, 10, 11) - another way to do x mod 4 is to simply set all the bits to zero in x except the last two ones.<br>
    this last note is valid also for modulo 10 and 10^n <br>
    347 % 10 = 7, since n = 1 <br>
    347 % 100 = 47, since n = 2

