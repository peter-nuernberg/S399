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

/** The provided solution to [[P04]]. */
object P04s extends P04 :

  /**
   * Returns the number of elements of a list.
   *
   * This solution has an additional complexity relative to the previous ones.
   * Namely, it introduces an auxilliary inner method.
   * The purpose of the inner method is to allow us to write the solution in a tail-recursive way.
   * 
   * First, let's take a look at the non-tail-recursive solution that does not require an inner method.
   * The recursion here is very straightforward.
   * There are only two cases:
   *
   *   1. EXIT: If the given list is empty, return a length of 0.
   *   1. RECURSE: Otherwise, call `length` on the tail of this list, and add 1 to the result.
   * 
   * So,
   * {{{
   *   override def length(l: List[_]): Result[Int] =
   *     l match
   *       case Nil => 0
   *       case _ :: t => length(t) + 1
   * }}}
   * 
   * This is fine, but it is not tail-recursisve, so it can fail on large lists.
   * (The last thing we do in the recursion case is to add 1 to the result of the recusive call.)
   * 
   * To make the solution tail-recursive, we define an auxilliary function that carries an accumulator.
   * The recursion is simililarly easy to that above:
   *
   *   1. EXIT: If the given list is empty, return the accumulator.
   *   1. RECURSE: Otherwise, add 1 to the accumulator and call `aux` on the tail of this list.
   *
   * You can call the inner method anything you'd like -- I usually use `aux`, but that is probably a holdover from
   * my earlier LISP days.
   * I also always call my accumulator `acc`, but again, anything works here.
   * Finally, there's the question of using default values.
   * I like give the accumulator a reasonable default value, but require the `rest` parameter to be specified
   * explicitly.
   * This, also, is just a matter of taste.
   * Reasonable alternatives might be:
   * 
   *   - `aux(rest: List[_] = l, acc: Int = 0)` -- the auxilliary knows what it wants, so just call `aux()` from the
   *     outer method
   *   - `aux(rest: List[_], acc: Int)` -- be specific about how to call the auxilliary from the outer method by calling
   *     `aux(l, 0)`
   *
   * Finally, since there is no error case here, one could just adjust the outer method signature to return `Int`
   * instead of `Result[Int]`.
   * I just kept it wrapped in `Result` to make the signatures uniform across problems.
   * In situations such as these, the auxilliary could also return `Result[Int]`.
   */
  override def length(l: List[_]): Result[Int] =
    @tailrec
    def aux(rest: List[_], acc: Int = 0): Int =
      rest match
        case Nil => acc
        case _ :: t => aux(t, acc + 1)
    Right(aux(l))

  /** A main method that executes the provided solution above on the sample input. */
  @main def p04smain: Unit = println(length(List(1, 1, 2, 3, 5, 8)))
