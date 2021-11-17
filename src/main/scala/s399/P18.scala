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
 * P18. (**) Extract a slice from a list.
 *
 * Given two indices, *i* and *k*, the slice is the list containing the elements from and including the *i*th element up
 * to but not including the *k*th element of the original list.
 * Start counting the elements with 0.
 *
 * @example
 * {{{
 *   scala> slice(3, 7, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11))
 *   val res0: s399.Result[List[Int]] = List(4, 5, 6, 7)
 * }}}
 */
trait P18:

  /**
   * Returns the slice of the given list with the given inclusive start and exclusive end indexes.
   *
   * @param start the starting index of the slice to be returned (inclusive)
   * @param end   the ending index of the slice to be returned (exclisive)
   * @param as    the list, the last element of which should be returned
   * @tparam A the type of elements in the given list
   * @return either the specified slice of the given list, or an error if the either index is too large or the given
   *         end index is larger than the given start index
   */
  def slice[A](start: Int, end: Int, as: List[A]): Result[List[A]]

/** The exercise solution to [[P18]]. */
object X18 extends P18 :

  // TODO: add your implementation here
  override def slice[A](start: Int, end: Int, as: List[A]): Result[List[A]] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def x18main: Unit = println(slice(3, 7, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)))
