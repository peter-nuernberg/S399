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

/** The provided solution to [[P17]]. */
object S17 extends P17 :

  /**
   * Returns the last element of a list.
   *
   * This solution again uses [[scala.collection.immutable.List.zipWithIndex]] to do the necessary processing.
   * The alternate solution [[A117]] uses the auxilliary tail recursive solution more closely resembling previous
   * solutions.
   */
  override def split[A](n: Int, as: List[A]): Result[(List[A], List[A])] =
    if math.abs(n) > as.length then
      Left(S399Error(s"index $n out of bounds"))
    else
      val i = if n >= 0 then n else as.length + n
      val (l1, l2) = as.zipWithIndex.partition(_._2 < i)
      Right(l1.map(_._1), l2.map(_._1))

  /** A main method that executes the provided solution above on the sample input. */
  @main def s17main: Unit = println(split(3, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)))

/** The firs alternate solution to [[P17]]. */
object A117 extends P17 :

  /**
   * Returns the last element of a list.
   *
   * This alternate solution recurses through the input, decrementing the split point and adding elements to the
   * accumulator until the split point is 0, at which point the recursion ends, and the sublists are returned.
   * If the input is exhausted before the split point reaches 0, an error is returned.
   */
  override def split[A](n: Int, as: List[A]): Result[(List[A], List[A])] =

    def aux(rest: List[A], curr: Int = n, acc: List[A] = Nil): Result[(List[A], List[A])] =
      (rest, curr) match {
        case (Nil, _) => Left(S399Error(s"index $n too large"))
        case (h :: t, 0) => Right((acc.reverse, rest))
        case (h :: t, c) => aux(t, c - 1, h :: acc)
      }

    aux(as, if n < 0 then as.length + n else n)

  /** A main method that executes the provided solution above on the sample input. */
  @main def a117main: Unit = println(split(3, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)))
