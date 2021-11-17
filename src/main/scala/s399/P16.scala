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
 * P16. (**) Drop every Nth element from a list.
 *
 * @example
 * {{{
 *   scala> drop(3, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11))
 *   val res0: s399.Result[List[Symbol]] = Right(List(1, 2, 4, 5, 7, 8, 10, 11))
 * }}}
 */
trait P16:

  /**
   * Drops every *n*th element of the given list.
   *
   * @param n the number indicating which elements shluld be dropped
   * @param as the list from which elements should be dropped
   * @tparam A the type of elements in the given list
   * @return either the list with the specified elements removed, or an error if the given number is negative
   */
  def drop[A](n: Int, as: List[A]): Result[List[A]]

/** The exercise solution to [[P16]]. */
object X16 extends P16 :

  // TODO: add your implementation here
  override def drop[A](n: Int, as: List[A]): Result[List[A]] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def x16main: Unit = println(drop(3, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)))
