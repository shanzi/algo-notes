#!/usr/bin/env python
# -*- coding: utf-8 -*-


def findRational(n):
    if n == 1: return (1, 1)
    else:
        x, y = findRational(n / 2)
        if n % 2 == 0: return x, x + y
        else: return x + y, y


def findN(a, b):
    if a == b: return 1
    elif a < b: return 2 * findN(a, b - a)
    else: return 2 * findN(a - b, b) + 1


if __name__ == "__main__":
    T = input()
    for t in range(T):
        inp = map(int, raw_input().split())
        if inp[0] == 1:
            x, y = findRational(inp[1])
            print "Case #%d: %d %d" % (t + 1, x, y)
        else:
            print "Case #%d: %d" % (t + 1, findN(inp[1], inp[2]))
