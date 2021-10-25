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

/** The provided solution to [[P09]]. */
object S09 extends P09 :

  /**
   * Returns a "packed" version of the given list in which consecutive duplicates are replaced by a list containing
   * consecutive duplicates.
   *
   * This solution is fairly similar to the one provided for [[P08]].
   * Instead of a `last` element seen, this method uses a `curr` list being built.
   * When the auxilliary method examines the head of the input, it recurses by either prepending the head to the current
   * list being built (if it is the same as the head element in that list) and leaving the accumulator the same;
   * or, it prepends eth current list to the accumulator and starts a new current list containing the head.
   * At the end of the input, the current list must be prepended to the accumulator before it is reversed and returned.
   */
  override def pack[A](as: List[A]): Result[List[List[A]]] =

    @tailrec
    def aux(rest: List[A], acc: List[List[A]] = Nil, curr: List[A] = Nil): List[List[A]] =
      rest match
        case Nil => (curr :: acc).reverse
        case h :: t =>
          if curr.headOption.forall(_ == h) then
            aux(t, acc, h :: curr)
          else
            aux(t, curr :: acc, List(h))

    Right(aux(as))

  /** A main method that executes the provided solution above on the sample input. */
  @main def s09main: Unit = println(pack(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)))

/** The provided solution to [[P09]]. */
object A109 extends P09 :

  /**
   * Returns a "packed" version of the given list in which consecutive duplicates are replaced by a list containing
   * consecutive duplicates.
   *
   * This solution is fairly similar to the one provided for [[P08]].
   * Instead of a `last` element seen, this method uses a `curr` list being built.
   * When the auxilliary method examines the head of the input, it recurses by either prepending the head to the current
   * list being built (if it is the same as the head element in that list) and leaving the accumulator the same;
   * or, it prepends eth current list to the accumulator and starts a new current list containing the head.
   * At the end of the input, the current list must be prepended to the accumulator before it is reversed and returned.
   */
  override def pack[A](as: List[A]): Result[List[List[A]]] =

    @tailrec
    def aux(rest: List[A], acc: List[List[A]] = Nil): List[List[A]] =
      rest match
        case Nil => acc.reverse
        case h :: t =>
          acc match {
            case Nil | Nil :: _ => aux(t, List(List(h)))
            case (ah @ (ch :: _)) :: at =>
              val newAcc = if h == ch then
                (h :: ah) :: at
              else
                List(h) :: ah :: at
              aux(t, newAcc)
          }

    Right(aux(as))

  /** A main method that executes the provided solution above on the sample input. */
  @main def a109main: Unit = println(pack(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)))
