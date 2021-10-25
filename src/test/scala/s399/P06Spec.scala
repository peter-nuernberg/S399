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

import org.scalacheck.Gen

/** The specification of the behavior of a correct solution to [[P06.isPalindrome]]. */
class P06Spec extends BaseSpec :

  // === ASSERTIONS ===

  "A solution to problem 6" - {

    "when given the example input, should return the example output" - {

      def assertion(f: Solution): Unit =
        f(List(1, 2, 3, 2, 1)).rightValue shouldBe true

      test(assertion)
    }

    "when given an empty list as input, should return true" - {

      def assertion(f: Solution): Unit =
        f(Nil).rightValue shouldBe true

      test(assertion)
    }

    "when given a pallindromic list as input, should return true" - {

      def assertion(f: Solution): Unit =
        forAll((palindrome, "p")) {
          (p) => f(p).rightValue shouldBe true
        }

      test(assertion)
    }

    "when given a non-pallindromic list as input, should return false" - {

      def assertion(f: Solution): Unit =
        forAll((nonPalindrome, "l")) {
          (l) => f(l).rightValue shouldBe false
        }

      test(assertion)
    }
  }

  // === INFRASTRUCTURE ===

  type Solution = List[_] =*=> Boolean

  given List[(S399Tag, Solution)] = List(
    S399Tag.ExerciseSolution -> X06.isPalindrome,
    S399Tag.PrimarySolution -> S06.isPalindrome,
    S399Tag.AlternateSolution -> A106.isPalindrome,
    S399Tag.PracticeSolution -> D106.isPalindrome,
  )

  val evenPalindrome: Gen[List[Int]] =
    for
      l <- arbIntList
    yield l ++ l.reverse

  val oddPalindrome: Gen[List[Int]] =
    for
      l <- arbIntList
      m <- arbInt
    yield (l :+ m) ++ l.reverse

  val palindrome: Gen[List[Int]] = Gen.oneOf(evenPalindrome, oddPalindrome)

  val nonPalindrome: Gen[List[Any]] =
    for
      p <- palindrome
      c <- arbChar
    yield c :: ((1 :: p) :+ 1)

