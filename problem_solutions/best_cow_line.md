# Best Cow Line (POJ 3617)

Given a string `S` of length `N`, from this string we pick characters to build a new string `T`,
every time we can remove a character from the head or tail of `S` and append it to `T`. You are
asked to return the lexicographical smallest string `T`.

Apply gready strategy, if the character at the head is smaller than that at the tail, we pick
character from the head. Conversely, if the character at the end is smaller, we pick the tail.
But what if the characters at the head and tail are the same? We can not pick arbitrary one as
it may affect character picking in the following steps. The correct way is to compare the string `S`
and its reverse `S'`. If `S <= S'` we pick from the head, or we pick from the tail.

!CODEFILE "../poj/BestCowLine.java"
