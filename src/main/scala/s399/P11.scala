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
 * P11. (*) Modified run-length encoding.
 *
 * Modify the result of problem P10 in such a way that if an element has no duplicates it is simply copied into the
 * result list.
 * Only elements with duplicates are transferred as (N, E) terms.
 *
 * @example
 * {{{
 *   scala> encodeModified(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5))
 *   val res0: s399.Result[List[Any]] = Right(List((4,1), 2, (2,3), (2,1), 4, (4,5)))
 * }}}
 */
trait P11:

  /**
   * Returns a modified run-length encoding of the given list.
   *
   * @param l   the list to be encoded
   * @param p09 a solution to [[P09]]
   * @param p10 a solution to [[P10]]
   * @return the modified run-length encoding of the given list
   */
  def encodeModified(l: List[_])(using p09: P09, p10: P10): Result[List[_]]

/** The exercise solution to [[P11]]. */
object X11 extends P11 :

  /** The solution to [[P09]] used by [[X10.encode]] by default. */
  given P09 = X09

  /** The solution to [[P10]] used by [[X11.encodeModified]] by default. */
  given P10 = X10

  // TODO: add your implementation here
  override def encodeModified(l: List[_])(using p09: P09, p10: P10): Result[List[_]] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def x11main: Unit = println(encodeModified(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)))
