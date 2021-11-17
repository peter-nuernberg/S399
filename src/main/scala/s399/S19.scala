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

/** The provided solution to [[P19]]. */
object S19 extends P19 :

  /**
   * Returns the given list, rotated the given number of places to the left.
   *
   * This method uses a tail-recursive auxilliary to count down the number of rotation places while taking the head
   * of the input and prepending it to an accumulator.
   * There are two special cases to consider.
   *
   *   1. If the input list is empty, we should always return an empty list.
   *   1. We should "normalize" the number of places to rotate so that it is &in; [0 .. |*as*| - 1].
   *
   * Any positive number of rotations that is as greater than or equal to the length of the given list has an equivalent
   * smaller number of rotations that fits within the range above.
   * For example, for a list of length 3, rotating 5 places leftward is equivalent of rotating 2 places leftward.
   * Likewise any negative number of rotations has an equivalent smaller number of rotations that fits within the range
   * above.
   * Again, for a list of length 3, rotating 1 place rightward is equivalent to rotating 2 places leftward.
   * The operation that will take any number of requested rightward or leftward rotations and generate the equivalent
   * "small", positive number in [0 .. |*as*| - 1] is *floored disision modulo*, accessible as [[scala.math.floorMod]].
   * The modulo operator `%` is a different operation, namely *truncated division modulo*, which can result in nagative
   * results.
   * For example, `-2 % 3 = -2`, whereas `scala.math.floorMod(-2, 3) = 1`.
   */
  override def rotate[A](n: Int, as: List[A]): Result[List[A]] =

    @tailrec
    def aux(c: Int, left: List[A], right: List[A] = Nil): List[A] =
      if c == 0 then
        left ++ right.reverse
      else
        aux(c - 1, left.tail, left.head :: right)

    if as.isEmpty then
      Right(Nil)
    else
      Right(aux(scala.math.floorMod(n, as.length), as))

  /** A main method that executes the provided solution above on the sample input. */
  @main def s19main: Unit = println(rotate(3, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)))
