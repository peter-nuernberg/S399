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

/** The provided solution to [[P12]]. */
object S12 extends P12 :

  /**
   * Returns the decoded version of the given run-length encoded list.
   *
   * This method uses an auxilliary tail-recursive method that processes each pair in the input list,
   * prepending the correct items to the accumulator, until reaching the end,
   * when it reverses and then returns the accumulator.
   * There are a couple of complications.
   * The first is that, since the auxilliary can encounter an error, it returns its value wrapped in a `Result`.
   * The error it can encounter is a non-positive run length.
   * The second is how to prepend the "correct items".
   * Consider a head element in the `rest` parameter `(n, a)`.
   * `n` is the run length, and must be positive (or an error is returned and the recursion stops).
   * `a` is the element that comprises the run.
   * The method must prepend `n` copies of `a` to the accumulator.
   * This solution uses a call to [[scala.collection.immutable.List.fill]] to generate a list that contains the correct
   * number of elements, and then prepends the whole list to the accumulator.
   * A more "manual" approach might prepend one element at a time (see [[A112]].)
   */
  override def decode[A](l: List[(Int, A)]): Result[List[A]] =

    @tailrec
    def aux(rest: List[(Int, A)], acc: List[A] = Nil): Result[List[A]] =
      rest match
        case Nil => Right(acc.reverse)
        case (n @ PosInt(), a) :: t => aux(t, List.fill(n)(a) ++ acc)
        case (n, _) :: _ => Left(S399Error(s"non-positive run-length $n"))

    aux(l)

  /** A main method that executes the provided solution above on the sample input. */
  @main def s12main: Unit = println(decode(List((4, 1), (1, 2), (2, 3), (2, 1), (1, 4), (4, 5))))

// === ALTERNATE SOLUTION 1 ===

/** The first alternate solution to [[P12]]. */
/** An alternate solution to [[P12]] that prepends one element at a time to the accumulator. */
object A112 extends P12 :

  /**
   * Returns the decoded version of the given run-length encoded list.
   * 
   * 
   */
  override def decode[A](l: List[(Int, A)]): Result[List[A]] =

    @tailrec
    def aux(rest: List[(Int, A)], curr: Option[(Int, A)] = None, acc: List[A] = Nil): Result[List[A]] =
      (rest, curr) match
        case (Nil, None) => Right(acc.reverse)
        case (_, Some(n @ PosInt(), a)) => aux(rest, Some(n - 1, a), a :: acc)
        case (_, Some(_, _)) => aux(rest, None, acc)
        case ((n @ PosInt(), a) :: t, None) => aux(t, Some(n, a), acc)
        case ((n, _) :: _, _) => Left(S399Error(s"non-positive run-length $n"))

    aux(l)

  /** A main method that executes the provided solution above on the sample input. */
  @main def a112main: Unit = println(decode(List((4, 1), (1, 2), (2, 3), (2, 1), (1, 4), (4, 5))))

/** A simple pattern matcher that matches positive integers. */
object PosInt:
  def unapply(n: Int): Boolean = n > 0
