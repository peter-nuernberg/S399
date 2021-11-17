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

/** The provided solution to [[P18]]. */
object S18 extends P18 :

  /**
   * Returns the slice of the given list with the given inclusive start and exclusive end indexes.
   *
   * This solution again uses [[scala.collection.immutable.List.zipWithIndex]], along with two methods not yet used in
   * the given solutions, [[scala.collection.immutable.List.dropWhile]] an
   * [[scala.collection.immutable.List.takeWhile]].
   * For the familiar tail-recursive auxilliary approach, see [[A118]].
   */
  override def slice[A](start: Int, end: Int, as: List[A]): Result[List[A]] =
    if start >= as.length || start < 0 then
      Left(S399Error(s"start index $start is out of bounds"))
    else if end > as.length || end < 0 then
      Left(S399Error(s"end index $end is out of bounds"))
    else if end < start then
      Left(S399Error(s"end index $end is smaller than start index $start"))
    else
      Right(as.zipWithIndex.dropWhile(_._2 < start).takeWhile(_._2 < end).map(_._1))

  /** A main method that executes the provided solution above on the sample input. */
  @main def s18main: Unit = println(slice(3, 7, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)))

/** The first alternate solution to [[P18]]. */
object A118 extends P18 :

  /**
   * Returns the slice of the given list with the given inclusive start and exclusive end indexes.
   *
   * This solution processes the input one element at a time, decrementing the "start" and "end" indices as it does so.
   * Once the "start" index reaches 0, the head of the input is prepended to the accumulator.
   * This continues until the "end" reaches either 1 or 0.
   * If "end" reaches 0 when s reaches 0, the indexes had the same start value, so an empty is returned.
   * If the "end" reaches 1, the final element is prepended to the accumulator, which is then reversed and returned.
   * If "end" is ever negative, it must have been smaller than start, and an error is returned.
   * Note that these error conditions can all be caught ahead of time before the recursion begins (see [[A218]].)
   * This solution requires no such checking by the caller.
   */
  override def slice[A](start: Int, end: Int, as: List[A]): Result[List[A]] =

    def aux(rest: List[A], s: Int = start, e: Int = end, acc: List[A] = Nil): Result[List[A]] =
      rest match {
        case Nil =>
          if s > 0 then
            Left(S399Error(s"start index $start is out of bounds"))
          else
            Left(S399Error(s"end index $end is out of bounds"))
        case h :: t =>
          if s > 0 then
            aux(t, s - 1, e - 1, acc)
          else if e > 1 then
            aux(t, s - 1, e - 1, h :: acc)
          else if e == 1 then
            Right((h :: acc).reverse)
          else if e == 0 then
            Right(Nil)
          else
            Left(S399Error(s"end index $end is smaller than start index $start"))
      }

    aux(as)

  /** A main method that executes the first alternate solution above on the sample input. */
  @main def a118main: Unit = println(slice(3, 7, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)))

/** The second alternate solution to [[P18]]. */
object A218 extends P18 :

  /**
   * Returns the slice of the given list with the given inclusive start and exclusive end indexes.
   *
   * This solution processes the input one element at a time, decrementing the "start" and "end" indices as it does so.
   * Once the "start" index reaches 0, the head of the input is prepended to the accumulator.
   * This continues until the "end" reaches either 1 or 0.
   * If "end" reaches 0 when s reaches 0, the indexes had the same start value, so an empty is returned.
   * If the "end" reaches 1, the final element is prepended to the accumulator, which is then reversed and returned.
   * If "end" is ever negative, it must have been smaller than start, and an error is returned.
   * Note that these error conditions can all be caught ahead of time before the recursion begins (see [[A218]].)
   * This solution requires no such checking by the caller.
   */
  override def slice[A](start: Int, end: Int, as: List[A]): Result[List[A]] =

    def aux(rest: List[A], s: Int = start, e: Int = end, acc: List[A] = Nil): List[A] =
      if s > 0 then
        aux(rest.tail, s - 1, e - 1, acc)
      else if e > 0 then
        aux(rest.tail, s - 1, e - 1, rest.head :: acc)
      else
        acc.reverse

    if start >= as.length || start < 0 then
      Left(S399Error(s"start index $start is out of bounds"))
    else if end > as.length || end < 0 then
      Left(S399Error(s"end index $end is out of bounds"))
    else if end < start then
      Left(S399Error(s"end index $end is smaller than start index $start"))
    else if start == end then
      Right(Nil)
    else
      Right(aux(as))

  /** A main method that executes the second alternate solution above on the sample input. */
  @main def a218main: Unit = println(slice(3, 7, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)))
