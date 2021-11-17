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

/**
 * P19. (**) Rotate a list N places to the left.
 *
 * @example
 * {{{
 *   scala> rotate(3, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11))
 *   val res0: s399.Result[List[Int]] = List(4, 5, 6, 7, 8, 9, 10, 11, 1, 2, 3)
 * }}}
 *
 * @example
 * {{{
 *   scala> rotate(-2, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11))
 *   val res0: s399.Result[List[Int]] = List(10, 11, 1, 2, 3, 4, 5, 6, 7, 8, 9)
 * }}}
 */
trait P19:

  /**
   * Returns the given list, rotated the given number of places to the left.
   *
   * @prama n the number of places to rotate the given list to the left (or right, if negative)
   * @param as the list, the last element of which should be returned
   * @tparam A the type of elements in the given list
   * @return the given list, rotated the given number of places
   */
  def rotate[A](n: Int, as: List[A]): Result[List[A]]

/** The exercise solution to [[P19]]. */
object X19 extends P19 :

  // TODO: add your implementation here
  override def rotate[A](n: Int, as: List[A]): Result[List[A]] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def x19main: Unit = println(rotate(3, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)))
