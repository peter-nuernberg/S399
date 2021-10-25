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

import scala.annotation.tailrec

/** The provided solution to [[P01]]. */
object S01 extends P01 :

  /**
   * Returns the last element of a list.
   *
   * The standard library provides the built-in [[scala.collection.immutable.List.last]] method,
   * but this solution implements this functionality "from scratch".
   * (In general, many of these problems can make good use of the functions the standard library provides,
   * but I will make as little use of these as possible in these solutions.)
   *
   * Here, we have a tail recursive function with two exits and one recursive case.
   *
   *   1. EXIT: If the given list is empty (i.e., matches the pattern `case Nil`),
   *      there is no last element, so the method returns an error.
   *   1. EXIT: If the given list has exactly 1 element (i.e., matches the pattern `case h :: Nil`),
   *      the only element in the list (`h`, for "head") is the last element, so the method returns it.
   *   1. RECURSE: If the given list has more than 1 element (i.e., matches `case _ :: t` -- `t` cannot be `Nil` since
   *      the case above didn't match), the method calls `last` on everything but the first element (`t` for
   *      "tail").
   *
   * This pattern of processing a list with a tail-recursive case matching function will be repeated often in the other
   * provided solutions.
   *
   * It is important to note that this is a *tail-recursive* function, meaning that in the recursive case, the very
   * last statement executed is the recursive call -- no further processing is done.
   * This allows the compiler to make certain optimizations and helps avoid "blown stack" errors.
   */
  @tailrec
  override def last[A](as: List[A]): Result[A] =
    as match
      case Nil => Left(S399Error("no last element of an empty list"))
      case h :: Nil => Right(h)
      case _ :: t => last(t)

  /** A main method that executes the provided solution above on the sample input. */
  @main def s01main: Unit = println(last(List(1, 1, 2, 3, 5, 8)))
