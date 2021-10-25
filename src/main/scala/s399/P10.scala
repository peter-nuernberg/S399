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
 * P10. (*) Run-length encoding of a list.
 * Use the result of problem P09 to implement the so-called run-length encoding data compression method.
 * Consecutive duplicates of elements are encoded as tuples (N, E) where N is the number of duplicates of the element E.
 *
 * @example
 * {{{
 *   scala> encode(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5))
 *   val res0: s399.Result[List[(Int, Int)]] = Right(List((4,1), (1,2), (2,3), (2,1), (1,4), (4,5)))
 * }}}
 */
trait P10:

  /**
   * Returns a run-length encoding of the given list.
   *
   * @param as  the list to be run-length encoded
   * @param p09 a solution to [[P09]]
   * @tparam A the type of elements in the given list
   * @return either a run-length encoding of the given list, or an error if the underlying call to [[P09.pack]] failed
   */
  def encode[A](as: List[A])(using p09: P09): Result[List[(Int, A)]]

/** The exercise solution to [[P10]]. */
object X10 extends P10 :

  /** The solution to [[P09]] used by [[X10.encode]] by default. */
  given P09 = X09

  // TODO: add your implementation here
  override def encode[A](as: List[A])(using p09: P09): Result[List[(Int, A)]] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def x10main: Unit = println(encode(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)))
