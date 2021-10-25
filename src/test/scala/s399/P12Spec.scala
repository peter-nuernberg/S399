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

/** The specification of the behavior of a correct solution to [[P12.decode]]. */
class P12Spec extends BaseSpec :

  // === ASSERTIONS ===

  "A solution to problem 12" - {

    "when given the example input, should return the example output" - {

      def assertion(f: Solution[Int]): Unit =
        f(List((4, 1), (1, 2), (2, 3), (2, 1), (1, 4), (4, 5))).rightValue shouldBe List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)

      test(assertion)
    }

    "when given input with only positive run-lengths, should return the expected decoding" - {

      def assertion(f: Solution[Int]): Unit =
        forAll((arbShuffledIntList, "(seed,l)")) {
          (_, base) =>
            val in = base.map(i => (i, i))
            val out = base.flatMap(i => List.fill(i)(i))
            f(in).rightValue shouldBe out
        }

      test(assertion)
    }

    "when given input with non-positive run lengths, should return an error" - {

      def assertion(f: Solution[Int]): Unit =
        forAll((arbShuffledIntList, "(seed,l)"), (Gen.chooseNum(-10, 0), "n")) {
          case ((_, base), n) =>
            val in = base.map(i => (i, i)) ++ List((n, n))
            f(in).leftValue shouldBe an[S399Error]
        }

      test(assertion)
    }
  }

  // === INFRASTRUCTURE ===

  type Solution[A] = List[(Int, A)] => Result[List[A]]

  given [A]: List[(S399Tag, Solution[A])] = List(
    S399Tag.ExerciseSolution -> X12.decode,
    S399Tag.PrimarySolution -> S12.decode,
    S399Tag.AlternateSolution -> A112.decode,
  )
