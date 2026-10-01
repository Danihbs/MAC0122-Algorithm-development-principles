# Permutations.java

This program counts the distinct permutations of a sequence of letters.

The recursive `perm1` method chooses one letter at a time and removes it from the remaining sequence. When no letters remain, it increments `count`. The `h` array prevents the same letter from being chosen twice at the same recursion level, avoiding duplicates. For example, `aba` produces `3` permutations and `abcd` produces `24`.