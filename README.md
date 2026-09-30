# MAC0122 — Algorithm Development Principles

This repository contains educational Java examples inspired by topics commonly explored in **MAC0122 (Principles of Algorithm Development)**.
The focus is on clear, standalone implementations of classic techniques such as recursion, dynamic programming, randomized methods, and recursive graphics.

## Repository Structure

```text
.
├── recursion/
│   ├── binary-representation/
│   │   └── Binary.java
│   ├── collatz/
│   │   └── CollatzSequence.java
│   ├── fibonacci/
│   │   └── FibonacciMemoization.java
│   ├── permutations/
│   │   └── Permutations.java
│   └── towers-of-hanoi/
│       ├── Hanoi.java
│       ├── HanoiFourPegs.java
│       └── AnimatedHanoi.java
├── dynamic-programming/
│   └── longest-common-subsequence/
│       └── LCS.java
├── randomized-algorithms/
│   ├── Gaussian.java
│   ├── RandomInt.java
│   └── RandomSeq.java
└── recursive-graphics/
    ├── ChaosGame.java
    └── HTree.java
```

## Included Algorithms and Examples

- **Recursion**
  - Binary representation of integers
  - Collatz sequence generation with stopping safeguards
  - Memoized Fibonacci recursion
  - Unique permutation generation (handles repeated characters correctly)
  - Tower of Hanoi (3 pegs and 4 pegs/Frame–Stewart style)
  - Headless-safe textual/Swing Hanoi animation demo

- **Dynamic Programming**
  - Longest Common Subsequence (LCS), including reconstruction of one subsequence

- **Randomized Algorithms**
  - Gaussian sampling via Java standard library
  - Uniform random integer generation with range validation
  - Random sequence generation with configurable length and range

- **Recursive Graphics**
  - Chaos game (Sierpinski-style point generation)
  - Recursive H-tree drawing

## Compilation and Execution

All files are plain Java source files without package declarations to keep compilation simple inside each directory.
Examples:

```bash
# Compile one file
javac recursion/fibonacci/FibonacciMemoization.java

# Run one example
java -cp recursion/fibonacci FibonacciMemoization 40
```

For GUI-based examples (`ChaosGame`, `HTree`, and Swing mode of `AnimatedHanoi`), headless environments automatically fall back to console-safe behavior where implemented.
