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

/** The provided solution to [[P03]]. */
object S03 extends P03 :

  /**
   * Returns the *i*th element of a list.
   *
   * This solution is structurally a bit different than other provided solutions, since it introduces a *nested method*.
   * You can name this nested method anything you'd like, but I tend to use `aux` (for "auxilliary"), which may be a
   * holdover from my LISP programming days.
   * In this solution, it is the auxilliary that is tail-recursive.
   * It has two exit cases and one recursive case:
   *
   *   1. EXIT: If the given list is empty, the index was too large, so the method returns an error.
   *   1. EXIT: If the requested index is 0, the method returns the head of the list (which has to exist, since this
   *      list is not empty, since the previous case did not match).
   *   1. RECURSE: The auxilliary calls itself with an index 1 less than the given index on the tail of the given list.
   *
   * The auxilliary assumes the given index is non-negative, so the caller will have to use `floorMod` to ensure that
   * any negative index is transformed to its equivalent positive value.
   * As noted in the problem documentation, [[scala.math.floorMod]] does the trick, except that it fails if the second
   * argument (in this case, the list length) is 0.
   * This means teh caller should have to do something like:
   * {{{
   * if as.isEmpty then
   *   Left(S399Error("cannot take an element from an empty list"))
   * else
   *   aux(scala.math.floorMod(i, as.length), as)
   * }}}
   * To simplify this a bit, and to give us more practice with errors, this package has an extension method on `Int`
   * named `%%` that returns `Result[Int]` -- an error if the second argument is 0, or the floor modulo.
   * This allows us to call:
   * {{{
   * (i %% as.length).flatMap(ii => aux(ii, as))
   * }}}
   */
  override def elementAt[A](i: Int, as: List[A]): Result[A] =

    @tailrec
    def aux(curr: Int, rest: List[A]): Result[A] =
      (curr, rest) match {
        case (_, Nil) => Left(S399Error("list is to short"))
        case (0, h :: _) => Right(h)
        case (_, _ :: t) => aux(curr - 1, t)
      }

    (i %% as.length).flatMap(ii => aux(ii, as))

  /** A main method that executes the provided solution above on the sample input. */
  @main def s03main: Unit = println(elementAt(2, List("a", "b", "c", "d", "e", "f")))
