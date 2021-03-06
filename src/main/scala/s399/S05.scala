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

/** The provided solution to [[P05]]. */
object S05 extends P05 :

  /**
   * Returns the given list in reverse order.
   *
   * Here, we have an auxilliary inner method with an accumulator to allow us to write a tail recursive solution.
   * For a non-tail-recursive alternate solution, see [[D105]].
   *
   * The construction of the inner tail-recursive method for this problem is similar to that of the auxilliary for
   * [[P04]].
   * The recurse case does the "addition" (integer addition in P04, prepending here) to the accumulator before making
   * the recursive call on the tail of the list being processed.
   */
  override def reverse[A](as: List[A]): Result[List[A]] =

    @tailrec
    def aux(rest: List[A], acc: List[A] = Nil): List[A] =
      rest match
        case Nil => acc
        case h :: t => aux(t, h :: acc)

    Right(aux(as))

  /** A main method that executes the provided solution above on the sample input. */
  @main def s05main: Unit = println(reverse(List("a", "b", "c", "d", "e", "f")))

// === PRACTICE SOLUTION 1 ===

/** The first practice solution to [[P05]]. */
object D105 extends P05 :

  /**
   * Returns the given list in reverse order.
   *
   * This is a non-tail-recursive solution.
   * The exit case here returns `Nil` instead of `0` in [[D104]] -- note that these are the additive identities for
   * lists and integers, respectively; likewise, the recursion case adds (or in the case of lists here, prepends).
   */
  override def reverse[A](as: List[A]): Result[List[A]] =
    as match
      case Nil => Right(Nil)
      case h :: t => reverse(t).map(_ :+ h)

  /** A main method that executes the first alternate solution above on the sample input. */
  @main def d105main: Unit = println(reverse(List("a", "b", "c", "d", "e", "f")))
