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
 * P20. (*) Remove the Kth element from a list.
 *
 * Return the list and the removed element in a Tuple. Elements are numbered from 0.
 *
 * @example
 * {{{
 *   scala> removeAt(1, List(1, 2, 3, 4))
 *   val res0: s399.Result[(List[Int], Int)] = (List(1, 3, 4),2)
 * }}}
 */
trait P20:

  /**
   * Removes the element at the given index from the given list.
   * 
   * Non-negative indexes are interpreted left-to-right (0-based).
   * Negative indexes are interpreted right-to-left (1-based).
   *
   * @param k the index of the element to remove
   * @param as the list, the last element of which should be returned
   * @tparam A the type of elements in the given list
   * @return either the modified list and the removed element, or an error if the given index is out of bounds
   */
  def removeAt[A](k: Int, as: List[A]): Result[(List[A], A)]

/** The exercise solution to [[P20]]. */
object X20 extends P20 :

  // TODO: add your implementation here
  override def removeAt[A](k: Int, as: List[A]): Result[(List[A], A)] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def x20main: Unit = println(removeAt(1, List(1, 2, 3, 4)))
