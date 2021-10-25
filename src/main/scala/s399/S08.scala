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

/** The provided solution to [[P08]]. */
object S08 extends P08 :

  /**
   * Returns a "compressed" version of the given list with consecutive duplicates of a given element replaced by a
   * single copy of that element.
   *
   * We again use the tail-recursive inner auxilliary method to build a backwards accumulator.
   * The only new twist here is when to prepend to the accumulator.
   * When the auxilliary method processes a new head element, that element should only be prepended if it is different
   * than the previous element we prepended.
   * In this solution, the method examines the head of the accumulator to see if the current input head is the same.
   * ([[scala.Option.exists]] provides a neat way to differentiate "existing value that is different" versus "either
   * existing value that is the same, or there is no existing value.)
   * If so, the method recurses with the unmodified accumulator (which already has the current head).
   * Otherwise, the input head is prepended to the accumulator.
   * In either event, the method recurses on the input's tail.
   *
   * Another option is to carry another variable to track the last element prepended to the accumulator.
   * (See [[A108]].)
   */
  override def compress[A](as: List[A]): Result[List[A]] =

    @tailrec
    def aux(rest: List[A], acc: List[A] = Nil): List[A] =
      rest match
        case Nil => acc.reverse
        case h :: t =>
          val newAcc = if acc.headOption.exists(_ == h) then acc else h :: acc
          aux(t, newAcc)

    Right(aux(as))

  /** A main method that executes the provided solution above on the sample input. */
  @main def s08main: Unit = println(compress(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)))

// === ALTERNATE SOLUTION 1 ===

/** The first alternate solution to [[P08]]. */
object A108 extends P08 :

  /**
   * Returns a "compressed" version of the given list with consecutive duplicates of a given element replaced by a
   * single copy of that element.
   *
   * In this solution, the auxilliary carries another parameter named `last` that holds the last element prepended to
   * the accumulator.
   * The type of this parameter is `Option[A]` to allow us to model the initial case (initially, there is no last
   * element prepended, so the initial value of this parameter is `None`.)
   */
  override def compress[A](as: List[A]): Result[List[A]] =

    @tailrec
    def aux(rest: List[A], acc: List[A] = Nil, last: Option[A] = None): List[A] =
      rest match {
        case Nil => acc.reverse
        case h :: t =>
          val newAcc = if last.exists(_ == h) then acc else h :: acc
          aux(t, newAcc, Some(h))
      }

    Right(aux(as))

  /** A main method that executes the first alternate solution above on the sample input. */
  @main def a108main: Unit = println(compress(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)))
