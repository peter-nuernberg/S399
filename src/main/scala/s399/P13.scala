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
 * (**) Run-length encoding of a list (direct solution).
 *
 * Implement the so-called run-length encoding data compression method directly.
 * I.e. don't use other methods you've written (like [[P09.pack]]); do all the work directly.
 *
 * @example
 * {{{
 *   scala> encodeDirect(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5))
 *   val res0: s399.Result[List[(Int, Int)]] = Right(List((4,1), (1,2), (2,3), (2,1), (1,4), (4,5)))
 * }}}
 */
trait P13:

  /**
   * Returns a run-length encoding of the given list.
   *
   * @param as the list to be run-length encoded
   * @tparam A the type of elements in the given list
   * @return a run-length encoding of the given list
   */
  def encodeDirect[A](as: List[A]): Result[List[(Int, A)]]

/** The exercise solution to [[P13]]. */
object X13 extends P13 :

  // TODO: add your implementation here
  override def encodeDirect[A](as: List[A]): Result[List[(Int, A)]] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def x13main: Unit = println(encodeDirect(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)))
