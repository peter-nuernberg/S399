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
 * P15. (**) Duplicate the elements of a list a given number of times.
 *
 * @example
 * {{{
 *   scala> duplicateN(3, List(1, 2, 3, 3, 4))
 *   val res0: s399.Result[List[Int]] = List(1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4)
 * }}}
 */
trait P15:

  /**
   * Returns a list in which all of the elements of the given list are duplicated the given number of times.
   *
   * @param n  the number of times to duplicate each element (must be non-negative)
   * @param as the list, the elements of which are to be duplucated
   * @tparam A the type of elements in the given list
   * @return either a list in which all of the elements of the given list are duplicated the given number of times, or
   *         an error if the given number of times to duplicate each element is negative
   */
  def duplicateN[A](n: Int, as: List[A]): Result[List[A]]

/** The exercise solution to [[P15]]. */
object X15 extends P15 :

  // TODO: add your implementation here
  override def duplicateN[A](n: Int, as: List[A]): Result[List[A]] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def x15main: Unit = println(duplicateN(3, List(1, 2, 3, 3, 4)))
