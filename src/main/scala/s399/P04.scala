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
 * P04. (*) Find the number of elements of a list.
 *
 * @example
 * {{{
 *   scala> length(List(1, 1, 2, 3, 5, 8))
 *   val res0: s399.Result[Int] = Right(6)
 * }}}
 */
trait P04:

  /**
   * Returns the number of elements of a list.
   *
   * @param l the list, the length of which should be returned
   * @return the length of the given list
   */
  def length(l: List[_]): Result[Int]

/** The exercise solution to P04. */
object P04x extends P04:

  // TODO: add your implementation here
  override def length(l: List[_]): Result[Int] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def p04xmain: Unit = println(length(List(1, 1, 2, 3, 5, 8)))
