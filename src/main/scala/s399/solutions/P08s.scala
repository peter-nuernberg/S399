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

/** The provided solution to [[P08]]. */
object P08s extends P08 :

  /**
   * Returns a "compressed" version of the given list with consecutive duplicates of a given element replaced by a
   * single copy of that element.
   *
   * We again use the tail-recursive inner auxilliary method to build a backwards accumulator.
   * The only new insight needed here is when to prepend to the accumulator.
   * When the auxilliary method processes a new head element, that element should only be prepended if it is different
   * than the previous element we prepended.
   * In this solution, we implement this by providing another parameter to the auxilliary named `last` that holds the
   * last element prepended to the accumulator.
   * The type of this parameter is `Option[A]` to allow us to model the initial case (initially, there is no last
   * element prepended, so the initial value of this parameter is `None`.)
   * The method can check both that there was a last element prepended, and that it was the same as the current head,
   * with a call to [[scala.Option.exists]].
   * If the current head matches the last element prepended (assuming there was one), it does *not* prepend the head to
   * the accumulator; otherwise, it does.
   * The method then recurses on the tail, with the (possibly new) accumulator, and setting the "last element prepended"
   * to the current head.
   * 
   * Another option is to compare the current head to the head of the accumulator.
   * (See [[P08s.compressAlt1]]
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

  /** An alterate solution that just examines the head of the accumulator instead of carrying an extra parameter. */
  def compressAlt1[A](as: List[A]): Result[List[A]] =
    @tailrec
    def aux(rest: List[A], acc: List[A] = Nil): List[A] =
      rest match {
        case Nil => acc.reverse
        case h :: t =>
          val newAcc = if acc.headOption.exists(_ == h) then acc else h :: acc
          aux(t, newAcc)
      }
    Right(aux(as))

  /** A main method that executes the provided solution above on the sample input. */
  @main def p08smain: Unit = println(compress(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)))
