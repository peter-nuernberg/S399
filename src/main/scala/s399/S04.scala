/*
 * Scala 99 problems
 *
 * Copyright 2021 Peter J. Nuernberg
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package s399

import scala.annotation.tailrec

/** The provided solution to [[P04]]. */
object S04 extends P04 :

  /**
   * Returns the number of elements of a list.
   *
   * This solution has an additional complexity relative to the previous ones.
   * Specifically, it introduces an auxilliary inner method.
   * The purpose of the inner method is to allow us to write the solution in a tail-recursive way.
   * (See [[D104]] for a non-tail-recursive solution.)
   *
   * To make the solution tail-recursive, we define an auxilliary function that carries an accumulator.
   * The recursion is simililarly easy to the given non-tail-recursive solution:
   *
   *   1. EXIT: If the given list is empty, the method returns the accumulator.
   *   1. RECURSE: Otherwise, the method adds 1 to the accumulator and calls `aux` on the tail of this input list.
   *
   * You can call the inner method anything you'd like -- I usually use `aux`, but that is probably a holdover from
   * my earlier LISP days.
   * I also usually call my accumulator `acc`, but again, anything works here.
   * Finally, there's the question of using default values.
   * I like give the accumulator a reasonable default value, but require the `rest` parameter to be specified
   * explicitly.
   * This, also, is just a matter of taste.
   * Reasonable alternatives might be are [[A104]] and [[A204]].
   *
   * Finally, since there is no error case here, one could just adjust the outer method signature to return `Int`
   * instead of `Result[Int]`.
   * I just kept it wrapped in `Result` to make the signatures uniform across problems.
   * In situations such as these, the auxilliary could also return `Result[Int]`.
   * See [[A304]].
   */
  override def length(l: List[_]): Result[Int] =

    @tailrec
    def aux(rest: List[_], acc: Int = 0): Int =
      rest match
        case Nil => acc
        case _ :: t => aux(t, acc + 1)

    Right(aux(l))

  /** A main method that executes the provided solution above on the sample input. */
  @main def s04main: Unit = println(length(List("a", "b", "c", "d", "e", "f")))

// === ALTERNATE SOLUTION 1 ===

/** The first alternate solution to [[P04]]. */
object A104 extends P04 :

  /**
   * Returns the number of elements of a list.
   *
   * This is the "all defaults provided" version of the solution.
   * The call to the auxilliary is pretty clean -- just call it with no parameters!
   */
  override def length(l: List[_]): Result[Int] =

    @tailrec
    def aux(rest: List[_] = l, acc: Int = 0): Int =
      rest match
        case Nil => acc
        case _ :: t => aux(t, acc + 1)

    Right(aux())

  /** A main method that executes the first alternate solution above on the sample input. */
  @main def a104main: Unit = println(length(List("a", "b", "c", "d", "e", "f")))

// === ALTERNATE SOLUTION 2 ===

/** The second alternate solution to [[P04]]. */
object A204 extends P04 :

  /**
   * Returns the number of elements of a list.
   *
   * This is the "no defaults provided" version of the solution.
   * The call to the auxilliary is very explicit about how to start the recursion.
   */
  override def length(l: List[_]): Result[Int] =

    @tailrec
    def aux(rest: List[_], acc: Int): Int =
      rest match
        case Nil => acc
        case _ :: t => aux(t, acc + 1)

    Right(aux(l, 0))

  /** A main method that executes the second alternate solution above on the sample input. */
  @main def a204main: Unit = println(length(List("a", "b", "c", "d", "e", "f")))

// === ALTERNATE SOLUTION 3 ===

/** The third alternate solution to [[P04]]. */
object A304 extends P04 :

  /**
   * Returns the number of elements of a list.
   *
   * This is the "wrapped auxilliary" version of the solution.
   * The value of the auxilliary is already wrapped in a [[Result]], so no need to wrap at the call site.
   * We'll need this approach for auxilliaries that can return errors.
   */
  override def length(l: List[_]): Result[Int] =

    @tailrec
    def aux(rest: List[_], acc: Int = 0): Result[Int] =
      rest match
        case Nil => Right(acc)
        case _ :: t => aux(t, acc + 1)

    aux(l)

  /** A main method that executes the third alternate solution above on the sample input. */
  @main def a304main: Unit = println(length(List("a", "b", "c", "d", "e", "f")))

// === PRACTICE SOLUTION 1 ===

/** The first practice solution to [[P04]]. */
object D104 extends P04 :

  /**
   * Returns the number of elements of a list.
   *
   * This is a non-tail-recursive solution.
   * Sometimes, it's easier to write the non-tail-recursive version of a solution first before trying the tail-recursive
   * version with an accumulator.
   * However, this version can fail on long lists since it has to push a stack frame for every recursive call.
   * So, no problems if you find this version easier to write, as long as you can convert it to the tail-recursive
   * version afterwards.
   */
  override def length(l: List[_]): Result[Int] =
    l match
      case Nil => Right(0)
      case _ :: t => length(t).map(_ + 1)

  /** A main method that executes the first alternate solution above on the sample input. */
  @main def d104main: Unit = println(length(List("a", "b", "c", "d", "e", "f")))

