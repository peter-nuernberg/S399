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
 * P01. (*) Split a list into two parts.
 *
 * The length of the first part is given. Use a Tuple for your result.
 *
 * @example
 * {{{
 *   scala> split(3, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11))
 *   val res0: s399.Result[(List[Int], List[Int])] = (List(1, 2, 3),List(3, 4, 5, 6, 7, 8, 9, 10, 11))
 * }}}
 */
trait P17:

  /**
   * Returns the given list, split at the given index.
   *
   * @param n the index that should begin the second half of the split
   * @param as the list, the last element of which should be returned
   * @tparam A the type of elements in the given list
   * @return the split list, with negative indexes counting from the right
   */
  def split[A](n: Int, as: List[A]): Result[(List[A], List[A])]

/** The exercise solution to [[P01]]. */
object X17 extends P17 :

  // TODO: add your implementation here
  override def split[A](n: Int, as: List[A]): Result[(List[A], List[A])] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def x17main: Unit = println(split(3, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)))
