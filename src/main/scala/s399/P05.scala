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
 * P05. (*) Reverse a list.
 *
 * @example
 * {{{
 * scala> reverse(List("a", "b", "c", "d", "e", "f"))
 * val res0: s399.Result[List[String]] = Right(List("f", "e", "d", "c", "b", "a"))
 * }}}
 */
trait P05:

  /**
   * Returns the given list in reverse order.
   *
   * @param as the list to be reversed
   * @tparam A the type of elements in the given list
   * @return a new list with items in the given list in reverse order
   */
  def reverse[A](as: List[A]): Result[List[A]]

/** The exercise solution to [[P05]]. */
object X05 extends P05 :

  // TODO: add your implementation here
  override def reverse[A](as: List[A]): Result[List[A]] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def x05main: Unit = println(reverse(List("a", "b", "c", "d", "e", "f")))
