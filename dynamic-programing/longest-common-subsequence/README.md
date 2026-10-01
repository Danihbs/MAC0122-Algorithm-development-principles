# Longest Common Subsequence

## Motivation

`LCS.java` finds a longest common subsequence of two strings: characters that appear in both strings in the same order, without needing to be adjacent. For example, `ACE` is an LCS of `ABCDE` and `ACE`. This problem helps explain how text comparison tools identify content shared by different versions.

## Logic

The program uses dynamic programming. `opt[i][j]` stores the LCS length of the suffixes starting at `s[i]` and `t[j]`. An empty suffix has LCS length zero, so the last row and column remain zero.

- If `s[i] == t[j]`, include that character: `opt[i][j] = 1 + opt[i + 1][j + 1]`.
- Otherwise, take the better result from skipping one character in either string: `opt[i][j] = max(opt[i + 1][j], opt[i][j + 1])`.

The table is filled backward so each needed result is already available. Its construction takes `O(NM)` time and space, where `N` and `M` are the string lengths. The final LCS length is `opt[0][0]`.

The recursive `lcs` method reconstructs one answer: matching characters are included, while mismatches follow the larger neighboring table value. On a tie, it skips a character in the first string. Recursion stops when either string ends. Different subsequences may have the same maximum length.

## Execution

Only the JDK is required. From this directory, compile and run:

```bash
javac LCS.java
java LCS
```

Enter two nonempty strings separated by whitespace, for example:

```text
ABCDE ACE
```

The program reads standard input and prints:

```text
LCS length:3
An LCS:ACE
[Sanity check: 3]
```