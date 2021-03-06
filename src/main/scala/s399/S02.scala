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

/** The provided solution to [[P02]]. */
object S02 extends P02 :

  /**
   * Returns the last but one element of a list.
   *
   * As with [[S01]], we have a tail-recursive function consisting of a single case match statement.
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
   * (See [[S02a1]], [[S02a2]], [[S02a3]].)
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

  /** A main method that executes the provided solution above on the sample input. */
  @main def s02smain: Unit = println(penultimate(List("a", "b", "c", "d", "e", "f")))

// === ALTERNATE SOLUTION 1 ===

/** The first alternate solution to [[P02]]. */
object A102 extends P02 :

  /**
   * Returns the last but one element of a list.
   *
   * In this alternate solution, the last case is written as `case _`, meaning "match anything".
   */
  @tailrec
  override def penultimate[A](as: List[A]): Result[A] =
    as match
      case Nil | _ :: Nil => Left(S399Error("no penultimate element of a list without at least 2 elements"))
      case h :: _ :: Nil => Right(h)
      case _ => penultimate(as.tail)

  /** A main method that executes the first alternate solution above on the sample input. */
  @main def a102smain: Unit = println(penultimate(List("a", "b", "c", "d", "e", "f")))

// === ALTERNATE SOLUTION 2 ===

/** The second alternate solution to [[P02]]. */
object A202 extends P02 :

  /**
   * Returns the last but one element of a list.
   *
   * In this alternate solution, the last case is written as `case _ :: e1 :: e2 :: t`, meaning "match a list with at
   * least 3 elements (but don't bother naming the first)".
   * Here, the method is explicit about what shape it expects in the last case.
   * The recursion applies to the tail of the input, namely `e1 :: e2 :: t`.
   */
  @tailrec
  override def penultimate[A](as: List[A]): Result[A] =
    as match
      case Nil | _ :: Nil => Left(S399Error("no penultimate element of a list without at least 2 elements"))
      case h :: _ :: Nil => Right(h)
      case _ :: e1 :: e2 :: t => penultimate(e1 :: e2 :: t)

  /** A main method that executes the second alternate solution above on the sample input. */
  @main def a202smain: Unit = println(penultimate(List("a", "b", "c", "d", "e", "f")))

// === ALTERNATE SOLUTION 3 ===

/** The third alternate solution to [[P02]]. */
object A302 extends P02 :

  /**
   * Returns the last but one element of a list.
   *
   * In this alternate solution, the last case is written as `case _ :: e1 :: e2 :: t`, meaning "match a list with at
   * least 3 elements (but don't bother naming any of them)".
   * Here, the method is explicit about what shape it expects in the last case, but creates the tail of the list to call
   * the recursion by calling [[scala.collection.immutable.List.tail]] on the input.
   */
  @tailrec
  override def penultimate[A](as: List[A]): Result[A] =
    as match
      case Nil | _ :: Nil => Left(S399Error("no penultimate element of a list without at least 2 elements"))
      case h :: _ :: Nil => Right(h)
      case _ :: _ :: _ :: _ => penultimate(as.tail)

  /** A main method that executes the third alternate solution above on the sample input. */
  @main def a302smain: Unit = println(penultimate(List("a", "b", "c", "d", "e", "f")))
