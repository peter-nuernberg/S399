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
object P02s extends P02:

  /**
   * Returns the last but one element of a list.
   *
   * As with [[P01s]], we have a tail-recursive function consisting of a single case match statement.
   * We again have a two exits and one recursive case.
   *
   *   1. EXIT: If the given list is empty (`Nil`) or has only 1 element (`_ :: Nil`), there is no penultimate element,
   *      so we return an error.
   *   1. EXIT: If the given list has exactly 2 elements (i.e., `h :: _ :: Nil`), the head is the last element, so we
   *      return it.
   *   1. RECURSE: If the given list has more than 2 elements (basically, everything else), we call `penultimate` on
   *      the tail of the list.
   *
   * The last case is not really used to confirm the given list conforms to a specific shape --
   * we already know the list has more than two elements, or it would have matched one of the first two cases.
   * There are other choices beside the one below.
   * Here are two other ways of writing the recusion case:
   *
   *   -  `case _ => penultimate(as.tail)` -- here, we clearly mark that this case should match everything left, no
   *      matter what it looks like.  `case _` is much like `default` in other contexts.
   *   -  `case _ :: e1 :: e2 :: t => penultimate(e1 :: e2 :: t)` -- here, we clearly mark that this case matches
   *      lists with at least 3 elements.  We discard the first, and can "reconstruct" the tail of the original list.
   *   -  `case _ :: _ :: _ :: _ => penultimate(as.tail)` -- we again clearly mark that this case matches
   *      lists with at least 3 elements (and a possibly empty tail).  We just call `tail` on the original list.
   *
   * There is probably some personal preference here.
   * I like `case _ :: t`, since I need to call `penultimate` on the tail of the list, and this pattern already
   * extracts it.
   */
  @tailrec
  override def penultimate[A](as: List[A]): Result[A] =
    as match
      case Nil | _ :: Nil => Left(S399Error("no penultimate element of a list without at least 2 elements"))
      case h :: _ :: Nil => Right(h)
      case _ :: t => penultimate(t)

  /** A main method that executes the provided solution above on the sample input. */
  @main def p02smain: Unit = println(penultimate(List(1, 1, 2, 3, 5, 8)))