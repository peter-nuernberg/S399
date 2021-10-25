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
 * P08. (**) Eliminate consecutive duplicates of list elements.
 * If a list contains repeated elements they should be replaced with a single copy of the element.
 * The order of the elements should not be changed.
 *
 * @example
 * {{{
 *   scala> compress(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5))
 *   val res0: s399.Result[List[Int]] = Right(List(1, 2, 3, 1, 4, 5))
 * }}}
 */
trait P08:

  /**
   * Returns a "compressed" version of the given list with consecutive duplicates of a given element replaced by a
   * single copy of that element.
   *
   * @param as the list to be compressed
   * @tparam A the type of elements in the given list
   * @return a "compressed" version of the given list with consecutive duplicates of a given element replaced by a
   *         single copy of that element
   */
  def compress[A](as: List[A]): Result[List[A]]

/** The exercise solution to [[P08]]. */
object X08 extends P08 :

  // TODO: add your implementation here
  override def compress[A](as: List[A]): Result[List[A]] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def x08main: Unit = println(compress(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)))
