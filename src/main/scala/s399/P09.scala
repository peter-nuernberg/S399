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
 * P09. (**) Pack consecutive duplicates of list elements into sublists.
 *
 * @example
 * {{{
 *   scala> pack(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5))
 *   val res0: s399.Result[List[List[Int]]] = Right(List(List(1, 1, 1, 1), List(2), List(3, 3), List(1, 1), List(4), List(5, 5, 5, 5)))
 * }}}
 */
trait P09:

  /**
   * Returns a "packed" version of the given list in which consecutive duplicates are replaced by a list containing
   * consecutive duplicates.
   *
   * @param as the list to be packed
   * @tparam A the type of elements in the given list
   * @return a "packed" version of the given list in which consecutive duplicates are replaced by a list containing
   *         consecutive duplicates
   */
  def pack[A](as: List[A]): Result[List[List[A]]]

/** The exercise solution to [[P09]]. */
object P09x extends P09 :

  // TODO: add your implementation here
  override def pack[A](as: List[A]): Result[List[List[A]]] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def p09xmain: Unit = println(pack(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)))
