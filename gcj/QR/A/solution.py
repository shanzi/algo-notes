#!/usr/bin/env python
# -*- coding: utf-8 -*-
import itertools


def generateNext(N):
    cur = 0
    while True:
        cur += N
        yield cur


def generateUntilSleep(N):
    digitSet = set()
    for n in generateNext(N):
        yield n
        digitSet.update(str(n))
        if (len(digitSet) == 10): return


def main():
    T = input()
    for i in range(1, T + 1):
        print "Case #%d:" % i,
        N = input()
        if N == 0: print 'INSOMNIA'
        else:
            res = list(itertools.islice(generateUntilSleep(N), 0, 100))
            print res[-1]


if __name__ == "__main__":
    main()
