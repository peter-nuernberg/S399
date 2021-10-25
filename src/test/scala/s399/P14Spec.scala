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

/** The specification of the behavior of a correct solution to [[P14.duplicate]]. */
class P14Spec extends BaseSpec :

  // === ASSERTIONS ===

  "A solution to problem 14" - {

    "when given the example input, should return the example output" - {

      def assertion(f: Solution[Int]): Unit =
        f(List(1, 2, 3, 3, 4)).rightValue shouldBe List(1, 1, 2, 2, 3, 3, 3, 3, 4, 4)

      test(assertion)
    }

    "when given an empty list, should return an empty list" - {

      def assertion(f: Solution[Any]): Unit =
        f(Nil).rightValue shouldBe Nil

      test(assertion)
    }

    "when given a non-empty list, should return the expected duplicated list" - {

      def assertion(f: Solution[Int]): Unit =
        forAll((arbShuffledIntList, "(seed, l)")) {
          case (_, l) => f(l).rightValue shouldBe l.flatMap{ a => List(a, a) }
        }

      test(assertion)
    }
  }

  // === INFRASTRUCTURE ===

  type Solution[A] = List[A] => Result[List[A]]

  given [A]: List[(S399Tag, Solution[A])] = List(
    S399Tag.ExerciseSolution -> X14.duplicate,
    S399Tag.PrimarySolution -> S14.duplicate,
  )

