# S399

## Introduction

Many years ago, when I was first learning Scala,
I ran across
[S-99: Ninety-Nine Scala Problems](http://aperiodic.net/phil/scala/s-99/),
which I found a helpful source of example problems.
This repository is my attempt to reimplement the solutions in Scala 3.
I also tried to organize the code so that other folks could get started with a bit of plumbing already set up.
Finally, I slightly modified the problems to handle exception cases in a more "Scala-like" way.

## Quick start

If you are familiar with Scala and sbt,
this section will give you all you need to jump right in.
Otherwise, feel free to skip this section and continue below with the more complete explanation of how things are set up
and how to use this code to practice.

Start by replacing the `???` in the problems with your implementation.
Verify your solution by running the tests.
This code uses the convention that every problem returns an `Either`,
so remember to return "correct" solutions on the "right" and return errors on the "left".
There is a given error class called `S399Error` and a type alias called `Result[A]` which is defined as
`Either[A, S399Error]`.
This repository has a dependency on [cats](https://typelevel.org/cats/),
so feel free to use the syntactic sugar cats provides for `Either`.

## Repository organization

This repository follows the standard repository organization expected by [sbt](https://www.scala-sbt.org).
That is:

- `build.sbt` is the main build definition
- `src/main/scala` contains the Scala main source files
- `src/test/scala` contains the Scala test source files

Under the main source tree, the following packages can be found:

- `s399` contains the problem definitions
- `s399/solutions` contains solutions to the problems 

Under the test source tree, the following packages can be found:

- `s399` contains a set of tests for solutions to the problems

If you are unfamiliar with `sbt`, follow the link above for more information.

## Common infrastructure

There are some concepts and some code common to all problems in this repository.

### Return types

Every problem in the repository should return either a correct result or an error.
The error should be an instance of `s399.S399Error` (see below for more information);
the type of correct result will vary from problem to problem.
The return type for a problem whose "correct" result should be of type `A` is modeled as `Either[S399Error, A]`,
with a type alias of `Result[A]`.
So, for a problem that should return an `Int`, the return type would be `Result[Int]`.
To return a correct result of `3`, simply return `Right(3)`.
To return an error, return `Left(S399Error("some message"))`.

### More information on errors

The type `S399Error` is a final case subclass of `java.lang.Exception`.
It has a message and potentially and underlying cause of type `Throwable`.
The standard constructor is:

```scala
final case class S399Error(message: String, cause: Option[Throwable])
    extends Exception(message, cause.orNull)
```

There are two potentially more convenient factory methods in the accompanying companion object:
```scala
def apply(message: String): S399Error = 
  new S399Error(message, Option.empty[Throwable])
  
def apply(message: String, cause: Throwable): S399Error = 
  new S399Error(message, Some(cause))
```

In practice, return a "simple" error using the first factory:
```scala
Left(S399Error("there was a problem"))
```
Return an error that was triggered by an underlying cause using the second factory:
```scala
scala.util.Try { ... }.fold(
  { thr => Left(S399Error("there was a problem", thr)) },
  Right.apply
)
```

## Problem structure

Each problem *n* in this repository has:

- a file named `Pn.scala` in the `s399` package in the `main` source tree containing:
  - a problem definition in a trait named `Pn`;
  - an exercise solution in an object named `Pnx`;
- a file named `Pns.scala` in the `s399.solutions` package in the `main` source tree containing:
  - a provided solution in an object named `Pns`; and,
- a file named `PnSpec.scala` in the `s399` package in the `test` source tree containing:
  - set of associated tests.

Consider the first problem in this repository: return the last element of a list.
For this problem, the following files are relevant:

- `src/main/scala/s399/P01.scala` contains documentation describing the problem and a (currently incorrect) exercise solution
- `src/main/scala/s399/solutions/P01s.scala` contains the provided solution to the problem
- `src/test/scala/s399/P01Spec.scala` contains the tests that any solution should pass

Let's look at each of these in turn:

### `src/main/scala/s399/P01.scala` -- The problem definition and exercise solution

```scala
/* (license header) */
package s399

/**
 * P01. (*) Find the last element of a list.
 *
 * @example
 * {{{
 *   scala> last(List(1, 1, 2, 3, 5, 8))
 *   res0: Either[S399Error, Int] = Right(8)
 * }}}
 */
trait P01:

  /**
   * Returns the last element of a list.
   *
   * @param as the list, the last element of which should be returned
   * @tparam A the type of elements in the given list
   * @return either the last element in the given list, or an error if the given list is empty
   */
  def last[A](as: List[A]): Result[A]

/** The exercise solution to [[P01]]. */
object P01 extends P01:

  // TODO: add your implementation here
  override def last[A](as: List[A]): Result[A] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def p01xmain: Unit = println(last(List(1, 1, 2, 3, 5, 8)))
```

This file contains a trait named `P01` that defines the problem
and an object named `P01x` that provides a place to implement the requested function.
The trait documentation is based on the documentation found on the
[S99 website](http://aperiodic.net/phil/scala/s-99/),
modified to fit the convention that all results are either the expected return type or
an error instead of marking errors by throwing exceptions.
The function documentation in the trait provides additional details,
mainly by calling out error cases and making the function generic, if possible.
The object `P01x` implements the trait and provides a dummy implementation of the function.
This dummy implementation is referred to as the "exercise solution" throughout this document and the codebase.
The exercise solution is initially `???`,
which throws a `NotImplementedException`.
You should replace the `???` with your code to solve the problem.
The object also contains a main method named `p01xmain` that will run the template solution
on the example input given in the initial problem description.

### `src/main/scala/s399/solutions/P01s.scala` -- The provided solution

```scala
/* (license header) */
package s399
package solutions

/** The provided solution to [[P01]]. */
object P01s extends P01:

  /** (extensive documentation that discusses the solution) */
  override def last[A](as: List[A]): Result[A] =
    /* the provided solution -- no cheating! :-) */

  /** A main method that executes the provided solution above on the sample input. */
  @main def p01smain: Unit = println(last(List(1, 1, 2, 3, 5, 8)))
```

This file contains an object named `P01s` that implements the provided solution to the first problem
and has a main method named `p01smain` that will run the provided solution
on the example input given in the initial problem description.
You should not need to edit this file.

### `src/test/scala/s399/P01Spec.scala` -- The tests any solution should pass

```scala
/* (license header) */
package s399

import s399.solutions.*

/** The specification of the behavior of a correct solution to [[P01]]. */
class P01Spec extends BaseSpec {

  type Solution[A] = List[A] => Result[A]

  given [A]: (Solution[A], Solution[A]) = (P01x.last, P01s.last)

  "A solution to problem 1" - {

    "when given the example input, should return the example output" - {

      def assertion(f: Solution[Int]): Unit =
        f(List(1, 1, 2, 3, 5, 8)).rightValue shouldBe 8

      test(assertion)
    }

    "when given a non-empty list as input, should return that list's last element" - {

      def assertion(f: Solution[Int]): Unit =
        forAll((arbIntList, "base"), (arbInt, "lastElem")) {
          (base, lastElem) =>
            f(base :+ lastElem).rightValue shouldBe lastElem
        }

      test(assertion)
    }

    "when given an empty list as input, should return an error" - {

      def assertion(f: Solution[Any]): Unit =
        f(Nil).leftValue shouldBe an[S399Error]

      test(assertion)
    }
  }
}
```

The class `P01Spec` provides a specification of the expected behavior of any solution to problem 1.
It is a subclass of `BaseSpec`, which itself is a subclass of `AnyFreeSpec`, provided by
[scalatest](https://www.scalatest.org).
You should not need to change anything in this file,
though you may want to add additional assertions if you are familiar with scalatest.

At the start of the class declaration, there are two declarations:
a type alias named `Solution` for the signature of a solution (in this case, `List[A] => Result[A]`); and,
a given instance of a pair of solutions (namely, the exercise and provided solutions).

This class goes on to provide three assertions concerning the behavior of a solution when given:

1. the example input (from the problem description);
2. an arbitrary non-empty list; and,
3. an empty list.

Each of these assertions has two parts: an assertion function; and,
a call to the `test` method.

The assertion function takes a solution as input and makes an assertion about the behavior of that solution.
These are written using the "should" matchers provided by scalatest,
optionally also invoking generators to provide random inputs.
(See, e.g., the second assertion, which generates an arbitrary list of integers and an additional integer;
the input to the solution is derived by appending the arbitrary integer to the arbitrary list,
so that we know what the last element of the input is.)

The `test` method actually registers two tests with the scalatest engine.
The first is named "(exercise solution)" and is tagged with `s399.ExerciseSolution`.
The second is named "(provided solution)" and is tagged with `s399.ProvidedSolution`.
The assertion provided as an argument is run against each solution.
This means if you execute all the tests in this package when you first clone the repository,
scalatest should mark half as failing and half as succeeding
(representing the exercise and provided solutions, respectively).
If you want to run tests only against the exercise or provided solutions,
you can pass arguments to the scalatest runner to do so.
For example, to verify all the provided solutions pass the tests,
run scalatest with the command line argument `-n s399.ProvidedSolution`.

Note that if you use the scalatest provided `in` method to register tests,
you will have to designate the solution to test (and optionally tag the test) manually.
