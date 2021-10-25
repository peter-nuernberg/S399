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
 * P03. (*) Find the *n*th element of a list.
 *
 * By convention, the first element in the list is element 0.
 *
 * @example
 * {{{
 *   scala> nth(2, List(1, 1, 2, 3, 5, 8))
 *   val res0: s399.Result[Int] = Right(2)
 * }}}
 */
trait P03:

  /**
   * Returns the *n*th element of a list.
   *
   * For non-negative *n*, the *n*th element of the list refers to the *n*th element from the left, 0 indexed.
   * For negative *n*, the *n*th element of the list refers to the *n*th element from the right, 1 indexed.
   * For a list of length *l*, providing an index outside the range [-*l* .. *l*-1] should result in an error.
   *
   * @param n  the index of the element to return
   * @param as the list to be indexed
   * @tparam A the type of elements in the given list
   * @return the element corresponding to the given index, or an error if the index was out of range
   * @example
   * Given the list `List("a", "b", "c")`, consider the following values of *n* and their results:
   *
   * <table>
   * <thead>
   * <tr><td><i>n</i></td><td>result</td></tr>
   * </thead>
   * <tbody>
   * <tr><td><i>&le; -4 or &ge; 3</i></td><td>error</td></tr>
   * <tr><td><i>-3 or 0</i></td><td>"a"</td></tr>
   * <tr><td><i>-2 or 1</i></td><td>"b"</td></tr>
   * <tr><td><i>-1 or 2</i></td><td>"c"</td></tr>
   * </tbody>
   * </table>
   */
  def nth[A](n: Int, as: List[A]): Result[A]

/** The exercise solution to [[P03]]. */
object X03 extends P03 :

  // TODO: add your implementation here
  override def nth[A](n: Int, as: List[A]): Result[A] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def x03main: Unit = println(nth(2, List(1, 1, 2, 3, 5, 8)))
