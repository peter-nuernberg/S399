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
package solutions

import scala.annotation.tailrec

/** The provided solution to [[P07]]. */
object P07s extends P07 :

  /**
   * Flattens the given structure into a single, top-level list.
   * 
   * This is, for me, a disappointing problem, because the type bounds are so loose.
   * We take a list of anything and hand back a list of anything.
   * We'll discuss this more after we discuss the algorithm for this solution.
   * 
   * We again use an inner tail-recursive auxilliary method to accumulate our answer as we traverse the input.
   * The auxilliary has three cases:
   * 
   *   1. EXIT: If the rest of the input is an empty list, processing is done, the function returns the *reverse* of the
   *      accumulator (see below).
   *   1. If the rest of theinput is a non-empty list:
   *      a. RECURSE: If the head element of the input is a list, call `aux` again on the head prepended to the tail; no
   *         change to the accumulator.  (This just flattens a sublist.)
   *      a. RECURSE: If the head element is not a list, prepend it to the accumulator and call `aux` on the tail.
   * 
   * Part of the problem is that we're trying to stay in the realm of "simple" lists.
   * 
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
   */
  override def flatten(l: List[_]): Result[List[_]] =
    @tailrec
    def aux(rest: List[_], acc: List[_] = Nil): List[_] =
      rest match {
        case Nil => acc.reverse
        case h :: t =>
          h match {
            case l: List[_] => aux(l ++ t, acc)
            case a => aux(t, a :: acc)
          }
      }
    Right(aux(l))

  /** A main method that executes the provided solution above on the sample input. */
  @main def p07smain: Unit = println(flatten(List(List(1, 1), 2, List(3, List(5, 8)))))
