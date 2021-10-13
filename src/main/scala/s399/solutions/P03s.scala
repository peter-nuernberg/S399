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
package solutions

import scala.annotation.tailrec

/** The provided solution to [[P03]]. */
object P03s extends P03:

  /**
   * Returns the _n_ th element of a list.
   *
   * This solution is structurally a bit different than other provided solutions, in that it uses `if/else` instead of
   * `match/case` to identify the cases, but still, we have two exits and two recursive steps:
   *
   *   1. EXIT: If the given list is empty, there is no nth element, se we return an error.
   *   1. EXIT: If the requested index is 0, we return the head of the list (which has to exist, since this list is not
   *      empty, since we got past the previous condition).
   *   1. RECURSE: If the requested index is negative, reverse the list, modify the requested index, and recurse.
   *   1. RECURSE: At this point, the index must positive, and the list must be empty, so we call `nth` with an index
   *      1 less than the given index on the tail of the given list.
   *
   * There are some folks who really do not like calls to `head` and `tail`, since they can fail with thrown exceptions.
   * Scala does provide `headOption`, which some of these same folks prefer, but there isn't a `tailOption`
   * equivalent.
   * We can use case matching and `headOption` to avoid calls to `head` and `tail`, but whether this is clearer is a
   * matter of personal taste.
   * For example:
   * {{{
   *    if n < 0 then
   *      nth(-n - 1, as.reverse)
   *    else if n == 0 then
   *      as.headOption.toRight(S399Error("list is to short"))
   *    else
   *      as match
   *        case _ :: t => nth(n - 1, t)
   *        case _ => Left(S399Error("list is to short"))
   * }}}
   */
  @tailrec
  def nth[A](n: Int, as: List[A]): Result[A] =
    if as.isEmpty then
      Left(S399Error("list is too short"))
    else if n == 0 then
      Right(as.head)
    else if n < 0 then
      nth(-n - 1, as.reverse)
    else
      nth(n - 1, as.tail)

  /** A main method that executes the provided solution above on the sample input. */
  @main def p03smain: Unit = println(nth(2, List(1, 1, 2, 3, 5, 8)))
