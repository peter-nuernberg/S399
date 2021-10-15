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
   * (See [[P04s.lengthAlt1]] for a non-tail-recursive solution.)
   * 
   * To make the solution tail-recursive, we define an auxilliary function that carries an accumulator.
   * The recursion is simililarly easy to the given non-tail-recursive solution:
   *
   *   1. EXIT: If the given list is empty, the method returns the accumulator.
   *   1. RECURSE: Otherwise, the method adds 1 to the accumulator and calls `aux` on the tail of this input list.
   *
   * You can call the inner method anything you'd like -- I usually use `aux`, but that is probably a holdover from
   * my earlier LISP days.
   * I also always call my accumulator `acc`, but again, anything works here.
   * Finally, there's the question of using default values.
   * I like give the accumulator a reasonable default value, but require the `rest` parameter to be specified
   * explicitly.
   * This, also, is just a matter of taste.
   * Reasonable alternatives might be are [[P04s.lengthAlt2]] and [[P04s.lengthAlt3]].
   *
   * Finally, since there is no error case here, one could just adjust the outer method signature to return `Int`
   * instead of `Result[Int]`.
   * I just kept it wrapped in `Result` to make the signatures uniform across problems.
   * In situations such as these, the auxilliary could also return `Result[Int]`.
   * Also, the auxilliary could return its value wrapped in a `Result`.
   * See [[P04s.lengthAlt4]].
   */
  override def length(l: List[_]): Result[Int] =
    @tailrec
    def aux(rest: List[_], acc: Int = 0): Int =
      rest match
        case Nil => acc
        case _ :: t => aux(t, acc + 1)
    Right(aux(l))

  /** A non-tail-recusive solution. */
  def lengthAlt1(l: List[_]): Result[Int] =
    l match
      case Nil => Right(0)
      case _ :: t => lengthAlt1(t).map(_ + 1)

  /** A tail-recursive alternate that provides good defaults for the outer method call to be very simple. */
  def lengthAlt2(l: List[_]): Result[Int] =
    @tailrec
    def aux(rest: List[_] = l, acc: Int = 0): Int =
      rest match
        case Nil => acc
        case _ :: t => aux(t, acc + 1)
    Right(aux())

  /** A tail-recursive alternate that provides no defaults, requiring the outer method call to be explicit. */
  def lengthAlt3(l: List[_]): Result[Int] =
    @tailrec
    def aux(rest: List[_], acc: Int): Int =
      rest match
        case Nil => acc
        case _ :: t => aux(t, acc + 1)
    Right(aux(l, 0))

  /** A tail-recursive alternate whose auxilliary also wraps values in `Result`. */
  def lengthAlt4(l: List[_]): Result[Int] =
    @tailrec
    def aux(rest: List[_], acc: Int = 0): Result[Int] =
      rest match
        case Nil => Right(acc)
        case _ :: t => aux(t, acc + 1)
    aux(l)

  /** A main method that executes the provided solution above on the sample input. */
  @main def p04smain: Unit = println(length(List(1, 1, 2, 3, 5, 8)))
