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

/** The specification of the behavior of a correct solution to [[P13.encodeDirect]]. */
class P13Spec extends BaseSpec :

  // === ASSERTIONS ===

  "A solution to problem 13" - {

    "when given the example input, should return the example output" - {

      def assertion(f: Solution[Int]): Unit =
        f(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)).rightValue shouldBe List(
          (4,1), (1,2), (2,3), (2,1), (1,4), (4,5))

      test(assertion)
    }

    "when given an empty list, should return an empty list" - {

      def assertion(f: Solution[Any]): Unit =
        f(Nil).rightValue shouldBe Nil

      test(assertion)
    }

    "when given a list with a single repeated element, should return the expected result" - {

      def assertion(f: Solution[Int]): Unit =
        forAll((arbInt, "i"), (arbSmallPosInt, "n")) {
          (i, n) => f(List.fill(n)(i)).rightValue shouldBe List((n, i))
        }

      test(assertion)
    }

    "when given a list with multiple repeated elements, should return the expected result" - {

      def assertion(f: Solution[Int]): Unit =
        forAll((arbShuffledIntList, "(seed, l)")) {
          (_, base) =>
            val in = base.flatMap(i => List.fill(i)(i))
            val out = base.map(i => (i, i))
            f(in).rightValue shouldBe out
        }

      test(assertion)
    }
  }

  // === INFRASTRUCTURE ===

  type Solution[A] = List[A] => Result[List[(Int, A)]]

  given [A]: List[(S399Tag, Solution[A])] = List(
    S399Tag.ExerciseSolution -> X13.encodeDirect,
    S399Tag.PrimarySolution -> S13.encodeDirect,
  )

