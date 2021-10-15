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
 * P07. (**) Flatten a nested list structure.
 *
 * @example
 * {{{
 *   scala> flatten(List(List(1, 1), 2, List(3, List(5, 8))))
 *   val res0: s399.Result[List[Int]] = Right(List(1, 1, 2, 3, 5, 8))
 * }}}
 */
trait P07:

  /**
   * Flattens the given structure into a single, top-level list.
   *
   * Note the type of the given list parameter `List[_]`.
   * We could more tightly constrain this type in the following way.
   * {{{
   *   sealed trait NList[A]
   *   final case class Leaf[A](a: A) extends NList[A]
   *   final case class Node[A](l: List[NList[A]]) extends NList[A]
   *   ...
   *   flatten[A](l: NList[A]): List[A] = ...
   * }}}
   * This ensures that the given nested list (tree) contains only instances of `A` at the leaves.
   * This seemed like too much of a rewrite of the original problem.
   *
   * @param l the list to be flattened
   * @tparam A the type of elements in the given list
   * @return a list that contains no sublists, but does contain all of the non-list elements of the given list in the
   *         order in which they would appear in a depth-first seach, or an error if a leaf element of a type other than
   *         `A` was encountered
   */
  def flatten(l: List[_]): Result[List[_]]

/** The exercise solution to [[P07]]. */
object P07x extends P07 :

  // TODO: add your implementation here
  override def flatten(l: List[_]): Result[List[_]] = ???

  /** A main method that executes the exercise solution above on the sample input. */
  @main def p07xmain: Unit = println(flatten(List(List(1, 1), 2, List(3, List(5, 8)))))
