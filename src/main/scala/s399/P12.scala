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
 * P12. (**) Decode a run-length encoded list.
 *
 * Given a run-length code list generated as specified in problem [[P10]], construct its uncompressed version.
 *
 * @example
 * {{{
 *    scala> decode(List((4, 1), (1, 2), (2, 3), (2, 1), (1, 4), (4, 5)))
 *    val res0: s399.Result[List[Int]] = Right(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5))
 * }}}
 */
trait P12:

  /**
   * Returns the decoded version of the given run-length encoded list.
   *
   * @param l the run-length encoded list to be decoded
   * @tparam A the type of encoded elements in the given list
   * @return either the decoded version of the given run-length encoded list, or an error if any of the run lengths
   *         were non-positive
   */
  def decode[A](l: List[(Int, A)]): Result[List[A]]

/** The exercise solution to [[P12]]. */
object X12 extends P12 :

  // TODO: add your implementation here
  override def decode[A](l: List[(Int, A)]): Result[List[A]] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def x12main: Unit = println(decode(List((4, 1), (1, 2), (2, 3), (2, 1), (1, 4), (4, 5))))
