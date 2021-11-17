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
 * P03. (*) Find the *i*th element of a list.
 *
 * @example
 * {{{
 * scala> elementAt(2, List("a", "b", "c", "d", "e", "f"))
 * val res0: s399.Result[String] = Right(c)
 * }}}
 */
trait P03:

  /**
   * Returns the *i*th element of a list.
   *
   * For this and all of the other problems in this repository, indexes should be understood in the following way.
   * For non-negative *i*, the *i*th element of the list refers to the *i*th element from the left, 0 indexed.
   * For negative *i*, the *i*th element of the list refers to the *i*th element from the right, 1 indexed.
   * For a list of length *l*, providing an index outside the range [-*l* .. *l*-1] should result in an error.
   *
   * For any given negative index that is in range, calculating the equivalent positive index can be done with the
   * "floored" modulo operator, accessible via [[scala.math.floorMod]].
   * (Note that the `%` operator provides the "truncated" modulo operator, which can be negative, making it of no use to
   * us here.)
   * Many provided solutions in this repository make use of `floorMod` to 
   *
   * @param i  the index of the element to return
   * @param as the list to be indexed
   * @tparam A the type of elements in the given list
   * @return the element corresponding to the given index, or an error if the index was out of range
   *
   * @example
   * Given the list `List("a", "b", "c")`, consider the following values of *i* and their results:
   *
   * <table>
   * <thead>
   * <tr><td><i>i</i></td><td>result</td></tr>
   * </thead>
   * <tbody>
   * <tr><td><i>&le; -4 or &ge; 3</i></td><td>error</td></tr>
   * <tr><td><i>-3 or 0</i></td><td>"a"</td></tr>
   * <tr><td><i>-2 or 1</i></td><td>"b"</td></tr>
   * <tr><td><i>-1 or 2</i></td><td>"c"</td></tr>
   * </tbody>
   * </table>
   */
  def elementAt[A](i: Int, as: List[A]): Result[A]

/** The exercise solution to [[P03]]. */
object X03 extends P03 :

  // TODO: add your implementation here
  override def elementAt[A](i: Int, as: List[A]): Result[A] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def x03main: Unit = println(elementAt(2, List("a", "b", "c", "d", "e", "f")))
