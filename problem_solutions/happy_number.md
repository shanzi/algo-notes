# Happy Number

Please refer to the problem statements through [this link](https://leetcode.com/problems/happy-number/).
It is a problem hard to think of a solution but easy to implement. To solve this problem, one should
pick some numbers to simulate the process of generating Happy Number and after tried several numbers
it is likely that we can find:

1. Every number will iterate into a number with only one digit in finite steps.
2. For 1 - 9, only 1 and 7 is Happy Number, so if a number generate a number in 1 - 9 but
not equals to 1 or 7 in its iteration process, it is not a Happy Number.
3. In fact every Non-HappyNumber will finally generate a 4. This law is not easy to find.
4. For a 32-bit integer, in the first generation step it will turn to a integer less than 9922
(see the explanation comment in the sample code below)

!CODEFILE "../leetcode/HappyNumber.java"
