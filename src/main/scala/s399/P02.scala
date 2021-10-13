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
 * P02. (*) Find the last but one element of a list.
 *
 * @example
 * {{{
 *   scala> penultimate(List(1, 1, 2, 3, 5, 8))
 *   res0: Either[S399Error, Int] = Right(5)
 * }}}
 */
trait P02:

  /**
   * Returns the last but one element of a list.
   *
   * @param as the list, the penultimate element of which should be returned
   * @tparam A the type of elements in the given list
   * @return either the penultimate element in the given list, or an error if the given list is empty or has only one
   *         element
   */
  def penultimate[A](as: List[A]): Result[A]

/** The exercise solution to P02. */
object P02x extends P02:

  // TODO: add your implementation here
  override def penultimate[A](as: List[A]): Result[A] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def p02xmain: Unit = println(penultimate(List(1, 1, 2, 3, 5, 8)))
