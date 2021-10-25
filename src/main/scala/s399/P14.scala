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
 * P14. (*) Duplicate the elements of a list.
 *
 * @example
 * {{{
 *   scala> duplicate(List(1, 2, 3, 3, 4))
 *   val res0: s399.Result[List[Int]] = Right(List(1, 1, 2, 2, 3, 3, 3, 3, 4, 4))
 * }}}
 */
trait P14:

  /**
   * Returns a list in which all of the elements of the given list are duplicated.
   *
   * @param as the list, the elements of which are to be duplucated
   * @tparam A the type of elements in the given list
   * @return a list in which all of the elements of the given list are duplicated
   */
  def duplicate[A](as: List[A]): Result[List[A]]

/** The exercise solution to [[P14]]. */
object X14 extends P14 :

  // TODO: add your implementation here
  override def duplicate[A](as: List[A]): Result[List[A]] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def x14main: Unit = println(duplicate(List(1, 2, 3, 3, 4)))
