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
 * P06. (*) Find out whether a list is a palindrome.
 *
 * @example
 * {{{
 *   scala> isPalindrome(List(1, 2, 3, 2, 1))
 *   res0: Boolean = true
 * }}}
 */
trait P06:

  /**
   * Indicates whether the given list is a palindrome.
   *
   * A palindrome reads the same forwards and backwards.
   *
   * @param l the list to be checked
   * @return `true` if the given list is a palindrome (or empty); `false`, otherwise
   */
  def isPalindrome(l: List[_]): Result[Boolean]

/**
 * The exercise solution to P06.
 */
object P06x extends P06:

  // TODO: add your implementation here
  override def isPalindrome(l: List[_]): Result[Boolean] = ???

  /**
   * Driver.
   *
   * A main method that executes the exercise solution above on the sample input.
   */
  @main def p06xmain: Unit = println(isPalindrome(List(1, 2, 3, 2, 1)))
