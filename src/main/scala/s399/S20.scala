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

/** The provided solution to [[P20]]. */
object S20 extends P20 :

  /**
   * Removes the element at the given index from the given list.
   *
   * TODO
   */
  override def removeAt[A](k: Int, as: List[A]): Result[(List[A], A)] =

    if k < -as.length then
      Left(S399Error(s"given index $k underflows legal range [${-as.length} .. ${as.length - 1}]"))
    else if k >= as.length then
      Left(S399Error(s"given index $k overflows legal range [${-as.length} .. ${as.length - 1}]"))
    else
      val eff = scala.math.floorMod(k, as.length)
      Right((as.zipWithIndex.filterNot(_._2 == eff).map(_._1), as(eff)))

  /** A main method that executes the provided solution above on the sample input. */
  @main def s20main: Unit = println(removeAt(-9, List(1, 2, 3, 4)))
