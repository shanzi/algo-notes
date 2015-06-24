# Invert Binary Tree

It is a simple problem, but also famous on Twitter. Please refer to the problem description on
[LeetCode](https://leetcode.com/problems/invert-binary-tree/).

There are three points should be noted about this problem:

1. Be careful of different description. *Let's say we have a Binary Search Tree, the order
of items in the tree is ascending and we'd like to transform it into descending. How can
we do this?* It is actually equavalent to invert this binary tree.
2. The recursive solution is simple. But how about the tree is very high and you will
get an `StackOverflowError` if you are using recursion? The solution using a stack
and without recursion should be mastered.
3. How about the tree is not a binary tree? Please make sure if the tree you meet
in an interview is a binary tree or not. If it is not a binary tree, recursion solution
might be as simple. But how can you do it without recursion?

Below are a piece of sample code for inverting a binary tree:

!CODEFILE "../leetcode/InvertBinaryTree.java"
