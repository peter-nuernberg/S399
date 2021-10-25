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

/** The provided solution to [[P06]]. */
object S06 extends P06 :

  /**
   * Indicates whether the given list is a palindrome.
   *
   * There is a very straightforward way of implementing this method by comparing the given list to the result of either
   * the standard library reverse or our implementation in [[P05.reverse]] on the given list.
   * (See [[P06s.isPalindromeAlt1]].)
   * Some of the problem definitions specifically call out using solutions to previous problems.
   * Since this one doesn't, I implemented a slower solution that doesn't rely on P05.
   *
   * Whether a given list is a palindrome is equivalent to whether the following property holds:
   * every element in the first half of the list is equal to the corresponding element in the second half that is
   * equidistant from the middle of the list.
   * Or, if you like symbols:
   *
   * &forall; &lambda; &isin; List, &iota; &isin; Int &SuchThat; 0 &ge; &iota; &lt; &mid;&lambda;&mid; / 2 : &lambda;(&iota;) = &lambda;(&mid;&lambda;&mid; - &iota; - 1)
   *
   * where &lambda;(&iota;) means the element of &lambda; at index &iota; (0-indexed).
   *
   * So, if we test every index starting at 0 that is less than half the length of the given list,
   * we check whether the element at that index is the same as the element on the other half of the last that is as far
   * from the midpoint as the element we're testing.
   *
   * Many folks might write a `for` loop that iterates over the indexes we want to test.
   * In this solution, we have an iterator over all `Int`s starting at 0.
   * For every int, the method treats that as an index and evaluates the property above, which yields a boolean
   * (at least for index values less than half the length of the list.)
   * Then, the method `take`s the correct number of these boolean results (each of which represents the truth or falsity
   * of the property above for a pair of elements);
   * this is an iterator over booleans that is half the length of the given list.
   * The method then `fold`s this, and-ing elements together, with a "zero" value of `true`.
   * Finally, we wrap the whole thing in `Right` (since there's no error we'll ever return from this method.)
   *
   * In the provided solutions, I'll use this "iterator" pattern instead of an explicit `for` loop.
   * This is a matter of personal taste.
   * We could have implemented this as a `for` loop that carries an accumulator that is updated on every pass through
   * the loop.
   * (See [[P06s.isPalindromeAlt2]].)
   *
   * For the primary provided solutions provided here, however, I try to avoid use of variables (`var`s).
   */
  override def isPalindrome(l: List[_]): Result[Boolean] =
    Right(
      Iterator.from(0)
          .map(i => l(i) == l(l.length - i - 1))
          .take(l.length / 2)
          .foldLeft(true)(_ & _))

  /** A main method that executes the provided solution above on the sample input. */
  @main def s06main: Unit = println(isPalindrome(List(1, 2, 3, 2, 1)))

// === ALTERNATE SOLUTION 1 ===

/** The first alternate solution to [[P06]]. */
object A106 extends P06 :

  /**
   * Indicates whether the given list is a palindrome.
   *
   * This alternate solution that uses the built-in reverse (could also use the reverse implemented in [[P05]].)
   */
  override def isPalindrome(l: List[_]): Result[Boolean] =
    Right(l == l.reverse)

  /** A main method that executes the first alternate solution above on the sample input. */
  @main def a106main: Unit = println(isPalindrome(List(1, 2, 3, 2, 1)))

// === PRACTICE SOLUTION 1 ===

/** The first practice solution to [[P06]]. */
object D106 extends P06 :

  /**
   * Indicates whether the given list is a palindrome.
   *
   * This alternate solution uses a variable accumulator.
   * Writing a solution in this way can sometimes be simpler than writing a solution that does not use variables.
   */
  override def isPalindrome(l: List[_]): Result[Boolean] =
    var acc: Boolean = true
    for (i <- 0 until l.length / 2)
      acc &= l(i) == l(l.length - i - 1)
    Right(acc)

  /** A main method that executes the provided solution above on the sample input. */
  @main def d106main: Unit = println(isPalindrome(List(1, 2, 3, 2, 1)))
