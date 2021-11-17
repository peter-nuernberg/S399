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

/** The provided solution to [[P16]]. */
object S16 extends P16 :

  /**
   * Drops every *n*th element of the given list.
   *
   * This solution follows the pattern of previous solutions in this package.
   * A tail-recursive auxilliary processes the given list 1 element at a time, prepending to an accumulator as it
   * processes.
   * The main method checks for two conditions before calling the auxilliary: namely:
   *
   *   1. If the given `n` is negative, return an error.
   *   1. If the given `n` is 0, return the given list.
   *
   * This should provide us some confidence that this pattern works for a wide variety of problems.
   * For an alternate that leverages the standard library, see [[A116]].
   */
  override def drop[A](n: Int, as: List[A]): Result[List[A]] =

    @tailrec
    def aux(rest: List[A], acc: List[A] = Nil, curr: Int = n): List[A] =
      (rest, curr) match
        case (Nil, _) => acc.reverse
        case (h :: t, 1) => aux(t, acc)
        case (h :: t, i) => aux(t, h :: acc, i - 1)

    if n < 0 then
      Left(S399Error(s"given number must be non-negative"))
    else if (n == 0)
      Right(as)
    else
      Right(aux(as))

  /** A main method that executes the provided solution above on the sample input. */
  @main def s16main: Unit = println(drop(3, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)))

/** The first alternate solution to [[P16]]. */
object A116 extends P16 :

  /**
   * Drops every *n*th element of the given list.
   *
   * This alternate uses two very useful methods for lists, namely [[scala.collection.immutable.List.zipWithIndex]]
   * and [[scala.collection.immutable.List.filterNot]].
   */
  override def drop[A](n: Int, as: List[A]): Result[List[A]] =

    if n < 0 then
      Left(S399Error(s"given number must be non-negative"))
    else if (n == 0)
      Right(as)
    else
      Right(as.zipWithIndex.filterNot { case (_, i) => (i + 1) % n == 0 }.map(_._1))

  /** A main method that executes the provided solution above on the sample input. */
  @main def a116main: Unit = println(drop(3, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)))
