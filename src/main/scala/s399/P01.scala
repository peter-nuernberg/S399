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
 * P01. (*) Find the last element of a list.
 *
 * @example
 * {{{
 *   scala> last(List(1, 1, 2, 3, 5, 8))
 *   val res0: s399.Result[Int] = Right(8)
 * }}}
 */
trait P01:

  /**
   * Returns the last element of a list.
   *
   * @param as the list, the last element of which should be returned
   * @tparam A the type of elements in the given list
   * @return either the last element in the given list, or an error if the given list is empty
   */
  def last[A](as: List[A]): Result[A]

/** The exercise solution to [[P01]]. */
object P01x extends P01 :

  // TODO: add your implementation here
  override def last[A](as: List[A]): Result[A] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def p01xmain: Unit = println(last(List(1, 1, 2, 3, 5, 8)))
