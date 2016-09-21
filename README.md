# Competitive-Programming

Contains solutions to competetive programming problems along with notes on
solving problems.

## Contributing

Feel free to contribute by means of pull-requests.

Solutions are expected to have their own folders named after the problems. These
exist in a directory called solutions. The folder should contain the source
code, txt files with sample input, a Makefile to build the source file, and a
run.sh file which runs the source file against the sample input files.

For example, the solution to a problem called `<name>` would have a folder
structure like:

```
<name>/
  <name>.<extension> // Source file.
  input.txt          // Sample input.
  Makefile           // Build source file.
  run.sh             // Run source file against sample input.
```

Makefile should work with a single `make` command. `run.sh` should work by
running `sh run.sh`. This consistency allows any solution, regardless of
language, to be built and run the same way.

The source file is expected to be extremely well documented explaining the
solution. It should also contain a header comment before any code of the form:

```
Problem URL: <problem url>

Tags: <problem tags>

Problem Description:

<problem description>
```

where <problem url> is the address the problem was found at, <problem tags> are
simple phrases describing the kind of problem, and <problem description> is a
concise description of the problem and solution.

The Makefile and run.sh files should build and run for a single chosen language
in the solution folder. Multiple source files in different languages can be in a
solutions folder. The only difference is that passing the name of the language
to the Makefile or run.sh files should run the solution for that language.
