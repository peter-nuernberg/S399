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

/** The provided solution to [[P02]]. */
object P02s extends P02 :

  /**
   * Returns the last but one element of a list.
   *
   * As with [[P01s]], we have a tail-recursive function consisting of a single case match statement.
   * We again have a two exits and one recursive case.
   *
   *   1. EXIT: If the given list is empty (`Nil`) or has only 1 element (`_ :: Nil`), there is no penultimate element,
   *      so the method returns an error.
   *   1. EXIT: If the given list has exactly 2 elements (i.e., `h :: _ :: Nil`), the head is the last element, so the
   *      method returns it.
   *   1. RECURSE: If the given list has more than 2 elements (basically, everything else), the method calls
   *      `penultimate` on the tail of the list.
   *
   * The last case is not really used to confirm the given list conforms to a specific shape --
   * we already know the list has more than two elements, or it would have matched one of the first two cases.
   * There are three alternative solutions in this object that rewrite the last case pattern.
   * There is some room for personal preference here.
   * I like `case _ :: t`, since the method needs to call `penultimate` on the tail of the list, and this pattern
   * already extracts it.
   */
  @tailrec
  override def penultimate[A](as: List[A]): Result[A] =
    as match
      case Nil | _ :: Nil => Left(S399Error("no penultimate element of a list without at least 2 elements"))
      case h :: _ :: Nil => Right(h)
      case _ :: t => penultimate(t)

  /** Alternative solution in which the last case matches everything (like `default` in other languages). */
  @tailrec
  def penultimateAlt1[A](as: List[A]): Result[A] =
    as match
      case Nil | _ :: Nil => Left(S399Error("no penultimate element of a list without at least 2 elements"))
      case h :: _ :: Nil => Right(h)
      case _ => penultimateAlt1(as.tail)

  /** Alternative solution in which the last case makes explicit that there should be at least 3 elements, and
   * reconstructs the tail of the input list to call the recursion. */
  @tailrec
  def penultimateAlt2[A](as: List[A]): Result[A] =
    as match
      case Nil | _ :: Nil => Left(S399Error("no penultimate element of a list without at least 2 elements"))
      case h :: _ :: Nil => Right(h)
      case _ :: e1 :: e2 :: t => penultimateAlt2(e1 :: e2 :: t)

  /** Alternative solution in which the last case makes explicit that there should be at least 3 elements, but
   * the tail of the input list for the call to the recursion is pulled from the argument, not the pattern. */
  @tailrec
  def penultimateAlt3[A](as: List[A]): Result[A] =
    as match
      case Nil | _ :: Nil => Left(S399Error("no penultimate element of a list without at least 2 elements"))
      case h :: _ :: Nil => Right(h)
      case _ :: _ :: _ :: _ => penultimateAlt3(as.tail)

  /** A main method that executes the provided solution above on the sample input. */
  @main def p02smain: Unit = println(penultimate(List(1, 1, 2, 3, 5, 8)))
