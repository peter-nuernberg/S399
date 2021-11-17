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

/** The specification of the behavior of a correct solution to [[P03.nth]]. */
class P03Spec extends BaseSpec :

  // === ASSERTIONS ===

  "A solution to problem 3" - {

    "when given the example input, should return the example output" - {

      def assertion(f: Solution[Int]): Unit =
        f(2, List(1, 1, 2, 3, 5, 8)).rightValue shouldBe 2

      test(assertion)
    }

    "when given a non-negative index and a sufficiently long list, should return the expected output" - {

      val legalPositiveIndexAndList: Gen[(Int, List[Int])] =
        for
          nn <- Gen.chooseNum(1, 100)
          n <- Gen.chooseNum(0, nn - 1)
          l <- Gen.listOfN(nn, arbInt)
        yield (n, l)

      def assertion(f: Solution[Int]): Unit =
        forAll((legalPositiveIndexAndList, "(n, l)")) {
          (n, l) => f(n, l).rightValue shouldBe l(n)
        }

      test(assertion)
    }

    "when given a negative index and a sufficiently long list, should return the expected output" - {

      val legalNegativeIndexAndList: Gen[(Int, List[Int])] =
        for
          nn <- Gen.chooseNum(1, 100)
          n <- Gen.chooseNum(-nn, -1)
          l <- Gen.listOfN(nn, arbInt)
        yield (n, l)

      def assertion(f: Solution[Int]): Unit =
        forAll((legalNegativeIndexAndList, "(n, l)")) {
          (n, l) => f(n, l).rightValue shouldBe l(l.length + n)
        }

      test(assertion)
    }

    "when given a non-negative index and an insufficiently long list, should return an error" - {

      val illegalPositiveIndexAndList: Gen[(Int, List[Int])] =
        for
          nn <- Gen.chooseNum(0, 100)
          o <- Gen.chooseNum(0, 100)
          l <- Gen.listOfN(nn, arbInt)
        yield (o + nn, l)

      def assertion(f: Solution[Int]): Unit =
        forAll((illegalPositiveIndexAndList, "(n, l)")) {
          (n, l) => f(n, l).leftValue shouldBe an[S399Error]
        }

      test(assertion)
    }

    "when given a negative index and an insufficiently long list, should return an error" - {

      val illegalNegativeIndexAndList: Gen[(Int, List[Int])] =
        for
          nn <- Gen.chooseNum(0, 100)
          o <- Gen.chooseNum(1, 100)
          l <- Gen.listOfN(nn, arbInt)
        yield (-(o + nn), l)

      def assertion(f: Solution[Int]): Unit =
        forAll((illegalNegativeIndexAndList, "(n, l)")) {
          (n, l) => f(n, l).leftValue shouldBe an[S399Error]
        }

      test(assertion)
    }
  }

  // === INFRASTRUCTURE ===

  type Solution[A] = (Int, List[A]) =*=> A

  given[A]: List[(S399Tag, Solution[A])] = List(
    S399Tag.ExerciseSolution -> X03.elementAt,
    S399Tag.PrimarySolution -> S03.elementAt,
  )
