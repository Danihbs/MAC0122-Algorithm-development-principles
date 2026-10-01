# Tower of Hanoi

Three recursive implementations of the Tower of Hanoi puzzle. Each move transfers one disc, and a larger disc must never be placed on a smaller one.

## Course context

`HanoiFourPegs.java` was the first programming assignment (Exercício Programa, or EP) given by our professor in MAC0122.

## Programs

- **Hanoi.java:** solves the puzzle with three pegs and prints each disc number followed by `L` or `R`. These directions follow a circular ordering of the pegs.
- **HanoiFourPegs.java:** uses four pegs and prints pairs of disc number and destination peg (`0` to `3`). An optional second argument also prints the move count.
- **AnimatedHanoi.java:** solves the three-peg puzzle, prints each move, and animates the discs. An array stores the current peg of each disc and is updated before every redraw.

## Recursive logic

With three pegs, move the `n - 1` smaller discs to the auxiliary peg, move the largest disc to the destination, then move the smaller discs onto it. Recursion stops at zero discs and produces `2^n - 1` moves. `Hanoi` expresses the transfers using directions; `AnimatedHanoi` passes the source, auxiliary, and destination pegs explicitly.

With four pegs, `k_otimo` chooses `k` using triangular sums. The program first moves the `n - k` smaller discs aside using four pegs, transfers the `k` larger discs using three pegs, then moves the smaller group onto them using four pegs. Cases with zero, one, or two discs are handled directly.

## Dependencies and execution

`Hanoi` and `HanoiFourPegs` require only the JDK. Run these commands from this directory:

```bash
javac Hanoi.java HanoiFourPegs.java
java Hanoi 3
java HanoiFourPegs 4 contar
```

Only `AnimatedHanoi` requires the external `StdDraw` class from Princeton's Java library. Place `StdDraw.java` (the version without a package declaration) in this directory, then run:

```bash
javac StdDraw.java AnimatedHanoi.java
java AnimatedHanoi 3
```

The animation requires a graphical environment. Pass a non-negative integer as the number of discs. All console output uses Java's `System.out`; `StdOut` is not required.